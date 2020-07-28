package by.epam.travel_agency.presentation;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.time.LocalDate;

/**
 * The type Date time tag.
 */
public class DateTimeTag extends TagSupport {
    private String date;

    @Override
    public int doStartTag() throws JspException {

        JspWriter out = pageContext.getOut();

        try {
            if (date == null) {
                date = LocalDate.now().toString();
            }

            out.write(date);

        } catch (IOException e) {

            throw new JspException(e);
        }

        return SKIP_BODY;
    }


    /**
     * Gets date.
     *
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(String date) {
        this.date = date;
    }
}