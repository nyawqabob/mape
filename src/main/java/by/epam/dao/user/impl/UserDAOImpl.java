package by.epam.dao.user.impl;

import by.epam.dao.AbstractEntityDAO;
import by.epam.dao.user.UserDAO;
import by.epam.constant.Parameters;
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

public class UserDAOImpl extends AbstractEntityDAO<User> implements UserDAO {

    private static final Logger LOGGER = LogManager.getLogger(UserDAOImpl.class);

    private static final String SQL_SELECT_ALL_USERS = "SELECT * FROM users WHERE role='user'";
    public static final String SQL_SELECT_USER_BY_ID = "SELECT id FROM users WHERE login=?";
    public static final String SQL_INSERT_PUBLICATION_TO_USER = "INSERT INTO user_publication(user_id, publication_id, subscribtion_periods) VALUES(?,?,?)";
    public static final String SQL_DELETE_PUBLICATION_FROM_USER = "DELETE FROM user_publication WHERE user_id =? AND publication_id = ?";
    private static final String SQL_SELECT_PUBLICATION_BY_USER = "SELECT user_id FROM user_publication WHERE user_id=? AND publication_id=? ";
    private static final String SQL_UPDATE_USER_BALANCE = "UPDATE users SET balance=? WHERE id=?";
    private static final String SQL_ADD_PAYMENT = "INSERT INTO payments(amount, date, publication_name, subscribtion_period, userid, data_finish) VALUES(?,?,?,?,?,?)";
    private static final String SQL_UPDATE_USERNAME = "UPDATE users SET login=? WHERE login=?";
    private static final String SQL_UPDATE_PASSWORD = "UPDATE users SET password=? WHERE login=?";
    private static final String SQL_SELECT_USEROBJ_BY_ID = "SELECT * FROM users WHERE login=?";
    private static final String SQL_SELECT_USER_BY_EMAIL = "SELECT * FROM users WHERE email=?";

    public UserDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User takeUserByName(String username) throws DAOException {
        User user = new User();
        PreparedStatement statement = null;
        try {
            user = takeEntity(username);
        } catch (SQLException e) {
            LOGGER.error("DAO take user by name operation failed", e);
            throw new DAOException("DAO take user by name operation failed", e);
        } finally {
            close(statement);
        }
        return user;
    }

