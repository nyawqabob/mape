package by.epam.service;

import by.epam.dao.DAOFactory;
import by.epam.dao.payment.impl.PaymentDAOImpl;
import by.epam.constant.DAOTypes;
import by.epam.entity.Payment;
import by.epam.exception.DAOException;
import by.epam.exception.ServiceException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PaymentService {

    private static final Logger LOGGER = LogManager.getLogger(PaymentService.class);

    public List<Payment> takeUserPayments(int userId) throws ServiceException {
        DAOFactory factory = new DAOFactory();
        PaymentDAOImpl dao = factory.getDAO(DAOTypes.PAYMENT_DAO);
        List<Payment> payments = new ArrayList<>();
        try {
            payments = dao.takeUserPayments(userId);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException("User " + userId + " doesn't exist", e);
        } finally {
            factory.closeConnection();
        }
        return payments;
    }

    public List<Payment> takeAllPayments() throws ServiceException {
        DAOFactory factory = new DAOFactory();
        PaymentDAOImpl dao = factory.getDAO(DAOTypes.PAYMENT_DAO);
        List<Payment> payments = new ArrayList<>();
        try {
            payments = dao.takeAllPayments();
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException("Payments aren't avialable now", e);
        } finally {
            factory.closeConnection();
        }
        return payments;
    }
    
    

}
