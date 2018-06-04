package by.epam.validator;

import by.epam.constant.Attributes;
import by.epam.constant.Parameters;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class ChangeNameValidator implements CommonValidator {

    public boolean isValid(HttpServletRequest request) {
        String newLogin = request.getParameter(Parameters.NEW_LOGIN);
        String oldLogin = request.getParameter(Parameters.OLD_LOGIN);
        String password = request.getParameter(Parameters.PASSWORD);
        List<String> messages = new ArrayList<>();
        checkOnRegExp(newLogin, messages, Parameters.NEW_LOGIN);
        checkOnRegExp(oldLogin, messages, Parameters.OLD_LOGIN);
        checkOnRegExp(password, messages, Parameters.PASSWORD);
        if (!messages.isEmpty()) {
            setMessageAttribute(messages, request, Attributes.USER_NAME_ERROR);
        }
        return messages.isEmpty();
    }
}