    @Override
    public User takeUserByEmail(String email) throws DAOException {
        User user = new User();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_USER_BY_EMAIL);
            statement.setObject(1, email);
            ResultSet rs = statement.executeQuery();
            user = parseResultSetToEntity(rs);
        } catch (SQLException e) {
            LOGGER.error("DAO take user by name operation failed", e);
            throw new DAOException("DAO take user by name operation failed", e);
        } finally {
            close(statement);
        }
        return user;
    }

    @Override
    public void addPublicationToUser(int userId, int publicationId, int publicationPeriod) throws DAOException {
        try {
            executeUpdate(SQL_INSERT_PUBLICATION_TO_USER, userId, publicationId, publicationPeriod);
        } catch (SQLException e) {
            LOGGER.error("DAO add publication to user operation failed", e);
            throw new DAOException("DAO add publication to user operation failed", e);
        }

    }

    @Override
    public boolean isUserExist(String email) throws DAOException {
        try {
            ResultSet resultSet = executeQuery(SQL_SELECT_USER_BY_EMAIL, email);
            boolean isRight = resultSet.next();
            return isRight;
        } catch (SQLException e) {
            LOGGER.error("DAO add publication to user operation failed", e);
            throw new DAOException("DAO add publication to user operation failed", e);
        }

    }

    @Override
    public boolean isMatchPublicationAtUser(int userId, int publicationId) throws DAOException {
        try {
            ResultSet resultSet = executeQuery(SQL_SELECT_PUBLICATION_BY_USER, userId, publicationId);
            boolean isMatched = resultSet.next();
            return isMatched;
        } catch (SQLException e) {
            LOGGER.error("DAO match publication operation failed", e);
            throw new DAOException("DAO match publication operation failed", e);
        } finally {
            closeQueryStatement();
        }
    }

    @Override
    public void changeUserBalance(int userId, double newBalance) throws DAOException {
        try {
            int rows = executeUpdate(SQL_UPDATE_USER_BALANCE, newBalance, userId);
            if (rows != 1) {
                throw new DAOException("User " + userId + " doesn't exist");
            }
        } catch (SQLException e) {
            LOGGER.error("DAO set balance by id operation failed", e);
            throw new DAOException("DAO set balance by id operation failed", e);
        }
    }

    @Override
    public void addPayment(int userId, double amount, String publicationName, String date, int subscribtionPeriod, String finishDate) throws DAOException {
        try {
            executeUpdate(SQL_ADD_PAYMENT, amount, date, publicationName, subscribtionPeriod, userId, finishDate);
        } catch (SQLException e) {
            LOGGER.error("DAO add money by id operation failed", e);
            throw new DAOException("DAO add money  by id operation failed", e);

        }
    }

    @Override
    public void deletePublicationFromUser(int userId, int publicationId) throws DAOException {
        try {
            int rows = executeUpdate(SQL_DELETE_PUBLICATION_FROM_USER, userId, publicationId);
            if (rows != 1) {
                throw new DAOException("Publication " + publicationId + " doesn't exist");
            }
        } catch (SQLException e) {
            LOGGER.error("DAO delete publication by id name operation failed", e);
            throw new DAOException("DAO delete publication by id operation failed", e);
        }

    }

    @Override
    public void changeName(String currentName, String newName) throws DAOException {
        try {
            int rows = executeUpdate(SQL_UPDATE_USERNAME, newName, currentName);
            if (rows != 1) {
                throw new DAOException("User " + currentName + " doesn't exist");
            }
        } catch (SQLException e) {
            LOGGER.error("DAO set name operation failed", e);
            throw new DAOException("DAO set name operation failed", e);
        }
    }

    @Override
    public void changePassword(String username, String newPassword) throws DAOException {
        try {
            int rows = executeUpdate(SQL_UPDATE_PASSWORD, newPassword, username);
            if (rows != 1) {
                throw new DAOException("User " + username + " doesn't exist");
            }
        } catch (SQLException e) {
            LOGGER.error("DAO set name operation failed", e);
            throw new DAOException("DAO set name operation failed", e);
        }
    }

    @Override
    public List<User> takeAllUsers() throws DAOException {
        List<User> users = new ArrayList<>();
        PreparedStatement statement = null;
        try {
            users = takeAll();
        } catch (SQLException e) {
            LOGGER.error("DAO take payments by id operation failed", e);
            throw new DAOException("DAO take payments  by id operation failed", e);
        } finally {
            close(statement);
        }
        return users;
    }

    @Override
    protected List<User> parseResultSetToList(ResultSet resultSet) {
        List<User> users = new ArrayList<>();
        try {
            while (resultSet.next()) {
                String name = resultSet.getString(Parameters.LOGIN);
                int id = resultSet.getInt(Parameters.ID);
                String pass = resultSet.getString(Parameters.PASSWORD);
                double balance = resultSet.getDouble(Parameters.BALANCE);
                String role = resultSet.getString(Parameters.ROLE);
                String status = resultSet.getString(Parameters.STATUS);
                String email = resultSet.getString(Parameters.EMAIL);
                User user = new User(id, name, pass, balance, role, status, email);
                users.add(user);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return users;
    }

    @Override
    protected User parseResultSetToEntity(ResultSet resultSet) {
        User user = new User();
        try {
            if (resultSet.next()) {
                user.setName(resultSet.getString(Parameters.LOGIN));
                user.setId(resultSet.getInt(Parameters.ID));
                user.setPass(resultSet.getString(Parameters.PASSWORD));
                user.setBalance(resultSet.getDouble(Parameters.BALANCE));
                user.setRole(resultSet.getString(Parameters.ROLE));
                user.setStatus(resultSet.getString(Parameters.STATUS));
                user.setEmail(resultSet.getString(Parameters.EMAIL));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return user;
    }

    @Override
    public String getSelectAllQuery() {
        return SQL_SELECT_ALL_USERS;
    }

    @Override
    public String getSelectEntityQuery() {
        return SQL_SELECT_USEROBJ_BY_ID;
    }

    public String getSelectUserByEmail() {
        return SQL_SELECT_USER_BY_EMAIL;
    }

}
