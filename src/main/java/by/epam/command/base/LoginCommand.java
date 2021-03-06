package by.epam.command.base;

import by.epam.command.AbstractCommand;
import by.epam.constant.Attributes;
import by.epam.constant.Constants;
import by.epam.constant.Pages;
import by.epam.constant.Parameters;
import by.epam.entity.User;
import by.epam.exception.ServiceException;
import by.epam.handler.PasswordHandler;
import by.epam.service.admin.impl.AdminServiceImpl;
import by.epam.service.base.impl.BaseServiceImpl;
import by.epam.service.publication.impl.PublicationServiceImpl;
import by.epam.service.user.impl.UserServiceImpl;
import by.epam.view.View;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import by.epam.validator.LoginValidator;

public class LoginCommand extends AbstractCommand {

    @Override
    public View execute(HttpServletRequest request) {
        String login = request.getParameter(Parameters.LOGIN);
        String pass = request.getParameter(Parameters.PASSWORD);
        HttpSession session = request.getSession();
        View view = new View();
        view.setViewType(View.ViewType.REDIRECT);
        LoginValidator loginValidator = new LoginValidator();
        if (loginValidator.isValid(request)) {
            try {
                String hashedPass = PasswordHandler.getHashedPassword(pass);
                BaseServiceImpl baseService = new BaseServiceImpl();
                UserServiceImpl userService = new UserServiceImpl();
                baseService.checkNamePassword(login, hashedPass);
                User user = userService.takeUserByName(login);
                session.setAttribute(Attributes.USER_OBJ, user);
                if (Constants.NOT_BANNED_STATUS.equals(user.getStatus())) {
                    session.setAttribute(Attributes.IS_LOGIN, "true");
                    session.setAttribute(Attributes.USER, user.getName());
                    if (Constants.ADMIN_ROLE.equals(user.getRole())) {
                        view.setPagePath(Pages.ADMIN_PATH);
                    } else {
                        view.setPagePath(Pages.USER_PATH);
                    }
                } else {
                    session.setAttribute(Attributes.LOGIN_BANNED_ERROR, "Account " + login + " was banned");
                    view.setPagePath(Pages.LOGIN_PATH);
                }
            } catch (ServiceException ex) {
                session.setAttribute(Attributes.LOGIN_WRONG_DATA_ERROR, ex.getMessage());
                view.setPagePath(Pages.LOGIN_PATH);
            }
        } else {
            view.setPagePath(Pages.LOGIN_PATH);
        }
        return view;
    }

}
