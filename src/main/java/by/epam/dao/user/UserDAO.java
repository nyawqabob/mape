package by.epam.dao.user;

import by.epam.entity.User;
import by.epam.exception.DAOException;
import java.util.List;

public interface UserDAO {

    public void addPublicationToUser(int userId, int publicationId, int subscribtionPeriods) throws DAOException;

    public boolean matchPublication(int userId, int publicationId) throws DAOException;

    public List<User> takeAllUsers() throws DAOException;

    public void setUserBalance(int userId, double newBalance) throws DAOException;

    public void addPayment(int userId, double amount, String publicationName, String date, int subscribtionPeriod, String finishDate) throws DAOException;

    public void setName(String currentName, String newName) throws DAOException;

    public void setPassword(String username, String newPassword) throws DAOException;

    public void deletePublicationFromUser(int userId, int publicationId) throws DAOException;
}
