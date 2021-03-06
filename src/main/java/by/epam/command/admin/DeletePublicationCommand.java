package by.epam.command.admin;

import by.epam.command.AbstractCommand;
import by.epam.configuration.ConfigurationPage;
import by.epam.constant.Attributes;
import by.epam.constant.Constants;
import by.epam.constant.Pages;
import by.epam.constant.Parameters;
import by.epam.entity.Publication;
import by.epam.exception.ServiceException;
import by.epam.service.admin.impl.AdminServiceImpl;
import by.epam.service.publication.impl.PublicationServiceImpl;
import by.epam.view.View;
import java.io.File;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DeletePublicationCommand extends AbstractCommand {

    @Override
    public View execute(HttpServletRequest request) {
        int publicationId = Integer.parseInt(request.getParameter(Parameters.PUBLICATION_ID));
        AdminServiceImpl adminService = new AdminServiceImpl();
        HttpSession session = request.getSession();
        PublicationServiceImpl publicationService = new PublicationServiceImpl();
        View view = new View();
        view.setViewType(View.ViewType.REDIRECT);
            try {
                Publication publication = publicationService.takePublicationById(publicationId);
                adminService.deletePublication(publicationId);
                deleteImage(publication.getImage());
                session.setAttribute(Attributes.PUBLICATION_DELETE_SUCCESS, "Publication deleted");
                view.setPagePath(Pages.REDIRECT_PUBLICATIONS_PATH);
            } catch (ServiceException ex) {
                session.setAttribute(Attributes.PUBLICATION_DELETE_ERROR, ex.getMessage());
                view.setPagePath(Pages.REDIRECT_PUBLICATIONS_PATH);
            }
        return view;
    }

    private void deleteImage(String publicationImage) {
        File file = new File(Constants.IMAGES_PATH + publicationImage);
        file.delete();
    }

}
