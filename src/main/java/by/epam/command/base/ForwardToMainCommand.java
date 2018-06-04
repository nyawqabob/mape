package by.epam.command.base;

import by.epam.command.AbstractCommand;
import by.epam.command.AbstractForwardCommand;
import by.epam.configuration.ConfigurationPage;
import by.epam.constant.Attributes;
import by.epam.constant.Pages;
import by.epam.constant.Parameters;
import by.epam.entity.Publication;
import by.epam.entity.User;
import by.epam.exception.ServiceException;
import by.epam.service.publication.impl.PublicationServiceImpl;
import by.epam.view.View;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ForwardToMainCommand extends AbstractForwardCommand {

    @Override
    public View execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        View view = new View();
        view.setViewType(View.ViewType.FORWARD);
        User user = (User) session.getAttribute(Attributes.USER_OBJ);
        view.setPagePath(Pages.MAIN_PATH);
        try {
            handlePublicationAttributes(request, 3);
            handleMainAttributes(request);
            session.setAttribute(Attributes.BALANCE, user.getBalance());
        } catch (ServiceException ex) {
            view.setPagePath(Pages.LOGIN_PATH);
        }
        return view;
    }

    private void handleMainAttributes(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();
        PublicationServiceImpl publicationService = new PublicationServiceImpl();
        session.setAttribute(Attributes.CHIPPIEST_PUBLICATION, publicationService.takeChippiestPublications());
    }

}
