package by.epam.command.admin;

import by.epam.command.AbstractCommand;
import by.epam.constant.Attributes;
import by.epam.constant.Pages;
import by.epam.constant.Parameters;
import by.epam.exception.ServiceException;
import by.epam.service.AdminService;
import by.epam.view.View;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SetPublicationTypeCommand extends AbstractCommand {

    @Override
    public View execute(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter(Parameters.PUBLICATION_ID));
        String newType = request.getParameter(Parameters.NEW_TYPE);
        HttpSession session = request.getSession();
        AdminService adminService = new AdminService();
        View view = new View();
        view.setViewType(View.ViewType.REDIRECT);
        try {
            adminService.setPublicationType(id, newType);
            session.setAttribute(Attributes.PUBLICATION_TYPE_SUCCESS, "You changed publication type");
               view.setPagePath(Pages.REDIRECT_PUBLICATIONS_PATH);
        } catch (ServiceException ex) {
            session.setAttribute(Attributes.PUBLICATION_TYPE_ERROR, ex.getMessage());
               view.setPagePath(Pages.REDIRECT_PUBLICATIONS_PATH);
        }
        return view;
    }
}