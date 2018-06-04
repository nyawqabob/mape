package by.epam.tag;

import java.io.IOException;
import java.util.Calendar;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class YearTag extends TagSupport {

    private static final Logger LOGGER = LogManager.getLogger(YearTag.class);

    @Override
    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            Integer year = Calendar.getInstance().get(Calendar.YEAR);
            String yearString = year + "";
            out.write(yearString);
        } catch (IOException ex) {
            LOGGER.info("Year wasn't written", ex);
            throw new JspException(ex.getMessage());
        }
        return SKIP_BODY;
    }
}
