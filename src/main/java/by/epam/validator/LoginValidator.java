package by.epam.validator;

import by.epam.constant.Attributes;
import by.epam.constant.Parameters;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class LoginValidator extends AbstractValidator {

    public boolean isValid(HttpServletRequest request) {
        String login = request.getParameter(Parameters.LOGIN);
        String password = request.getParameter(Parameters.PASSWORD);
        List<String> messages = new ArrayList<>();
        checkOnEmptyAndLength(login, messages, Parameters.LOGIN);
        checkOnEmptyAndLength(password, messages, Parameters.PASSWORD);
        if (!messages.isEmpty()) {
            setMessageAttribute(messages, request, Attributes.LOGIN_WRONG_DATA_ERROR);
        }

        return messages.isEmpty();
    }
}
