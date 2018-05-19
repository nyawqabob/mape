package by.epam.dao.admin;

import by.epam.exception.DAOException;

public interface AdminDAO {

    public void setUserStatus(String username, String status) throws DAOException;

    public void addPublication(String publicationName, String publicationType, double publicationPrice, String publicationImage, String periodType) throws DAOException;

    public void deletePublication(int publicationId) throws DAOException;

    public void setSystemBalance(double newbalance) throws DAOException;

    public double takeSystemBalance() throws DAOException;

    public void setUserBalance(String username, int newAmount) throws DAOException;

    public void setPublicationType(int id, String newStatus) throws DAOException;

    public void setPublicationPrice(int id, int newPrice) throws DAOException;

    public void setPublicationName(String newName, int id) throws DAOException;

    public void setUserName(String newName, String oldName) throws DAOException;
}
