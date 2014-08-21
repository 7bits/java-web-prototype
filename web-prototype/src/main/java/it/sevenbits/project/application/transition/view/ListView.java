package it.sevenbits.project.application.transition.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * POJO for representation of given list of any Class
 * @param <Object> class of list
 */
public class ListView<Object extends Serializable> extends AbstractView {

    /** Unique generated serial id */
    private static final long serialVersionUID = -1554982557863629759L;
    /** List of elements */
    private List<Object> elements;
    /** Number of pages */
    private Integer pagesNumber;

    /** Default constructor */
    public ListView() {
        elements = new ArrayList<>();
        pagesNumber = 0;
    }

    /** Returns list of elements */
    public List<Object> getElements() {
        return elements;
    }

    /** Sets list of elements */
    public void setElements(final List<Object> elements) {
        this.elements = elements;
    }

    /** Returns number of pages */
    public Integer getPagesNumber() {
        return pagesNumber;
    }

    /** Sets number of pages */
    public void setPagesNumber(final Integer pagesNumber) {
        this.pagesNumber = pagesNumber;
    }
}
