package by.epam.validator;

import by.epam.constant.Attributes;
import by.epam.constant.Parameters;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class AddPublicationValidator implements CommonValidator {

    public boolean isValid(HttpServletRequest request) {
        String publicationName = request.getParameter(Parameters.PUBLICATION_NAME);
        String publicationPrice = request.getParameter(Parameters.PUBLICATION_PRICE);
        List<String> messages = new ArrayList<>();
        checkOnRegExp(publicationName, messages, Parameters.PUBLICATION_NAME);
        checkOnRegExp(publicationPrice, messages, Parameters.PUBLICATION_PRICE);
        if (!messages.isEmpty()) {
            setMessageAttribute(messages, request, Attributes.PUBLICATION_ADD_ERROR);
        }
        return messages.isEmpty();
    }
}
