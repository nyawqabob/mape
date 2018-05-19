package by.epam.dao.payment;

import by.epam.entity.Payment;
import by.epam.exception.DAOException;
import java.util.List;

public interface PaymentDAO {

    public List<Payment> takeUserPayments(int userId) throws DAOException;

    public List<Payment> takeAllPayments() throws DAOException;
}
