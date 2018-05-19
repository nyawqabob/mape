package by.epam.validator;

import by.epam.constant.Constants;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public abstract class AbstractValidator {

    public void setMessageAttribute(List<String> messages, HttpServletRequest request, String messageType) {
        String commonMessage = "";
        for (String message : messages) {
            commonMessage = commonMessage + message;
        }
        HttpSession session = request.getSession();
        session.setAttribute(messageType, commonMessage);
    }

    public void checkOnEmptyAndLength(String checkedParameter, List<String> messages, String parameterName) {
        if (checkedParameter != null) {
            if (checkedParameter.isEmpty()) {
                messages.add(parameterName + " is empty; ");
            } else {
                if (checkedParameter.length() > Constants.STRING_LIMITER) {
                    messages.add(parameterName + " very long; ");
                }
            }
        } else {
            messages.add(parameterName + " is empty; ");
        }
    }

    public void checkOnValueAndLength(Integer checkedParameter, List<String> messages, String parameterName) {
        if (checkedParameter != null) {
            if (checkedParameter == 0) {
                messages.add(parameterName + " can't equals 0");
            } else {
                if (checkedParameter > Constants.INTEGER_LIMITTER) {
                    messages.add(parameterName + " very long; ");
                }
            }
        } else {
            messages.add(parameterName + " is empty; ");
        }
    }

}
