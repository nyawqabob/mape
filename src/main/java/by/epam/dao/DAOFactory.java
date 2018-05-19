package by.epam.dao;

import by.epam.dao.admin.impl.AdminDAOImpl;
import by.epam.dao.base.impl.BaseDAOImpl;
import by.epam.dao.payment.impl.PaymentDAOImpl;
import by.epam.dao.publication.impl.PublicationDAOImpl;
import by.epam.dao.user.impl.UserDAOImpl;
import by.epam.constant.DAOTypes;
import by.epam.exception.DAOException;
import by.epam.pool.ConnectionPool;
import java.sql.Connection;
import java.sql.SQLException;

public class DAOFactory {

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private Connection connection;

    /**
     * Need to create connection if connection is null
     */
    public DAOFactory() {
        if (connection == null) {
            connection = connectionPool.getConnection();

        }
    }

    /**
     * Get needed dao by type
     * @param <T> extends abstractdao
     * @param daoType this is string of daotype
     * @return smth extends abstractdao
     */
    public <T extends AbstractDAO> T getDAO(String daoType) {
        switch (daoType) {
            case DAOTypes.ADMIN_DAO:
                return (T) new AdminDAOImpl(connection);
            case DAOTypes.BASE_DAO:
                return (T) new BaseDAOImpl(connection);
            case DAOTypes.USER_DAO:
                return (T) new UserDAOImpl(connection);
            case DAOTypes.PUBLICATION_DAO:
                return (T) new PublicationDAOImpl(connection);
            case DAOTypes.PAYMENT_DAO:
                return (T) new PaymentDAOImpl(connection);
            default:
                throw new RuntimeException("afs");
        }

    }

    /**
     * Need to set auto commit of connection to false
     */
    public void startTransaction() {
        try {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        } catch (SQLException e) {
        }
    }

    /**
     * Need to commit connection and put it back to pool
     * @throws DAOException
     */
    public void commitTransaction() throws DAOException {
        try {
            connection.commit();
            connectionPool.putConnectionBack(connection);
        } catch (SQLException e) {
            throw new DAOException("Cannot commit transaction");
        }
    }

    /**
     * Need to rollback connection and put it back to pool
     * @throws DAOException
     */
    public void rollbackTransaction() throws DAOException {
        try {
            connection.rollback();
            connectionPool.putConnectionBack(connection);
        } catch (SQLException e) {
            throw new DAOException("Cannot rollback transaction");
        }
    }

    public void closeConnection() {
        connectionPool.putConnectionBack(connection);
    }
}
