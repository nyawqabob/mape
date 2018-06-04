package by.epam.validator;

import by.epam.constant.Attributes;
import by.epam.constant.Parameters;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class SetUserNameValidator implements CommonValidator {

    public boolean isValid(HttpServletRequest request) {
        String newName = request.getParameter(Parameters.NEW_LOGIN);
        List<String> messages = new ArrayList<>();
        checkOnRegExp(newName, messages, Parameters.NEW_LOGIN);
        if (!messages.isEmpty()) {
            setMessageAttribute(messages, request, Attributes.USER_NAME_ERROR);
        }

        return messages.isEmpty();
    }
}
