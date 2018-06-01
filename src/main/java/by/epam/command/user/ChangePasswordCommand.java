package by.epam.command.user;

import by.epam.command.AbstractCommand;
import by.epam.constant.Attributes;
import by.epam.constant.Pages;
import by.epam.constant.Parameters;
import by.epam.entity.User;
import by.epam.exception.ServiceException;
import by.epam.handler.PasswordHandler;
import by.epam.service.base.impl.BaseServiceImpl;
import by.epam.service.user.impl.UserServiceImpl;
import by.epam.validator.ChangePasswordValidator;
import by.epam.view.View;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangePasswordCommand extends AbstractCommand {

    @Override
    public View execute(HttpServletRequest request) {
        String username = request.getParameter(Parameters.LOGIN);
        String pass = request.getParameter(Parameters.PASSWORD);
        HttpSession session = request.getSession();
        String newPassword = request.getParameter(Parameters.NEW_PASSWORD);
        UserServiceImpl userService = new UserServiceImpl();
        BaseServiceImpl baseService = new BaseServiceImpl();
        View view = new View();
        view.setViewType(View.ViewType.REDIRECT);
        ChangePasswordValidator changePasswordValidator = new ChangePasswordValidator();
        if (changePasswordValidator.isValid(request)) {
            try {
                String hashedPass = PasswordHandler.getHashedPassword(pass);
                String hashedNewPass = PasswordHandler.getHashedPassword(newPassword);
                baseService.checkNamePassword(username, hashedPass);
                userService.setPassword(username, hashedNewPass);
                User user = userService.takeUserByName(username);
                session.setAttribute(Attributes.USER_OBJ, user);
                session.setAttribute(Attributes.USER_PASSWORD_SUCCESS, "Password was changed");
                view.setPagePath(Pages.USER_PATH);
            } catch (ServiceException ex) {
                session.setAttribute(Attributes.USER_PASSWORD_ERROR, ex.getMessage());
                view.setPagePath(Pages.USER_PATH);
            }
        } else {
            view.setPagePath(Pages.USER_PATH);
        }
        return view;
    }
}
