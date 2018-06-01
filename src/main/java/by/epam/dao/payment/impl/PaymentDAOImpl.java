package by.epam.dao.payment.impl;

import by.epam.dao.AbstractEntityDAO;
import by.epam.dao.payment.PaymentDAO;
import by.epam.entity.Payment;
import by.epam.exception.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PaymentDAOImpl extends AbstractEntityDAO<Payment> implements PaymentDAO {

    Logger LOGGER = LogManager.getLogger(PaymentDAOImpl.class);

    private static final String SQL_SELECT_PAYMENTS_BY_USERID = "SELECT * FROM payments WHERE userid = ?";
    private static final String SQL_SELECT_ALL_PAYMENTS = "SELECT * FROM payments";
    private static final String AMOUNT = "amount";
    private static final String DATE = "date";
    private static final String PUBLICATION_NAME = "publication_name";
    private static final String SUBSCRIBTION_PERIOD = "subscribtion_period";
    private static final String USER_ID = "userid";
    private static final String FINISH_DATE = "data_finish";

    public PaymentDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Payment> takeUserPayments(int userId) throws DAOException {
        List<Payment> payments = new ArrayList<>();
        try {
            ResultSet resultSet = executeQuery(SQL_SELECT_PAYMENTS_BY_USERID, userId);
            payments = parseResultSetToList(resultSet);
        } catch (SQLException e) {
            LOGGER.error("DAO take payments by id operation failed", e);
            throw new DAOException("DAO take payments  by id operation failed", e);
        }
        return payments;
    }

    @Override
    public List<Payment> takeAllPayments() throws DAOException {
        List<Payment> payments = new ArrayList<>();
        PreparedStatement statement = null;
        try {
            payments = takeAll();
        } catch (SQLException e) {
            LOGGER.error("DAO take payments operation failed", e);
            throw new DAOException("DAO take payments operation failed", e);
        } finally {
            close(statement);
        }
        return payments;
    }

    @Override
    protected List<Payment> parseResultSetToList(ResultSet resultSet) {
        List<Payment> payments = new ArrayList<>();
        try {
            while (resultSet.next()) {
                double amount = resultSet.getDouble(AMOUNT);
                String date = resultSet.getString(DATE);
                String publicationName = resultSet.getString(PUBLICATION_NAME);
                int subscribtionPeriod = resultSet.getInt(SUBSCRIBTION_PERIOD);
                int userId = resultSet.getInt(USER_ID);
                String finishDate = resultSet.getString(FINISH_DATE);
                Payment payment = new Payment(amount, date, publicationName, subscribtionPeriod, userId, finishDate);
                payments.add(payment);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return payments;
    }

    @Override
    public String getSelectAllQuery() {
        return SQL_SELECT_ALL_PAYMENTS;
    }

    @Override
    public String getSelectEntityQuery() {
        return "faf";
    }

    @Override
    public Payment parseResultSetToEntity(ResultSet resultSet) {
        return new Payment();
    }

}
