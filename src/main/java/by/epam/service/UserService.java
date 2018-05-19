package by.epam.service;

import by.epam.dao.DAOFactory;
import by.epam.dao.admin.impl.AdminDAOImpl;
import by.epam.dao.payment.impl.PaymentDAOImpl;
import by.epam.dao.publication.impl.PublicationDAOImpl;
import by.epam.dao.user.impl.UserDAOImpl;
import by.epam.constant.DAOTypes;
import by.epam.entity.Payment;
import by.epam.entity.Publication;
import by.epam.entity.User;
import by.epam.exception.DAOException;
import by.epam.exception.ServiceException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserService {

    private static final Logger LOGGER = LogManager.getLogger(UserService.class);

    public void addPublication(User user, Publication publication, int subscribtionPeriod) throws ServiceException {
        DAOFactory factory = new DAOFactory();
        double userBalance = user.getBalance();
        int userId = user.getId();
        double publicationPrice = publication.getPrice();
        String publicationName = publication.getName();
        String publicationPeriodType = publication.getPeriodType();
        int publicationId = publication.getId();
        try {
            UserDAOImpl userDAO = factory.getDAO(DAOTypes.USER_DAO);
            AdminDAOImpl adminDAO = factory.getDAO(DAOTypes.ADMIN_DAO);
            if (!userDAO.matchPublication(userId, publicationId)) {
                if (!(publicationPrice * subscribtionPeriod > userBalance)) {
                    Date date = new Date();
                    String stringDate = date.toString();
                    String finishDate = takeFinishDate(date, publicationPeriodType, subscribtionPeriod);
                    double systemBalance = adminDAO.takeSystemBalance();
                    factory.startTransaction();
                    adminDAO.setSystemBalance(systemBalance + publicationPrice * subscribtionPeriod);
                    userDAO.addPayment(userId, publicationPrice * subscribtionPeriod, publicationName, stringDate, subscribtionPeriod, finishDate);
                    userDAO.addPublicationToUser(userId, publicationId, subscribtionPeriod);
                    userDAO.setUserBalance(userId, userBalance - (publicationPrice * subscribtionPeriod));
                } else {
                    throw new ServiceException("Your money: " + userBalance + ". Publication price: " + publicationPrice);
                }
            } else {
                throw new ServiceException("You already subscribed to this publication");
            }
            factory.commitTransaction();
        } catch (DAOException e) {
            try {
                factory.rollbackTransaction();
            } catch (DAOException ex) {
                LOGGER.error(ex.getMessage(), ex);
                throw new ServiceException("Cannot rollback transaction", ex);
            }
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException("Publication " + publicationId + " doesn't exist or user " + user.getId() + " doesn't exist", e);
        }
    }

    public void setName(String currentName, String newName) throws ServiceException {
        DAOFactory factory = new DAOFactory();
        UserDAOImpl dao = factory.getDAO(DAOTypes.USER_DAO);
        try {
            dao.setName(currentName, newName);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(newName + " is busy", e);
        } finally {
            factory.closeConnection();
        }
    }

    public void setPassword(String username, String newpassword) throws ServiceException {
        DAOFactory factory = new DAOFactory();
        UserDAOImpl dao = factory.getDAO(DAOTypes.USER_DAO);
        try {
            dao.setPassword(username, newpassword);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException("Password wasn't changed. Try again", e);
        } finally {
            factory.closeConnection();
        }
    }

    public void deletePublicationFromUser(int userId, int publicationId) throws ServiceException {
        DAOFactory factory = new DAOFactory();
        UserDAOImpl dao = factory.getDAO(DAOTypes.USER_DAO);
        try {
            dao.deletePublicationFromUser(userId, publicationId);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        } finally {
            factory.closeConnection();
        }
    }

    public List<User> takeUsers() throws ServiceException {
        DAOFactory factory = new DAOFactory();
        UserDAOImpl dao = factory.getDAO(DAOTypes.USER_DAO);
        List<User> users = new ArrayList<>();
        try {
            users = dao.takeAllUsers();
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        } finally {
            factory.closeConnection();
        }
        return users;
    }

    public User takeUserByName(String username) throws ServiceException {
        DAOFactory factory = new DAOFactory();
        UserDAOImpl dao = factory.getDAO(DAOTypes.USER_DAO);
        User user = new User();
        try {
            user = dao.takeUserByName(username);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        } finally {
            factory.closeConnection();
        }
        return user;
    }

    private String takeFinishDate(Date date, String publicationType, int subscribtionPeriod) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        addPeriod(instance, publicationType, subscribtionPeriod);
        Date newDate = instance.getTime();
        return newDate.toString();
    }

    private void addPeriod(Calendar calendar, String publicationType, int subscribtionPeriod) {
        switch (publicationType) {
            case "year":
                calendar.add(Calendar.YEAR, subscribtionPeriod);
                break;
            case "month":
                calendar.add(Calendar.MONTH, subscribtionPeriod);
                break;
            case "weak":
                calendar.add(Calendar.WEEK_OF_MONTH, subscribtionPeriod);
                break;
        }
    }

}
