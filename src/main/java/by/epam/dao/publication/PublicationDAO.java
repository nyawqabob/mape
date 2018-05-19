package by.epam.dao.publication;

import by.epam.entity.Publication;
import by.epam.exception.DAOException;
import java.util.List;

public interface PublicationDAO {

    public Publication takePublicationById(int publicationId) throws DAOException;

    public List<Publication> takeAllPublications() throws DAOException;

    public Publication takeChippiestPublications() throws DAOException;

    public void setPublicationImage(int publicationId, String newImage) throws DAOException;

    public List<Publication> takeConcretePublicationsByType(String publicationType) throws DAOException;

    public List<Publication> takeUserPublications(String username) throws DAOException;

    public List<Publication> takeConcreteUserPublicationsByType(String username, String publicationType) throws DAOException;

    public List<Publication> takeConcreteUserPublicationsByName(String username, String publicationName) throws DAOException;

    public List<Publication> takeConcretePublicationsByName(String publicationName) throws DAOException;
}
