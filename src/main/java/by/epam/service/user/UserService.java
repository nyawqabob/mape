package by.epam.service.user;

import by.epam.entity.Publication;
import by.epam.entity.User;
import by.epam.exception.ServiceException;
import java.util.List;

public interface UserService {

    public void addPublication(User user, Publication publication, int subscribtionPeriod) throws ServiceException;

    public void setName(String currentName, String newName) throws ServiceException;

    public void setPassword(String username, String newpassword) throws ServiceException;

    public void deletePublicationFromUser(int userId, int publicationId) throws ServiceException;

    public List<User> takeUsers() throws ServiceException;

    public boolean isUserExist(String email) throws ServiceException;

    public User takeUserByName(String username) throws ServiceException;
    
    public User takeUserByEmail(String email) throws ServiceException;

}
