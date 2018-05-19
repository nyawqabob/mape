package by.epam.command;

import by.epam.constant.Attributes;
import by.epam.entity.Entity;
import by.epam.pagination.EntityPagination;
import by.epam.view.View;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * Class realise Command pattern used in controller servlet
 * @author User
 */
public abstract class AbstractCommand {

    /**
     * Use to realise command pattern
     * @param request
     * @return View 
     */
    public abstract View execute(HttpServletRequest request);

    /**
     * Take entity list of needed page
     * @param <T> T extends Entity
     * @param fullList this is full list of entities
     * @param request allow to take number of needed page
     * @param pages amount of all pagination pages
     * @return needed list
     */
    public <T extends Entity> List<T> takeNeededList(List<T> fullList, HttpServletRequest request, int pages) {
        EntityPagination<T> paginator = new EntityPagination(fullList);
        int pageNumber = paginator.getPageNumber(request);
        List<T> neededList = paginator.getPageContent(pageNumber, pages);
        List<Integer> pagesList = paginator.getAmountOfPages(pages);
        if (!(pagesList.size() <= 1)) {
            request.setAttribute(Attributes.PAGES, pagesList);
        }
        return neededList;

    }
}
