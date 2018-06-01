package by.epam.dao.publication.impl;

import by.epam.dao.AbstractEntityDAO;
import by.epam.dao.AbstractDAO;
import by.epam.dao.publication.PublicationDAO;
import by.epam.constant.Parameters;
import by.epam.entity.Publication;
import by.epam.entity.User;
import by.epam.exception.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PublicationDAOImpl extends AbstractEntityDAO<Publication> implements PublicationDAO {

    private static final Logger LOGGER = LogManager.getLogger(PublicationDAOImpl.class);

    private static final String SQL_SELECT_PUBLICATIONOBJ_BY_ID = "SELECT * FROM publications WHERE id=?";
    private static final String SQL_SELECT_PUBLICATIONS_BY_USER = "SELECT publications.* FROM (SELECT publication_id AS pub_id FROM"
            + " user_publication WHERE user_id = (SELECT id FROM users WHERE users.login = ?))"
            + " AS T LEFT JOIN publications ON publications.id = pub_id";
    private static final String SQL_SELECT_ALL_PUBLICATION = "SELECT * FROM publications";
    private static final String SQL_SELECT_PUBLICATIONS_BY_NAME = "SELECT * FROM publications WHERE name=?";
    private static final String SQL_SELECT_PUBLICATIONS_BY_TYPE = "SELECT * FROM publications WHERE type=?";
    private static final String SQL_SELECT_USER_PUBLICATIONS_BY_NAME = "SELECT * FROM publications, users, user_publication WHERE publications.id = user_publication.publication_id AND users.id = user_publication.user_id AND users.login = ? AND publications.name = ?";
    private static final String SQL_SELECT_USER_PUBLICATIONS_BY_TYPE = "SELECT * FROM publications, users, user_publication WHERE publications.id = user_publication.publication_id AND users.id = user_publication.user_id AND users.login = ? AND publications.type = ?";
    public static final String SQL_SELECT_ID_BY_PUBLICATION = "SELECT id FROM publications WHERE name=?";
    private static final String SQL_SELECT_IMAGE_BY_PUBLICATION_ID = "UPDATE publications SET image=? WHERE id=?";
    private static final String SQL_SELECT_CHIPPIEST_PUBLICATION = "SELECT * FROM publications WHERE price = (SELECT MIN(price) FROM publications)";
    private static final String PRICE = "price";
    private static final String IMAGE = "image";
    private static final String PUBLICATION_NAME = "name";
    private static final String PUBLICATION_TYPE = "type";
    private static final String PERIOD_TYPE = "period_type";

    public PublicationDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Publication takePublicationById(int publicationId) throws DAOException {
        Publication publication = new Publication();
        PreparedStatement statement = null;
        try {
            publication = takeEntity(publicationId);
        } catch (SQLException e) {
            LOGGER.error("DAO take publication by id operation failed", e);
            throw new DAOException("DAO take publication by id operation failed", e);
        } finally {
            close(statement);
        }
        return publication;
    }

    @Override
    public Publication takeChippiestPublications() throws DAOException {
        Publication publication;
        try {
            ResultSet resultSet = executeQueryWithoutParameters(SQL_SELECT_CHIPPIEST_PUBLICATION);
            publication = parseResultSetToEntity(resultSet);
        } catch (SQLException e) {
            LOGGER.error("DAO take publication by id operation failed", e);
            throw new DAOException("DAO take publication by id operation failed", e);
        } finally {
            closeQueryStatement();
        }

        return publication;
    }

    @Override
    public void changePublicationImage(int publicationId, String newImage) throws DAOException {
        try {
            int rows = executeUpdate(SQL_SELECT_IMAGE_BY_PUBLICATION_ID, publicationId, newImage);
            if (rows != 1) {
                throw new DAOException("user " + publicationId + " doesn't exist");
            }
        } catch (SQLException e) {
            LOGGER.error("DAO set publication image by id operation failed", e);
            throw new DAOException("DAO set publication image by id operation failed", e);
        }
    }

    @Override
    public List<Publication> takeAllPublications() throws DAOException {
        List<Publication> publications = new ArrayList<>();
        PreparedStatement statement = null;
        try {
            publications = takeAll();
        } catch (SQLException e) {
            LOGGER.error("DAO take all publication operation failed", e);
            throw new DAOException("DAO take all publication operation failed", e);
        } finally {
            close(statement);
        }
        return publications;
    }

    @Override
    public List<Publication> takeUserPublications(String username) throws DAOException {
        List<Publication> publications = new ArrayList<>();
        try {
            ResultSet resultSet = executeQuery(SQL_SELECT_PUBLICATIONS_BY_USER, username);
            publications = parseResultSetToList(resultSet);
        } catch (SQLException e) {
            LOGGER.error("DAO take publications by name operation failed", e);
            throw new DAOException("DAO take publications by name operation failed", e);
        } finally {
            closeQueryStatement();
        }
        return publications;
    }

    @Override
    public List<Publication> takeConcretePublicationsByType(String publicationType) throws DAOException {
        List<Publication> publications = new ArrayList<>();
        try {
            ResultSet resultSet = executeQuery(SQL_SELECT_PUBLICATIONS_BY_TYPE, publicationType);
            publications = parseResultSetToList(resultSet);
        } catch (SQLException e) {
            LOGGER.error("DAO take publication name by type operation failed", e);
            throw new DAOException("DAO take publication name by type operation failed", e);
        } finally {
            closeQueryStatement();
        }
        return publications;
    }

    @Override
    public List<Publication> takeConcretePublicationsByName(String publicationName) throws DAOException {
        List<Publication> publications = new ArrayList<>();
        try {
            ResultSet resultSet = executeQuery(SQL_SELECT_PUBLICATIONS_BY_NAME, publicationName);
            publications = parseResultSetToList(resultSet);
        } catch (SQLException e) {
            LOGGER.error("DAO take publication name by type operation failed", e);
            throw new DAOException("DAO take publication name by type operation failed", e);
        } finally {
            closeQueryStatement();
        }
        return publications;
    }

    @Override
    public List<Publication> takeConcreteUserPublicationsByType(String username, String publicationType) throws DAOException {
        List<Publication> publications = new ArrayList<>();
        try {
            ResultSet resultSet = executeQuery(SQL_SELECT_USER_PUBLICATIONS_BY_TYPE, username, publicationType);
            publications = parseResultSetToList(resultSet);
        } catch (SQLException e) {
            LOGGER.error("DAO take user publication name by type operation failed", e);
            throw new DAOException("DAO take user publication name by type operation failed", e);
        } finally {
            closeQueryStatement();
        }
        return publications;
    }

    @Override
    public List<Publication> takeConcreteUserPublicationsByName(String username, String publicationName) throws DAOException {
        List<Publication> publications = new ArrayList<>();
        try {
            ResultSet resultSet = executeQuery(SQL_SELECT_USER_PUBLICATIONS_BY_NAME, username, publicationName);
            publications = parseResultSetToList(resultSet);
        } catch (SQLException e) {
            LOGGER.error("DAO take user publication name by type operation failed", e);
            throw new DAOException("DAO take user publication name by type operation failed", e);
        } finally {
            closeQueryStatement();
        }
        return publications;
    }

    @Override
    protected List<Publication> parseResultSetToList(ResultSet resultSet) {
        List<Publication> publications = new ArrayList<>();
        try {
            while (resultSet.next()) {
                String name = resultSet.getString(PUBLICATION_NAME);
                int id = resultSet.getInt(Parameters.ID);
                String type = resultSet.getString(PUBLICATION_TYPE);
                double price = resultSet.getDouble(PRICE);
                String image = resultSet.getString(IMAGE);
                String periodType = resultSet.getString(PERIOD_TYPE);
                Publication publication = new Publication(id, name, type, price, image, periodType);
                publications.add(publication);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return publications;
    }

    @Override
    protected Publication parseResultSetToEntity(ResultSet resultSet) {
        Publication publication = new Publication();
        try {
            if (resultSet.next()) {
                publication.setId(resultSet.getInt("id"));
                publication.setName(resultSet.getString("name"));
                publication.setPrice(resultSet.getDouble("price"));
                publication.setImage(resultSet.getString("image"));
                publication.setType(resultSet.getString("type"));
                publication.setPeriodType(resultSet.getString("period_type"));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return publication;
    }

    @Override
    public String getSelectAllQuery() {
        return SQL_SELECT_ALL_PUBLICATION;
    }

    @Override
    public String getSelectEntityQuery() {
        return SQL_SELECT_PUBLICATIONOBJ_BY_ID;
    }

}
