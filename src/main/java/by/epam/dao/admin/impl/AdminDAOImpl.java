package by.epam.dao.admin.impl;

import by.epam.dao.AbstractDAO;
import by.epam.dao.admin.AdminDAO;
import by.epam.exception.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AdminDAOImpl extends AbstractDAO implements AdminDAO {

    private static final Logger LOGGER = LogManager.getLogger(AdminDAOImpl.class);

    private static final String SQL_UPDATE_USER_STATUS = "UPDATE users SET status=? WHERE login=?";
    private static final String SQL_INSERT_PUBLICATION = "INSERT INTO publications(name, type, price, image, period_type) VALUES(?,?,?,?,?)";
    private static final String SQL_DELETE_PUBLICATION = "DELETE FROM publications WHERE id=?";
    private static final String SQL_SELECT_SYSTEM_BALANCE = "SELECT value FROM system_balance WHERE balance_type='system'";
    private static final String SQL_SET_SYSTEM_BALANCE = "UPDATE system_balance SET value=? WHERE balance_type='system'";
    private static final String SQL_UPDATE_USERNAME = "UPDATE users SET login=? WHERE login=?";
    private static final String SQL_UPDATE_USER_BALANCE = "UPDATE users SET balance=? WHERE login=?";
    private static final String SQL_UPDATE_PUBLICATION_NAME = "UPDATE publications SET name=? WHERE id=?";
    private static final String SQL_UPDATE_PUBLICATION_PRICE = "UPDATE publications SET price=? WHERE id=?";
    private static final String SQL_UPDATE_PUBLICATION_TYPE = "UPDATE publications SET type=? WHERE id=?";
    private static final String VALUE = "value";

    public AdminDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void changeUserStatus(String username, String status) throws DAOException {
        try {
            int rows = executeUpdate(SQL_UPDATE_USER_STATUS, status, username);
            if (rows != 1) {
                throw new DAOException("user " + username + " doesn't exist");
            }
        } catch (SQLException e) {
            LOGGER.error("DAO change status by name operation failed", e);
            throw new DAOException("DAO change status by name operation failed", e);
        }
    }

    @Override
    public void changeUserName(String newName, String oldName) throws DAOException {
        try {
            int rows = executeUpdate(SQL_UPDATE_USERNAME, newName, oldName);
            if (rows != 1) {
                throw new DAOException("user " + oldName + " doesn't exist");
            }
        } catch (SQLException e) {
            LOGGER.error("DAO change status by name operation failed", e);
            throw new DAOException("DAO change status by name operation failed", e);
        }
    }

    @Override
    public void changePublicationName(String newName, int id) throws DAOException {
        try {
            int rows = executeUpdate(SQL_UPDATE_PUBLICATION_NAME, newName, id);
            if (rows != 1) {
                throw new DAOException("publication " + id + " doesn't exist");
            }
        } catch (SQLException e) {
            LOGGER.error("DAO change status by name operation failed", e);
            throw new DAOException("DAO change status by name operation failed", e);
        }
    }

    @Override
    public void changePublicationPrice(int id, int newPrice) throws DAOException {
        try {
            int rows = executeUpdate(SQL_UPDATE_PUBLICATION_PRICE, newPrice, id);
            if (rows != 1) {
                throw new DAOException("publication " + id + " doesn't exist");
            }
        } catch (SQLException e) {
            LOGGER.error("DAO change status by name operation failed", e);
            throw new DAOException("DAO change status by name operation failed", e);
        }
    }

    @Override
    public void changePublicationType(int id, String newStatus) throws DAOException {
        try {
            int rows = executeUpdate(SQL_UPDATE_PUBLICATION_TYPE, newStatus, id);
            if (rows != 1) {
                throw new DAOException("publication " + id + " doesn't exist");
            }
        } catch (SQLException e) {
            LOGGER.error("DAO change status by name operation failed", e);
            throw new DAOException("DAO change status by name operation failed", e);
        }
    }

    @Override
    public void changeUserBalance(String username, int newAmount) throws DAOException {
        try {
            int rows = executeUpdate(SQL_UPDATE_USER_BALANCE, newAmount, username);
            if (rows != 1) {
                throw new DAOException("user " + username + " doesn't exist");
            }
        } catch (SQLException e) {
            LOGGER.error("DAO change status by name operation failed", e);
            throw new DAOException("DAO change status by name operation failed", e);
        }
    }

    @Override
    public void addPublication(String publicationName, String publicationType, double publicationPrice, String publicationImage, String publicationPeriodType) throws DAOException {
        try {
            executeUpdate(SQL_INSERT_PUBLICATION, publicationName, publicationType, publicationPrice, publicationImage, publicationPeriodType);
        } catch (SQLException e) {
            LOGGER.error("DAO change status by name operation failed", e);
            throw new DAOException("DAO change status by name operation failed", e);
        }
    }

    @Override
    public void deletePublication(int publicationId) throws DAOException {
        try {
            int rows = executeUpdate(SQL_DELETE_PUBLICATION, publicationId);
            if (rows != 1) {
                throw new DAOException("Publication " + publicationId + " doesn't exist");
            }
        } catch (SQLException e) {
            LOGGER.error("DAO delete publication by id name operation failed", e);
            throw new DAOException("DAO delete publication by id operation failed", e);
        }
    }

    @Override
    public double takeSystemBalance() throws DAOException {
        double systemBalance;
        try {
            ResultSet resultSet = executeQueryWithoutParameters(SQL_SELECT_SYSTEM_BALANCE);
            if (resultSet.next()) {
                systemBalance = resultSet.getDouble(VALUE);
                return systemBalance;
            } else {
                throw new DAOException("DAO take system balance operation failed");
            }
        } catch (SQLException e) {
            LOGGER.error("DAO take system balance operation failed", e);
            throw new DAOException("DAO take system balance operation failed", e);
        } finally {
            closeQueryStatement();
        }
    }

    @Override
    public void changeSystemBalance(double newBalance) throws DAOException {
        try {
            executeUpdate(SQL_SET_SYSTEM_BALANCE, newBalance);
        } catch (SQLException e) {
            LOGGER.error("DAO set system balance operation failed", e);
            throw new DAOException("DAO set system balance operation failed", e);
        }
    }

}
