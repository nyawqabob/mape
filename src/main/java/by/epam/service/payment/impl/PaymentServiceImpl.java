package by.epam.service.payment.impl;

import by.epam.dao.DAOFactory;
import by.epam.dao.payment.impl.PaymentDAOImpl;
import by.epam.constant.DAOTypes;
import by.epam.entity.Payment;
import by.epam.exception.DAOException;
import by.epam.exception.ServiceException;
import by.epam.service.payment.PaymentService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PaymentServiceImpl implements PaymentService {

    private static final Logger LOGGER = LogManager.getLogger(PaymentServiceImpl.class);

    @Override
    public List<Payment> takeUserPayments(int userId) throws ServiceException {
        DAOFactory factory = new DAOFactory();
        PaymentDAOImpl dao = factory.getDAO(DAOTypes.PAYMENT_DAO);
        List<Payment> payments = new ArrayList<>();
        try {
            payments = dao.takeUserPayments(userId);
            convertSecondsSinceEpochToDate(payments);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException("User " + userId + " doesn't exist", e);
        } finally {
            factory.closeConnection();
        }
        return payments;
    }

    @Override
    public List<Payment> takeAllPayments() throws ServiceException {
        DAOFactory factory = new DAOFactory();
        PaymentDAOImpl dao = factory.getDAO(DAOTypes.PAYMENT_DAO);
        List<Payment> payments = new ArrayList<>();
        try {
            payments = dao.takeAllPayments();
            convertSecondsSinceEpochToDate(payments);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException("Payments aren't avialable now", e);
        } finally {
            factory.closeConnection();
        }
        return payments;
    }

    private void convertSecondsSinceEpochToDate(List<Payment> payments) {
        for (Payment payment : payments) {
            String stringDate = payment.getDate();
            String stringFinishDate = payment.getFinishDate();
            long dateSecondsSinceEoch = Long.valueOf(stringDate).longValue();
            long finishDateSecondsSinceEoch = Long.valueOf(stringFinishDate).longValue();
            Date date = new Date(dateSecondsSinceEoch);
            Date finishDate = new Date(finishDateSecondsSinceEoch);
            payment.setDate(date.toString());
            payment.setFinishDate(finishDate.toString());

        }
    }

}
