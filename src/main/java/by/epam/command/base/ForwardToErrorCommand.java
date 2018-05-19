package by.epam.command.base;

import by.epam.command.AbstractCommand;
import by.epam.constant.Pages;
import by.epam.view.View;
import javax.servlet.http.HttpServletRequest;

public class ForwardToErrorCommand extends AbstractCommand {

    @Override
    public View execute(HttpServletRequest request) {
        View view = new View();
        String header = request.getHeader("referer");
        view.setViewType(View.ViewType.FORWARD);
        takeNeededHeaderAndSetView(header, view);
        return view;
    }

    private void takeNeededHeaderAndSetView(String header, View view) {
        String neededHeader;
        try {
            neededHeader = header.substring(45);
        } catch (StringIndexOutOfBoundsException ex) {
            view.setPagePath(Pages.LOGIN_PATH);
            return;
        }
        view.setPagePath(neededHeader);
    }
}
