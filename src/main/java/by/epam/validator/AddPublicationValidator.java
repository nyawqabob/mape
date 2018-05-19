package by.epam.validator;

import by.epam.constant.Attributes;
import by.epam.constant.Constants;
import by.epam.constant.Parameters;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class AddPublicationValidator extends AbstractValidator {

    public boolean isValid(HttpServletRequest request) {
        String publicationName = request.getParameter(Parameters.PUBLICATION_NAME);
        String publicationPrice = request.getParameter(Parameters.PUBLICATION_PRICE);
        String publicationType = request.getParameter(Parameters.PUBLICATION_TYPE);
        List<String> messages = new ArrayList<>();
        checkOnEmptyAndLength(publicationName, messages, Parameters.PUBLICATION_NAME);
        checkOnEmptyAndLength(publicationPrice, messages, Parameters.PUBLICATION_PRICE);
        checkOnEmptyAndLength(publicationType, messages, Parameters.PUBLICATION_TYPE);
        if (!messages.isEmpty()) {
            setMessageAttribute(messages, request, Attributes.PUBLICATION_ADD_ERROR);
        }
        Integer i = 10;
        return messages.isEmpty();
    }
}
