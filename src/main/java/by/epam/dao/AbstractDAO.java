package by.epam.dao;

import by.epam.entity.Entity;
import by.epam.exception.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractDAO<T extends Entity> {

    private static final Logger LOGGER = LogManager.getLogger(AbstractDAO.class);
    private PreparedStatement statementForQuery = null;

    protected Connection connection;

    protected void close(Statement st) {
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            LOGGER.info("Can't close statement", e);
        }
    }

    /**
     * Common executeUpdate
     * @param query need to take statement from connection
     * @param args need for prepared statemend
     * @return amount of updated rows
     * @throws SQLException
     */
    protected int executeUpdate(String query, Object... args) throws SQLException {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(query);
            int objectNumber = 1;
            for (Object arg : args) {
                statement.setObject(objectNumber, arg);
                objectNumber++;
            }
            return statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.info("Operation executeUpdate failed", e);
            throw new SQLException("Operation executeUpdate failed", e);
        } finally {
            close(statement);
        }
    }

    /**
     * Common executeQuery
     * @param query need to take statement from connection
     * @param args need for prepared statement
     * @return result set from default statment executeQuery
     * @throws SQLException
     */
    protected ResultSet executeQuery(String query, Object... args) throws SQLException {
        statementForQuery = null;
        try {
            statementForQuery = connection.prepareStatement(query);
            int objectNumber = 1;
            for (Object arg : args) {
                statementForQuery.setObject(objectNumber, arg);
                objectNumber++;
            }
            ResultSet resultSet = statementForQuery.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            throw new SQLException("Operation executeQuery failed", e);
        }
    }

    /**
     * Looks like executeQuery from this class but without args 
     */
    protected ResultSet executeQueryWithoutParameters(String query) throws SQLException {
        statementForQuery = null;
        try {
            statementForQuery = connection.prepareStatement(query);
            ResultSet resultSet = statementForQuery.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            throw new SQLException("Operation executeQuery failed", e);
        }
    }

    protected void closeQueryStatement() {
        close(statementForQuery);
    }

}
