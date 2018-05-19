package by.epam.dao.base.impl;

import by.epam.dao.AbstractDAO;
import by.epam.dao.base.BaseDAO;
import by.epam.exception.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDAOImpl extends AbstractDAO implements BaseDAO {

    private static final String SQL_SELECT_USER_BY_LOGIN_AND_PASSWORD = "SELECT login,password FROM users WHERE login=? AND password=?";

    public BaseDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean isRightUsernameAndPassword(String username, String password) throws DAOException {
        try {
            ResultSet resultSet = executeQuery(SQL_SELECT_USER_BY_LOGIN_AND_PASSWORD, username, password);
            boolean isRight = resultSet.next();
            return isRight;
        } catch (SQLException e) {
            throw new DAOException("Operation check username/password failed", e);
        } finally {
            closeQueryStatement();
        }
    }

}
