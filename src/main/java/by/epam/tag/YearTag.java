package by.epam.tag;

import java.io.IOException;
import java.util.Calendar;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class YearTag extends TagSupport {

    @Override
    public int doStartTag() throws JspException {

        try {
            JspWriter out = pageContext.getOut();
            Integer year = Calendar.getInstance().get(Calendar.YEAR);
            String yearString = year+"";
            out.write(yearString);
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }
}
