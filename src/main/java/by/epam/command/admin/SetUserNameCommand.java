package by.epam.command.admin;

import by.epam.command.AbstractCommand;
import by.epam.constant.Attributes;
import by.epam.constant.Pages;
import by.epam.constant.Parameters;
import by.epam.exception.ServiceException;
import by.epam.pool.AdminService;
import by.epam.validator.SetUserNameValidator;
import by.epam.view.View;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SetUserNameCommand extends AbstractCommand {

    @Override
    public View execute(HttpServletRequest request) {
        String oldName = request.getParameter(Parameters.LOGIN);
        String newName = request.getParameter(Parameters.NEW_LOGIN);
        HttpSession session = request.getSession();
        AdminService adminService = new AdminService();
        View view = new View();
        view.setViewType(View.ViewType.REDIRECT);
        SetUserNameValidator validator = new SetUserNameValidator();
        if (validator.isValid(request)) {
            try {
                adminService.setUserName(oldName, newName);
                session.setAttribute(Attributes.USER_NAME_SUCCESS, "You changed username");
                view.setPagePath(Pages.REDIRECT_USERS_PATH);
            } catch (ServiceException ex) {
                session.setAttribute(Attributes.USER_NAME_ERROR, ex.getMessage());
                view.setPagePath(Pages.REDIRECT_USERS_PATH);
            }
        } else {
            view.setPagePath(Pages.REDIRECT_USERS_PATH);
        }
        return view;
    }
}
