package by.epam.service.publication.impl;

import by.epam.dao.DAOFactory;
import by.epam.dao.publication.impl.PublicationDAOImpl;
import by.epam.constant.DAOTypes;
import by.epam.entity.Publication;
import by.epam.exception.DAOException;
import by.epam.exception.ServiceException;
import by.epam.service.publication.PublicationService;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PublicationServiceImpl implements PublicationService {

    private static final Logger LOGGER = LogManager.getLogger(PublicationServiceImpl.class);

    @Override
    public List<Publication> takeAllPublications() throws ServiceException {
        List<Publication> publications;
        DAOFactory factory = new DAOFactory();
        PublicationDAOImpl dao = factory.getDAO(DAOTypes.PUBLICATION_DAO);
        try {
            publications = dao.takeAllPublications();
            return publications;
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException("Publications aren't avialable now", e);
        } finally {
            factory.closeConnection();
        }
    }

    @Override
    public List<Publication> takeConcretePublicationsByType(String publicationType) throws ServiceException {
        List<Publication> publications;
        DAOFactory factory = new DAOFactory();
        PublicationDAOImpl dao = factory.getDAO(DAOTypes.PUBLICATION_DAO);
        try {
            publications = dao.takeConcretePublicationsByType(publicationType);
            return publications;
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException("Publications " + publicationType + " aren't avialable now", e);
        } finally {
            factory.closeConnection();
        }
    }

    @Override
    public List<Publication> takeConcretePublicationsByName(String publicationName) throws ServiceException {
        List<Publication> publications;
        DAOFactory factory = new DAOFactory();
        PublicationDAOImpl dao = factory.getDAO(DAOTypes.PUBLICATION_DAO);
        try {
            publications = dao.takeConcretePublicationsByName(publicationName);
            return publications;
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException("Publications " + publicationName + " aren't avialable now", e);
        } finally {
            factory.closeConnection();
        }
    }

    @Override
    public List<Publication> takeConcreteUserPublicationsByType(String username, String publicationType) throws ServiceException {
        List<Publication> publications;
        DAOFactory factory = new DAOFactory();
        PublicationDAOImpl dao = factory.getDAO(DAOTypes.PUBLICATION_DAO);
        try {
            publications = dao.takeConcreteUserPublicationsByType(username, publicationType);
            return publications;
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException("User " + username + " doesn't exist", e);
        } finally {
            factory.closeConnection();
        }
    }

    @Override
    public List<Publication> takeConcreteUserPublicationsByName(String username, String publicationName) throws ServiceException {
        List<Publication> publications;
        DAOFactory factory = new DAOFactory();
        PublicationDAOImpl dao = factory.getDAO(DAOTypes.PUBLICATION_DAO);
        try {
            publications = dao.takeConcreteUserPublicationsByName(username, publicationName);
            return publications;
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException("Publication " + publicationName + " doesn't exist", e);
        } finally {
            factory.closeConnection();
        }
    }

    @Override
    public List<Publication> takeUserPublications(String username) throws ServiceException {
        DAOFactory factory = new DAOFactory();
        PublicationDAOImpl dao = factory.getDAO(DAOTypes.PUBLICATION_DAO);
        List<Publication> publications = new ArrayList<>();
        try {
            publications = dao.takeUserPublications(username);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException("User " + username + " doesn't exist", e);
        } finally {
            factory.closeConnection();
        }
        return publications;
    }

    @Override
    public Publication takePublicationById(int publicationId) throws ServiceException {
        DAOFactory factory = new DAOFactory();
        PublicationDAOImpl dao = factory.getDAO(DAOTypes.PUBLICATION_DAO);
        Publication publication = new Publication();
        try {
            publication = dao.takePublicationById(publicationId);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        } finally {
            factory.closeConnection();
        }
        return publication;
    }

    @Override
    public Publication takeChippiestPublications() throws ServiceException {
        DAOFactory factory = new DAOFactory();
        PublicationDAOImpl dao = factory.getDAO(DAOTypes.PUBLICATION_DAO);
        Publication publication = new Publication();
        try {
            publication = dao.takeChippiestPublications();
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            factory.closeConnection();
        }
        return publication;
    }
}
