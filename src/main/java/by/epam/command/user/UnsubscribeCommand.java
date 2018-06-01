package by.epam.command.user;

import by.epam.command.AbstractCommand;
import by.epam.constant.Attributes;
import by.epam.constant.Constants;
import by.epam.constant.Pages;
import by.epam.constant.Parameters;
import by.epam.entity.Publication;
import by.epam.entity.User;
import by.epam.exception.ServiceException;
import by.epam.service.payment.impl.PaymentServiceImpl;
import by.epam.service.publication.impl.PublicationServiceImpl;
import by.epam.service.user.impl.UserServiceImpl;
import by.epam.view.View;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UnsubscribeCommand extends AbstractCommand {

    @Override
    public View execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserServiceImpl userService = new UserServiceImpl();
        View view = new View();
        view.setViewType(View.ViewType.REDIRECT);
        User user = (User) session.getAttribute(Attributes.USER_OBJ);
        int userId = user.getId();
        try {
            int publicationId = Integer.parseInt(request.getParameter(Parameters.PUBLICATION_ID));
            userService.deletePublicationFromUser(userId, publicationId);
            String username = user.getName();
            user = userService.takeUserByName(username);
            session.setAttribute(Attributes.USER_OBJ, user);
            session.setAttribute(Attributes.USER_UNSUBSCRIBE_SUCCESS, "Unsubscribe was successed");
            view.setPagePath(Pages.USER_PATH);

        } catch (ServiceException ex) {
            session.setAttribute(Attributes.USER_UNSUBSCRIBE_SUCCESS, ex.getMessage());
            view.setPagePath(Pages.USER_PATH);
        }

        return view;
    }

}
