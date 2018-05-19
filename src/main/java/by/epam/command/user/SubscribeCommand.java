package by.epam.command.user;

import by.epam.command.AbstractCommand;
import by.epam.constant.Attributes;
import by.epam.constant.Constants;
import by.epam.constant.Pages;
import by.epam.constant.Parameters;
import by.epam.entity.Publication;
import by.epam.entity.User;
import by.epam.exception.ServiceException;
import by.epam.service.PaymentService;
import by.epam.service.PublicationService;
import by.epam.service.UserService;
import by.epam.view.View;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SubscribeCommand extends AbstractCommand {

    @Override
    public View execute(HttpServletRequest request) {
        int subscribtionPeriod;
        HttpSession session = request.getSession();
        UserService userService = new UserService();
        View view = new View();
        view.setViewType(View.ViewType.REDIRECT);
        try {
            subscribtionPeriod = Integer.parseInt(request.getParameter(Parameters.SUBSCRIBTION_PERIODS));
        } catch (NumberFormatException ex) {
            session.setAttribute(Attributes.MAIN_ERROR, "Wrong subscribtion periods");
            view.setPagePath(Pages.MAIN_PATH);
            return view;
        }
        PublicationService publicationService = new PublicationService();
        User user = (User) session.getAttribute(Attributes.USER_OBJ);
        try {
            if (Constants.USER_ROLE.equals(user.getRole())) {
                int publicationId = Integer.parseInt(request.getParameter(Parameters.PUBLICATION_ID));
                Publication publication = publicationService.takePublicationById(publicationId);
                userService.addPublication(user, publication, subscribtionPeriod);
                String username = user.getName();
                user = userService.takeUserByName(username);
                session.setAttribute(Attributes.USER_OBJ, user);
                session.setAttribute(Attributes.MAIN_SUCCESS, "Subscribe was successed");
                view.setPagePath(Pages.MAIN_PATH);
            } else {
                session.setAttribute(Attributes.MAIN_ERROR, "Subscribe wasn't confirmed, you are admin");
                view.setPagePath(Pages.MAIN_PATH);
            }
        } catch (ServiceException ex) {
            session.setAttribute(Attributes.MAIN_ERROR, ex.getMessage());
            view.setPagePath(Pages.MAIN_PATH);
        }

        return view;
    }

}
