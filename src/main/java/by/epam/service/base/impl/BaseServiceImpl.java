package by.epam.service.base.impl;

import by.epam.dao.base.impl.BaseDAOImpl;
import by.epam.dao.DAOFactory;
import by.epam.constant.DAOTypes;
import by.epam.exception.DAOException;
import by.epam.exception.ServiceException;
import by.epam.service.base.BaseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseServiceImpl implements BaseService {

    private static final Logger LOGGER = LogManager.getLogger(BaseServiceImpl.class);

    @Override
    public void checkNamePassword(String username, String password) throws ServiceException {

        DAOFactory factory = new DAOFactory();
        BaseDAOImpl dao = factory.getDAO(DAOTypes.BASE_DAO);
        try {
            if (!dao.isRightUsernameAndPassword(username, password)) {
                throw new ServiceException("Wrong username or password");
            }
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException("User " + username + " doesn't exist", e);
        } finally {
            factory.closeConnection();
        }
    }
}
