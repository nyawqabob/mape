package by.epam.command.base;

import by.epam.command.AbstractCommand;
import by.epam.constant.Pages;
import by.epam.view.View;
import javax.servlet.http.HttpServletRequest;

public class EmptyCommand extends AbstractCommand {

    @Override
    public View execute(HttpServletRequest request) {
        View view = new View();
        view.setViewType(View.ViewType.FORWARD);
        view.setPagePath(Pages.LOGIN_PATH);
        return view;

    }

}
