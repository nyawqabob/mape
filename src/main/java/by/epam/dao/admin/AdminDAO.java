package by.epam.dao.admin;

import by.epam.exception.DAOException;

public interface AdminDAO {

    public void changeUserStatus(String username, String status) throws DAOException;

    public void addPublication(String publicationName, String publicationType, double publicationPrice, String publicationImage, String periodType) throws DAOException;

    public void deletePublication(int publicationId) throws DAOException;

    public void changeSystemBalance(double newbalance) throws DAOException;

    public double takeSystemBalance() throws DAOException;

    public void changeUserBalance(String username, int newAmount) throws DAOException;

    public void changePublicationType(int id, String newStatus) throws DAOException;

    public void changePublicationPrice(int id, int newPrice) throws DAOException;

    public void changePublicationName(String newName, int id) throws DAOException;

    public void changeUserName(String newName, String oldName) throws DAOException;
}
