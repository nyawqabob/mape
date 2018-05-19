package by.epam.pagination;

import by.epam.constant.Parameters;
import by.epam.entity.Entity;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class EntityPagination<E extends Entity> {

    private final List<E> fullList;

    public EntityPagination(List<E> allRecords) {
        this.fullList = allRecords;
    }

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

    public List<E> getPageContent(int amountOfPages, int recordAtOnePage) {
        List<E> neededList = new ArrayList<>();
        int elementNumber = amountOfPages * recordAtOnePage - recordAtOnePage;
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
