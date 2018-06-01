package by.epam.command.base;

import by.epam.command.AbstractCommand;
import by.epam.constant.Attributes;
import by.epam.constant.Pages;
import by.epam.constant.Parameters;
import by.epam.entity.User;
import by.epam.exception.ServiceException;
import by.epam.handler.PasswordHandler;
import by.epam.mail.MailSender;
import by.epam.service.user.impl.UserServiceImpl;
import by.epam.view.View;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.RandomStringUtils;

public class RestorePasswordCommand extends AbstractCommand {

    
    @Override
    public View execute(HttpServletRequest request) {
        String email = request.getParameter(Parameters.EMAIL);
        HttpSession session = request.getSession();
        UserServiceImpl userService = new UserServiceImpl();
        View view = new View();
        view.setViewType(View.ViewType.REDIRECT);
        try {
            if (userService.isUserExist(email)) {
                User user = userService.takeUserByEmail(email);
                String username = user.getName();
                String generatedPassword = genereateNewPassword();
                String hashedPassword = PasswordHandler.getHashedPassword(generatedPassword);
                userService.setPassword(username, hashedPassword);
                MailSender mailSender = new MailSender();
                mailSender.sendNewPassword(email, username, generatedPassword);
                session.setAttribute(Attributes.LOGIN_RESTORE_SUCCESS, "Check your mail");
                view.setPagePath(Pages.LOGIN_PATH);
            } else {
                session.setAttribute(Attributes.LOGIN_RESTORE_ERROR, "User with this mail doesn't exist");
                view.setPagePath(Pages.LOGIN_PATH);
            }

        } catch (ServiceException ex) {
            session.setAttribute(Attributes.USER_PASSWORD_ERROR, ex.getMessage());
            view.setPagePath(Pages.LOGIN_PATH);
        }
        return view;
    }

    

    private String genereateNewPassword() {
        String generatedPassword = RandomStringUtils.randomAlphabetic(20);
        return generatedPassword;
    }
}
