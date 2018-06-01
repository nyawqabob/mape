package by.epam.service.payment;

import by.epam.entity.Payment;
import by.epam.exception.ServiceException;
import java.util.List;

public interface PaymentService {

    public List<Payment> takeUserPayments(int userId) throws ServiceException;

    public List<Payment> takeAllPayments() throws ServiceException;
}
