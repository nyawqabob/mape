package by.epam.command.admin;

import by.epam.command.AbstractCommand;
import by.epam.configuration.ConfigurationPage;
import by.epam.constant.Attributes;
import by.epam.constant.Constants;
import by.epam.constant.Pages;
import by.epam.constant.Parameters;
import by.epam.entity.User;
import by.epam.exception.ServiceException;
import by.epam.service.AdminService;
import by.epam.service.UserService;
import by.epam.view.View;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UnbanUserCommand extends AbstractCommand {

    @Override
    public View execute(HttpServletRequest request) {
        String login = request.getParameter(Parameters.LOGIN);
        HttpSession session = request.getSession();
        AdminService adminService = new AdminService();
        View view = new View();
        view.setViewType(View.ViewType.REDIRECT);
            try {
                adminService.setUserStatus(login, Constants.NOT_BANNED_STATUS);
                session.setAttribute(Attributes.USER_UNBAN_SUCCESS, "User unbanned");
                view.setPagePath(Pages.REDIRECT_USERS_PATH);
            } catch (ServiceException ex) {
                session.setAttribute(Attributes.USER_UNBAN_ERROR, ex.getMessage());
                view.setPagePath(Pages.REDIRECT_USERS_PATH);
            }
        return view;
    }

}
