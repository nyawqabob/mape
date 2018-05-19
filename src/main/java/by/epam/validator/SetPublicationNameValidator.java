package by.epam.validator;

import by.epam.constant.Attributes;
import by.epam.constant.Parameters;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class SetPublicationNameValidator extends AbstractValidator  {

    public boolean isValid(HttpServletRequest request) {
        String newName = request.getParameter(Parameters.NEW_PUBLICATION_NAME);
        List<String> messages = new ArrayList<>();
        checkOnEmptyAndLength(newName, messages, Parameters.NEW_PUBLICATION_NAME);
        if (!messages.isEmpty()) {
            setMessageAttribute(messages, request, Attributes.PUBLICATION_NAME_ERROR);
        }

        return messages.isEmpty();
    }
}
