package by.epam.pagination;

import by.epam.constant.Parameters;
import by.epam.entity.Entity;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class EntityPagination<E extends Entity> {

    private final List<E> fullList;

    /**
     * Need to fill list of all entities
     *
     * @param allRecords this is all entities
     */
    public EntityPagination(List<E> allRecords) {
        this.fullList = allRecords;
    }

    /**
     * Need to take list of page numbers
     *
     * @param recordAtOnePage this is number of record at one page
     * @return list of page numbers
     */
    public List<Integer> getAmountOfPages(int recordAtOnePage) {
        int amountOfPages = fullList.size() / recordAtOnePage;
        if (fullList.size() % recordAtOnePage != 0) {
            amountOfPages++;
        }
        List<Integer> pageNumbers = new ArrayList<>();
        int number = 1;
        for (int i = 0; i < amountOfPages; i++) {
            pageNumbers.add(number);
            number++;
        }
        return pageNumbers;
    }

    /**
     * Need to take needed list of certain page
     *
     * @param numberOfPage this is number of page
     * @param recordAtOnePage this is number of entities at one pages
     * @return needed list
     */
    public List<E> getPageContent(int numberOfPage, int recordAtOnePage) {
        List<E> neededList = new ArrayList<>();
        int elementNumber = numberOfPage * recordAtOnePage - recordAtOnePage;
        if (fullList.isEmpty()) {
            return neededList;
        }
        for (int i = 0; i < recordAtOnePage; i++) {
            neededList.add(fullList.get(elementNumber));
            elementNumber++;
            if (elementNumber == fullList.size()) {
                break;
            }
        }
        return neededList;
    }
  /**
     * Need to take needed page number by choosen parameter 
     * @param request need to take parameter
     * @return number of page
     */
    public int getPageNumber(HttpServletRequest request) {
        int pageNumber;
        String param = request.getParameter(Parameters.PAGE);
        if (param != null) {
            pageNumber = Integer.parseInt(param);
        } else {
            pageNumber = 1;
        }
        return pageNumber;
    }

}
