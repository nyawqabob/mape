package by.epam.dao.base;

import by.epam.exception.DAOException;

public interface BaseDAO {

    public boolean isRightUsernameAndPassword(String username, String password) throws DAOException;
}
