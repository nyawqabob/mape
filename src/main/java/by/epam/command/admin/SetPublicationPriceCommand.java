package by.epam.command.admin;

import by.epam.command.AbstractCommand;
import by.epam.constant.Attributes;
import by.epam.constant.Pages;
import by.epam.constant.Parameters;
import by.epam.exception.ServiceException;
import by.epam.pool.AdminService;
import by.epam.validator.SetPublicationPriceValidator;
import by.epam.view.View;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SetPublicationPriceCommand extends AbstractCommand {

    @Override
    public View execute(HttpServletRequest request) {
        int newPrice;
        View view = new View();
        view.setViewType(View.ViewType.REDIRECT);
        HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter(Parameters.PUBLICATION_ID));
        try {
            newPrice = Integer.parseInt(request.getParameter(Parameters.NEW_PRICE));
        } catch (NumberFormatException ex) {
            session.setAttribute(Attributes.PUBLICATION_PRICE_ERROR, ex.getMessage());
            view.setPagePath(Pages.REDIRECT_PUBLICATIONS_PATH);
            return view;
        }
        AdminService adminService = new AdminService();
        SetPublicationPriceValidator validator = new SetPublicationPriceValidator();

        if (validator.isValid(request)) {
            try {
                adminService.setPublicationPrice(id, newPrice);
                session.setAttribute(Attributes.PUBLICATION_PRICE_SUCCESS, "You changed publication price");
                view.setPagePath(Pages.REDIRECT_PUBLICATIONS_PATH);
            } catch (ServiceException ex) {
                session.setAttribute(Attributes.PUBLICATION_PRICE_ERROR, ex.getMessage());
                view.setPagePath(Pages.REDIRECT_PUBLICATIONS_PATH);
            }
        } else {
            view.setPagePath(Pages.REDIRECT_PUBLICATIONS_PATH);
        }
        return view;
    }
}
