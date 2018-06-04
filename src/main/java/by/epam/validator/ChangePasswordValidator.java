package by.epam.validator;

import by.epam.constant.Attributes;
import by.epam.constant.Parameters;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class ChangePasswordValidator implements CommonValidator {

    public boolean isValid(HttpServletRequest request) {
        String newPassword = request.getParameter(Parameters.NEW_PASSWORD);
        String password = request.getParameter(Parameters.PASSWORD);
        String login = request.getParameter(Parameters.LOGIN);
        List<String> messages = new ArrayList<>();
        checkOnRegExp(newPassword, messages, Parameters.NEW_PASSWORD);
        checkOnRegExp(password, messages, Parameters.PASSWORD);
        checkOnRegExp(login, messages, Parameters.LOGIN);
        if (!messages.isEmpty()) {
            setMessageAttribute(messages, request, Attributes.USER_NAME_ERROR);
        }
        return messages.isEmpty();
    }
}
