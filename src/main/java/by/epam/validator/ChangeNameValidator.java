package by.epam.validator;

import by.epam.constant.Attributes;
import by.epam.constant.Constants;
import by.epam.constant.Parameters;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class ChangeNameValidator extends AbstractValidator {

    public boolean isValid(HttpServletRequest request) {
        String newLogin = request.getParameter(Parameters.NEW_LOGIN);
        List<String> messages = new ArrayList<>();
        checkOnEmptyAndLength(newLogin, messages, Parameters.NEW_LOGIN);
        if (!messages.isEmpty()) {
            setMessageAttribute(messages, request, Attributes.USER_NAME_ERROR);
        }
        return messages.isEmpty();
    }
}
