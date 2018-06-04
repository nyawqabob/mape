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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DAOFactory {

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final Logger LOGGER = LogManager.getLogger(DAOFactory.class);
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
     * @throws DAOException when can't start transaction
     */
    public void startTransaction() throws DAOException {
        try {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        } catch (SQLException ex) {
            LOGGER.info("Cannot start transaction", ex);
            throw new DAOException("Cannot start transaction");
        }
    }

    /**
     * Need to commit connection and put it back to pool
     * @throws DAOException when can't commit transaction
     */
    public void commitTransaction() throws DAOException {
        try {
            connection.commit();
            connectionPool.putConnectionBack(connection);
        } catch (SQLException ex) {
            LOGGER.info("Cannot start transaction", ex);
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
        } catch (SQLException ex) {
            LOGGER.info("Cannot rollback transaction", ex);
            throw new DAOException("Cannot rollback transaction");
        }
    }

    public void closeConnection() {
        connectionPool.putConnectionBack(connection);
    }
}
