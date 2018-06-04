package by.epam.service.user.impl;

import by.epam.dao.DAOFactory;
import by.epam.dao.admin.impl.AdminDAOImpl;
import by.epam.dao.user.impl.UserDAOImpl;
import by.epam.constant.DAOTypes;
import by.epam.entity.Publication;
import by.epam.entity.User;
import by.epam.exception.DAOException;
import by.epam.exception.ServiceException;
import by.epam.service.user.UserService;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

    @Override
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
            if (!userDAO.isMatchPublicationAtUser(userId, publicationId)) {
                if (!(publicationPrice * subscribtionPeriod > userBalance)) {
                    Date date = new Date();
                    Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
                    long secondsSinceEpoch = calendar.getTimeInMillis();
                    String stringSecondsSinceEpoch = Long.toString(secondsSinceEpoch);
                    String stringFinishSecondsSinceEpoch = takeFinishDate(date, publicationPeriodType, subscribtionPeriod);
                    double systemBalance = adminDAO.takeSystemBalance();
                    factory.startTransaction();
                    adminDAO.changeSystemBalance(systemBalance + publicationPrice * subscribtionPeriod);
                    userDAO.addPayment(userId, publicationPrice * subscribtionPeriod, publicationName, stringSecondsSinceEpoch, subscribtionPeriod, stringFinishSecondsSinceEpoch);
                    userDAO.addPublicationToUser(userId, publicationId, subscribtionPeriod);
                    userDAO.changeUserBalance(userId, userBalance - (publicationPrice * subscribtionPeriod));
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
        } finally {
            factory.closeConnection();
        }
    }

    @Override
    public void setName(String currentName, String newName) throws ServiceException {
        DAOFactory factory = new DAOFactory();
        UserDAOImpl dao = factory.getDAO(DAOTypes.USER_DAO);
        try {
            dao.changeName(currentName, newName);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(newName + " is busy", e);
        } finally {
            factory.closeConnection();
        }
    }

    @Override
    public boolean isUserExist(String email) throws ServiceException {
        DAOFactory factory = new DAOFactory();
        UserDAOImpl dao = factory.getDAO(DAOTypes.USER_DAO);
        try {
            return dao.isUserExist(email);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(email + "doesn't exist", e);
        } finally {
            factory.closeConnection();
        }
    }

    @Override
    public void setPassword(String username, String newpassword) throws ServiceException {
        DAOFactory factory = new DAOFactory();
        UserDAOImpl dao = factory.getDAO(DAOTypes.USER_DAO);
        try {
            dao.changePassword(username, newpassword);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException("Password wasn't changed. Try again", e);
        } finally {
            factory.closeConnection();
        }
    }

    @Override
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

    @Override
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

    @Override
    public User takeUserByName(String username) throws ServiceException {
        DAOFactory factory = new DAOFactory();
        UserDAOImpl dao = factory.getDAO(DAOTypes.USER_DAO);
        User user;
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

    @Override
    public User takeUserByEmail(String email) throws ServiceException {
        DAOFactory factory = new DAOFactory();
        UserDAOImpl dao = factory.getDAO(DAOTypes.USER_DAO);
        User user = new User();
        try {
            user = dao.takeUserByEmail(email);
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
        long finishDate = instance.getTimeInMillis();
        String stringSecondSinceEpoch = Long.toString(finishDate);
        return stringSecondSinceEpoch;
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
