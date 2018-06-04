package by.epam.validator;

import by.epam.constant.Attributes;
import by.epam.constant.Parameters;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class SetUserBalanceValidator implements CommonValidator {

    public boolean isValid(HttpServletRequest request) {
        Integer newPrice = Integer.parseInt(request.getParameter(Parameters.NEW_BALANCE));
        List<String> messages = new ArrayList<>();
        checkOnValueAndLength(newPrice, messages, Parameters.NEW_BALANCE);
        if (!messages.isEmpty()) {
            setMessageAttribute(messages, request, Attributes.USER_BALANCE_ERROR);
        }

        return messages.isEmpty();
    }
}
