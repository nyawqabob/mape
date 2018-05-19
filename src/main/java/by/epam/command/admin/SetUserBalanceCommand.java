package by.epam.command.admin;

import by.epam.command.AbstractCommand;
import by.epam.constant.Attributes;
import by.epam.constant.Pages;
import by.epam.constant.Parameters;
import by.epam.exception.ServiceException;
import by.epam.service.AdminService;
import by.epam.validator.SetUserBalanceValidator;
import by.epam.view.View;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SetUserBalanceCommand extends AbstractCommand {

    @Override
    public View execute(HttpServletRequest request) {
        View view = new View();
        view.setViewType(View.ViewType.REDIRECT);
        HttpSession session = request.getSession();
        int newBalance;
        String username = request.getParameter(Parameters.LOGIN);
        try {
            newBalance = Integer.parseInt(request.getParameter(Parameters.NEW_BALANCE));
        } catch (NumberFormatException ex) {
            session.setAttribute(Attributes.USER_BALANCE_ERROR, ex.getMessage());
            view.setPagePath(Pages.REDIRECT_USERS_PATH);
            return view;
        }
        AdminService adminService = new AdminService();
        SetUserBalanceValidator validator = new SetUserBalanceValidator();
        if (validator.isValid(request)) {
            try {
                adminService.setUserBalance(username, newBalance);
                session.setAttribute(Attributes.USER_BALANCE_SUCCESS, "You changed user balance");
                view.setPagePath(Pages.REDIRECT_USERS_PATH);
            } catch (ServiceException ex) {
                session.setAttribute(Attributes.USER_BALANCE_ERROR, ex.getMessage());
                view.setPagePath(Pages.REDIRECT_USERS_PATH);
            }
        } else {
            view.setPagePath(Pages.REDIRECT_USERS_PATH);
        }
        return view;
    }
}
