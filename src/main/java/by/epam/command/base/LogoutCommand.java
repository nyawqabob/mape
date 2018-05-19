package by.epam.command.base;

import by.epam.command.AbstractCommand;
import by.epam.constant.Attributes;
import by.epam.constant.Pages;
import by.epam.view.View;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutCommand extends AbstractCommand {

    @Override
    public View execute(HttpServletRequest request) {
        View view = new View();
        view.setViewType(View.ViewType.REDIRECT);
        view.setPagePath(Pages.LOGIN_PATH);
        HttpSession session = request.getSession();
        session.setAttribute(Attributes.IS_LOGIN, "false");
        session.removeAttribute(Attributes.ACCOUNT_ROLE);
        session.removeAttribute(Attributes.USER_OBJ);
        session.invalidate();
        return view;
    }

}
