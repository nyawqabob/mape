package by.epam.validator;

import by.epam.constant.Attributes;
import by.epam.constant.Parameters;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class ChangePasswordValidator extends AbstractValidator {

    public boolean isValid(HttpServletRequest request) {
        String newPassword = request.getParameter(Parameters.NEW_PASSWORD);
        List<String> messages = new ArrayList<>();
        checkOnEmptyAndLength(newPassword, messages, Parameters.NEW_PASSWORD);
        if (!messages.isEmpty()) {
            setMessageAttribute(messages, request, Attributes.USER_NAME_ERROR);
        }
        return messages.isEmpty();
    }
}
