package by.epam.command.admin;

import by.epam.command.AbstractCommand;
import by.epam.constant.Attributes;
import by.epam.constant.Pages;
import by.epam.constant.Parameters;
import by.epam.exception.ServiceException;
import by.epam.service.AdminService;
import by.epam.validator.SetPublicationNameValidator;
import by.epam.view.View;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SetPublicationNameCommand extends AbstractCommand {

    @Override
    public View execute(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter(Parameters.PUBLICATION_ID));
        String newName = request.getParameter(Parameters.NEW_PUBLICATION_NAME);
        HttpSession session = request.getSession();
        AdminService adminService = new AdminService();
        SetPublicationNameValidator validator = new SetPublicationNameValidator();
        View view = new View();
        view.setViewType(View.ViewType.REDIRECT);
        if (validator.isValid(request)) {
            try {
                adminService.setPublicationName(newName, id);
                session.setAttribute(Attributes.PUBLICATION_NAME_SUCCESS, "You changed publication name");
                view.setPagePath(Pages.REDIRECT_PUBLICATIONS_PATH);
            } catch (ServiceException ex) {
                session.setAttribute(Attributes.PUBLICATION_NAME_ERROR, ex.getMessage());
                view.setPagePath(Pages.REDIRECT_PUBLICATIONS_PATH);
            }
        } else {
            view.setPagePath(Pages.REDIRECT_PUBLICATIONS_PATH);
        }
        return view;
    }
}
