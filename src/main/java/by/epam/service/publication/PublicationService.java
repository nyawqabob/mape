package by.epam.service.publication;

import by.epam.entity.Publication;
import by.epam.entity.User;
import by.epam.exception.ServiceException;
import java.util.List;

public interface PublicationService {

    public List<Publication> takeAllPublications() throws ServiceException;

    public List<Publication> takeConcretePublicationsByType(String publicationType) throws ServiceException;

    public List<Publication> takeConcretePublicationsByName(String publicationName) throws ServiceException;

    public List<Publication> takeConcreteUserPublicationsByType(String username, String publicationType) throws ServiceException;

    public List<Publication> takeConcreteUserPublicationsByName(String username, String publicationName) throws ServiceException;

    public List<Publication> takeUserPublications(String username) throws ServiceException;

    public Publication takePublicationById(int publicationId) throws ServiceException;

    public Publication takeChippiestPublications() throws ServiceException;
    
}
