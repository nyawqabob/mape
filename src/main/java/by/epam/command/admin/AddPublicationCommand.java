package by.epam.command.admin;

import by.epam.command.AbstractCommand;
import by.epam.validator.AddPublicationValidator;
import by.epam.constant.Attributes;
import by.epam.constant.Constants;
import by.epam.constant.Pages;
import by.epam.constant.Parameters;
import by.epam.exception.ServiceException;
import by.epam.pool.AdminService;
import by.epam.view.View;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.commons.io.FileUtils;

public class AddPublicationCommand extends AbstractCommand {

    @Override
    public View execute(HttpServletRequest request) {
        String publicationName = request.getParameter(Parameters.PUBLICATION_NAME);
        String publicationType = request.getParameter(Parameters.PUBLICATION_TYPE);
        String publicationPeriodType = request.getParameter(Parameters.PUBLICATION_PERIOD_TYPE);
        double publicationPrice = Double.parseDouble(request.getParameter(Parameters.PUBLICATION_PRICE));
        HttpSession session = request.getSession();
        AddPublicationValidator addPublicationValidator = new AddPublicationValidator();
        View view = new View();
        view.setViewType(View.ViewType.REDIRECT);
        if (addPublicationValidator.isValid(request)) {
            try {
                AdminService adminService = new AdminService();
                adminService.addPublication(publicationName, publicationType, publicationPrice, addFileAndTakeName(request), publicationPeriodType);
                session.setAttribute(Attributes.PUBLICATION_ADD_SUCCESS, "Publication added");
                view.setPagePath(Pages.REDIRECT_PUBLICATIONS_PATH);
            } catch (ServiceException ex) {
                session.setAttribute(Attributes.PUBLICATION_ADD_ERROR, ex.getMessage());
                view.setPagePath(Pages.REDIRECT_PUBLICATIONS_PATH);
            }
        } else {
            view.setPagePath(Pages.REDIRECT_PUBLICATIONS_PATH);
        }
        return view;
    }

    private String addFileAndTakeName(HttpServletRequest request) throws ServiceException {
        String publicationImage = null;
        try {
            Part filePart;
            filePart = request.getPart(Parameters.PUBLICATION_IMAGE);
            InputStream fileContent = filePart.getInputStream();
            publicationImage = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            File file = new File(Constants.IMAGES_PATH + publicationImage);
            FileUtils.copyInputStreamToFile(fileContent, file);
        } catch (IOException | ServletException ex) {
            throw new ServiceException("Can't upload, try again later", ex);
        }
        return publicationImage;
    }

}
