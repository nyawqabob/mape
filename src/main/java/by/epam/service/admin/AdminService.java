package by.epam.service.admin;

import by.epam.exception.ServiceException;

public interface AdminService {

    public void addPublication(String publicationName, String publicationType, double publicationPrice, String publicationImage, String periodType) throws ServiceException;

    public void setUserStatus(String username, String status) throws ServiceException;

    public void deletePublication(int publicationId) throws ServiceException;

    public double takeSystemBalance() throws ServiceException;

    public void setUserName(String newName, String oldName) throws ServiceException;

    public void setPublicationName(String newName, int id) throws ServiceException;

    public void setPublicationType(int id, String newType) throws ServiceException;

    public void setPublicationPrice(int id, int newPrice) throws ServiceException;

    public void setUserBalance(String username, int newBalance) throws ServiceException;

}
