
package by.epam.validator;

import by.epam.constant.Attributes;
import by.epam.constant.Parameters;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class SetPublicationPriceValidator extends AbstractValidator {
     public boolean isValid(HttpServletRequest request) {
        Integer newPrice = Integer.parseInt(request.getParameter(Parameters.NEW_PRICE));
        List<String> messages = new ArrayList<>();
        checkOnValueAndLength(newPrice, messages, Parameters.NEW_PRICE);
        if (!messages.isEmpty()) {
            setMessageAttribute(messages, request, Attributes.PUBLICATION_PRICE_ERROR);
        }

        return messages.isEmpty();
    }
}
