package by.epam.service.admin.impl;

import by.epam.dao.admin.impl.AdminDAOImpl;
import by.epam.dao.DAOFactory;
import by.epam.dao.user.impl.UserDAOImpl;
import by.epam.constant.Constants;
import by.epam.constant.DAOTypes;
import by.epam.entity.User;
import by.epam.exception.DAOException;
import by.epam.exception.ServiceException;
import by.epam.service.admin.AdminService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AdminServiceImpl implements AdminService {

    private static final Logger LOGGER = LogManager.getLogger(AdminServiceImpl.class);

    @Override
    public void addPublication(String publicationName, String publicationType, double publicationPrice, String publicationImage, String periodType) throws ServiceException {
        DAOFactory factory = new DAOFactory();
        AdminDAOImpl adminDAO = factory.getDAO(DAOTypes.ADMIN_DAO);
        try {
            adminDAO.addPublication(publicationName, publicationType, publicationPrice, publicationImage, periodType);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException("Choose another image", e);
        } finally {
            factory.closeConnection();
        }
    }

    @Override
    public void setUserStatus(String username, String status) throws ServiceException {
        DAOFactory factory = new DAOFactory();
        UserDAOImpl userDAO = factory.getDAO(DAOTypes.USER_DAO);
        AdminDAOImpl adminDAO = factory.getDAO(DAOTypes.ADMIN_DAO);
        try {
            statusLogic(username, status, adminDAO, userDAO);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException("This user doesn't exist", e);
        } finally {
            factory.closeConnection();
        }
    }

    @Override
    public void deletePublication(int publicationId) throws ServiceException {
        DAOFactory factory = new DAOFactory();
        AdminDAOImpl adminDAO = factory.getDAO(DAOTypes.ADMIN_DAO);
        try {
            adminDAO.deletePublication(publicationId);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException("Someone subscribe to this publication", e);
        } finally {
            factory.closeConnection();
        }
    }

    @Override
    public double takeSystemBalance() throws ServiceException {
        DAOFactory factory = new DAOFactory();
        AdminDAOImpl adminDAO = factory.getDAO(DAOTypes.ADMIN_DAO);
        try {
            double systemBalance = adminDAO.takeSystemBalance();
            return systemBalance;
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException("System balance isn't avialable now", e);
        } finally {
            factory.closeConnection();
        }
    }

    @Override
    public void setUserName(String newName, String oldName) throws ServiceException {
        DAOFactory factory = new DAOFactory();
        AdminDAOImpl adminDAO = factory.getDAO(DAOTypes.ADMIN_DAO);
        try {
            adminDAO.changeUserName(oldName, newName);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException("This user doesn't exist", e);
        } finally {
            factory.closeConnection();
        }
    }

    @Override
    public void setPublicationName(String newName, int id) throws ServiceException {
        DAOFactory factory = new DAOFactory();
        AdminDAOImpl adminDAO = factory.getDAO(DAOTypes.ADMIN_DAO);
        try {
            adminDAO.changePublicationName(newName, id);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException("This publication doesn't exist", e);
        } finally {
            factory.closeConnection();
        }
    }

    @Override
    public void setPublicationType(int id, String newType) throws ServiceException {
        DAOFactory factory = new DAOFactory();
        AdminDAOImpl adminDAO = factory.getDAO(DAOTypes.ADMIN_DAO);
        try {
            adminDAO.changePublicationType(id, newType);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException("This publication doesn't exist", e);
        } finally {
            factory.closeConnection();
        }
    }

    @Override
    public void setPublicationPrice(int id, int newPrice) throws ServiceException {
        DAOFactory factory = new DAOFactory();
        AdminDAOImpl adminDAO = factory.getDAO(DAOTypes.ADMIN_DAO);
        try {
            adminDAO.changePublicationPrice(id, newPrice);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException("This publication doesn't exist", e);
        } finally {
            factory.closeConnection();
        }
    }

    @Override
    public void setUserBalance(String username, int newBalance) throws ServiceException {
        DAOFactory factory = new DAOFactory();
        AdminDAOImpl adminDAO = factory.getDAO(DAOTypes.ADMIN_DAO);
        try {
            adminDAO.changeUserBalance(username, newBalance);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException("This user doesn't exist", e);
        } finally {
            factory.closeConnection();
        }
    }

    private void statusLogic(String username, String status, AdminDAOImpl adminDAO, UserDAOImpl userDAO) throws ServiceException, DAOException {
        User user = userDAO.takeUserByName(username);
        switch (status) {
            case Constants.BANNED_STATUS:
                if (Constants.BANNED_STATUS.equals(user.getStatus())) {
                    throw new ServiceException("This user already banned");
                }
                if (Constants.ADMIN_ROLE.equals(user.getRole())) {
                    throw new ServiceException("You can't ban this user");
                }
                adminDAO.changeUserStatus(username, status);
                break;
            case Constants.NOT_BANNED_STATUS:
                if (Constants.NOT_BANNED_STATUS.equals(user.getStatus())) {
                    throw new ServiceException("This user was not banned");
                }
                adminDAO.changeUserStatus(username, status);
                break;
        }

    }

}
