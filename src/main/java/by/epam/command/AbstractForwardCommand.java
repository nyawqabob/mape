package by.epam.command;

import by.epam.constant.Attributes;
import by.epam.constant.Constants;
import by.epam.constant.Parameters;
import by.epam.entity.Payment;
import by.epam.entity.Publication;
import by.epam.entity.User;
import by.epam.exception.ServiceException;
import by.epam.service.admin.impl.AdminServiceImpl;
import by.epam.service.payment.impl.PaymentServiceImpl;
import by.epam.service.publication.impl.PublicationServiceImpl;
import by.epam.service.user.impl.UserServiceImpl;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public abstract class AbstractForwardCommand extends AbstractCommand {

    private static final String USER_MAIN = "user";
    private static final String USER_PAYMENTS = "payments";
    private static final String ADMIN_MAIN = "admin";
    private static final String ADMIN_PUBLICIATIONS = "publications";
    private static final String ADMIN_USERS = "users";
    private static String helpType = "all";

    /**
     * Method need to choose user page
     *
     * @param request need to take parameters and session
     * @param userPageType type of user page
     * @param user need to take user id
     * @throws ServiceException
     */
    public void handleUserPageAttributes(HttpServletRequest request, String userPageType, User user) throws ServiceException {
        switch (userPageType) {
            case USER_MAIN:
                handleUserMainPage(user, request);
                break;
            case USER_PAYMENTS:
                handleUserPaymentsPage(user, request);
        }
    }

    /**
     * Method need to choose admin page
     *
     * @param request need to take parameters and session
     * @param adminPageType type of admin page
     * @param user need to take user id
     * @throws ServiceException
     */
    public void handleAdminPageAttributes(HttpServletRequest request, String adminPageType) throws ServiceException {
        switch (adminPageType) {
            case ADMIN_MAIN:
                handleAdminMainAttributes(request);
                break;
            case ADMIN_PUBLICIATIONS:
                handlePublicationAttributes(request, 5);
                break;
            case ADMIN_USERS:
                handleAdminUsersAttributes(request);
                break;
        }
    }

    private void handleAdminMainAttributes(HttpServletRequest request) throws ServiceException {
        String userName = request.getParameter(Parameters.FIND_PAYMENT_NAME);
        HttpSession session = request.getSession();
        PaymentServiceImpl paymentService = new PaymentServiceImpl();
        AdminServiceImpl adminService = new AdminServiceImpl();
        UserServiceImpl userService = new UserServiceImpl();
        User user = userService.takeUserByName(userName);
        double systemBalance = adminService.takeSystemBalance();
        List<Payment> payments;
        if (!(userName == null || "".equals(userName))) {
            payments = paymentService.takeUserPayments(user.getId());
        } else {
            payments = paymentService.takeAllPayments();
        }
        session.setAttribute(Attributes.SYSTEM_BALANCE, systemBalance);
        session.setAttribute(Attributes.ACCOUNT_ROLE, Constants.ADMIN_ROLE);
        session.setAttribute(Attributes.ALL_PAYMENTS, payments);
    }

    protected void handlePublicationAttributes(HttpServletRequest request, int amountOfPages) throws ServiceException {
        String type = request.getParameter(Parameters.TYPE);
        String publicationName = request.getParameter(Parameters.FIND_PUBLICATION_NAME);
        HttpSession session = request.getSession();
        PublicationServiceImpl publicationService = new PublicationServiceImpl();
        List<Publication> attributePublications = (List<Publication>) session.getAttribute(Attributes.PUBLICATIONS);
        List<Publication> publications;
        List<Publication> neededPublications;
        if (!(type == null || "".equals(type))) {
            if ("all".equals(type)) {
                helpType = "all";
                publications = publicationService.takeAllPublications();
                neededPublications = takeNeededList(publications, request, amountOfPages);
                session.setAttribute(Attributes.PUBLICATIONS, neededPublications);
                return;
            }
            publications = publicationService.takeConcretePublicationsByType(type);
            helpType = type;
        } else {
            if (!(publicationName == null || "".equals(publicationName))) {
                publications = publicationService.takeConcretePublicationsByName(publicationName);
            } else {
                if (attributePublications == null) {
                    publications = publicationService.takeAllPublications();
                } else {
                    if ("all".equals(helpType)) {
                        publications = publicationService.takeAllPublications();
                    } else {
                        publications = publicationService.takeConcretePublicationsByType(helpType);
                    }
                }
            }
        }
        neededPublications = takeNeededList(publications, request, amountOfPages);

        session.setAttribute(Attributes.PUBLICATIONS, neededPublications);
    }

    private void handleAdminUsersAttributes(HttpServletRequest request) throws ServiceException {
        String userName = request.getParameter(Parameters.FIND_USER_NAME);
        HttpSession session = request.getSession();
        UserServiceImpl userService = new UserServiceImpl();
        List<User> users = new ArrayList<>();
        if (!(userName == null || "".equals(userName))) {
            User user = userService.takeUserByName(userName);
            if (!(user.getName() == null)) {
                users.add(user);
            }
        } else {
            users = userService.takeUsers();
        }
        List<User> neededUsers = takeNeededList(users, request, 5);
        session.setAttribute(Attributes.ALL_USERS, neededUsers);
    }

    private void handleUserMainPage(User user, HttpServletRequest request) throws ServiceException {
        String type = request.getParameter(Parameters.TYPE);
        String publicationName = request.getParameter(Parameters.FIND_PUBLICATION_NAME);
        HttpSession session = request.getSession();
        String username = user.getName();
        PublicationServiceImpl publicationService = new PublicationServiceImpl();
        List<Publication> publications;
        if (!(type == null || "".equals(type))) {
            publications = publicationService.takeConcreteUserPublicationsByType(username, type);
        } else {
            if (!(publicationName == null || "".equals(publicationName))) {
                publications = publicationService.takeConcreteUserPublicationsByName(username, publicationName);
            } else {
                publications = publicationService.takeUserPublications(username);
            }
        }
        List<Publication> neededPublications = takeNeededList(publications, request, 3);
        session.setAttribute(Attributes.BALANCE, user.getBalance());
        session.setAttribute(Attributes.USER_PUBLICATIONS, neededPublications);
        session.setAttribute(Attributes.ACCOUNT_ROLE, Constants.USER_ROLE);
        session.setAttribute(Attributes.USER, username);
    }

    private void handleUserPaymentsPage(User user, HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();
        PaymentServiceImpl paymentService = new PaymentServiceImpl();
        int userId = user.getId();
        List<Payment> payments = paymentService.takeUserPayments(userId);
        session.setAttribute(Attributes.USER_PAYMENTS, payments);

    }
}
