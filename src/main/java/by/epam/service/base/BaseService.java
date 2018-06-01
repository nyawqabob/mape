package by.epam.service.base;

import by.epam.exception.ServiceException;

public interface BaseService {

    public void checkNamePassword(String username, String password) throws ServiceException;
}
