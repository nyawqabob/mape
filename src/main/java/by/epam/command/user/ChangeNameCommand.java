package by.epam.command.user;

import by.epam.command.AbstractCommand;
import by.epam.configuration.ConfigurationPage;
import by.epam.constant.Attributes;
import by.epam.constant.Pages;
import by.epam.constant.Parameters;
import by.epam.entity.User;
import by.epam.exception.ServiceException;
import by.epam.handler.PasswordHandler;
import by.epam.service.base.impl.BaseServiceImpl;
import by.epam.service.user.impl.UserServiceImpl;
import by.epam.view.View;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import by.epam.validator.ChangeNameValidator;

public class ChangeNameCommand extends AbstractCommand {

    @Override
    public View execute(HttpServletRequest request) {
        String oldName = request.getParameter(Parameters.OLD_LOGIN);
        String pass = request.getParameter(Parameters.PASSWORD);
        HttpSession session = request.getSession();
        String newName = request.getParameter(Parameters.NEW_LOGIN);
        UserServiceImpl userService = new UserServiceImpl();
        BaseServiceImpl baseService = new BaseServiceImpl();
        View view = new View();
        view.setViewType(View.ViewType.REDIRECT);
        ChangeNameValidator changeNameValidator = new ChangeNameValidator();
        if (changeNameValidator.isValid(request)) {
            try {
                String hashedPass = PasswordHandler.getHashedPassword(pass);
                baseService.checkNamePassword(oldName, hashedPass);
                userService.setName(oldName, newName);
                User user = userService.takeUserByName(newName);
                session.setAttribute(Attributes.USER_OBJ, user);
                session.setAttribute(Attributes.USER_NAME_SUCCESS, "Name was changed");
                view.setPagePath(Pages.USER_PATH);
            } catch (ServiceException ex) {
                session.setAttribute(Attributes.USER_NAME_ERROR, ex.getMessage());
                view.setPagePath(Pages.USER_PATH);
            }
        } else {
            view.setPagePath(Pages.USER_PATH);
        }
        return view;
    }

}
