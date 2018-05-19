package by.epam.command.admin;

import by.epam.command.AbstractCommand;
import by.epam.constant.Attributes;
import by.epam.constant.Constants;
import by.epam.constant.Pages;
import by.epam.constant.Parameters;
import by.epam.exception.ServiceException;
import by.epam.service.AdminService;
import by.epam.view.View;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class BanUserCommand extends AbstractCommand {

    @Override
    public View execute(HttpServletRequest request) {
        String username = request.getParameter(Parameters.LOGIN);
        HttpSession session = request.getSession();
        AdminService adminService = new AdminService();
        View view = new View();
        view.setViewType(View.ViewType.REDIRECT);
            try {
                adminService.setUserStatus(username, Constants.BANNED_STATUS);
                session.setAttribute(Attributes.USER_SUCCESS_BANNED, "User banned");
                view.setPagePath(Pages.REDIRECT_USERS_PATH);
            } catch (ServiceException ex) {
                session.setAttribute(Attributes.USER_ERROR_BANNED, ex.getMessage());
                view.setPagePath(Pages.REDIRECT_USERS_PATH);
            }
        return view;
    }

}
