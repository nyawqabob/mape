package by.epam.command.base;

import by.epam.command.AbstractCommand;
import by.epam.command.AbstractForwardCommand;
import by.epam.constant.Attributes;
import by.epam.constant.Constants;
import by.epam.constant.Pages;
import by.epam.entity.Publication;
import by.epam.entity.User;
import by.epam.exception.ServiceException;
import by.epam.service.PublicationService;
import by.epam.view.View;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ForwardToProfileCommand extends AbstractForwardCommand {

    private final String adminPath;
    private final String userPath;

    public ForwardToProfileCommand(String path, String secondPath) {
        this.adminPath = path;
        this.userPath = secondPath;
    }

    @Override
    public View execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Attributes.USER_OBJ);
        View view = new View();
        view.setViewType(View.ViewType.FORWARD);
        try {
            if (Constants.USER_ROLE.equals(user.getRole())) {
                handleUserPageAttributes(request, userPath, user);
                view.setPagePath(userPath);
            } else {
                handleAdminPageAttributes(request, adminPath);
                view.setPagePath(adminPath);
            }
        } catch (ServiceException ex) {
            view.setPagePath(Pages.LOGIN_PATH);
        }
        return view;
    }
}
