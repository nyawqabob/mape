package by.epam.command.base;

import by.epam.command.AbstractCommand;
import by.epam.constant.Attributes;
import by.epam.constant.Pages;
import by.epam.view.View;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangeLanguageCommand extends AbstractCommand {

    @Override
    public View execute(HttpServletRequest request) {
        String lang = request.getParameter(Attributes.LANGUAGE);
        HttpSession session = request.getSession();
        session.setAttribute(Attributes.LANGUAGE, lang);
        View view = new View();
        String header = request.getHeader("referer");
        view.setViewType(View.ViewType.REDIRECT);
        takeNeededHeaderAndSetView(header, view);

        return view;
    }

    private void takeNeededHeaderAndSetView(String header, View view) {
        String neededHeader;
        if (header.contains("?")) {
            int numberOfQuestion = header.indexOf("?");
            neededHeader = header.substring(38, numberOfQuestion);
            view.setPagePath(neededHeader);
            return;
        }
        try {
            neededHeader = header.substring(38);
        } catch (StringIndexOutOfBoundsException ex) {
            view.setPagePath(Pages.LOGIN_PATH);
            return;
        }
        view.setPagePath(neededHeader);
    }

}
