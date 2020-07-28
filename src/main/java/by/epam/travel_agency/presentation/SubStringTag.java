package by.epam.travel_agency.presentation;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * The type Sub string tag.
 */
public class SubStringTag extends TagSupport {

	private static final long serialVersionUID = 1L;
	private final int MAX_TEXT_LENGTH = 140;
	private String text;

	/**
	 * Sets text.
	 *
	 * @param text the text
	 */
	public void setText(String text) {
		this.text = text;
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			if (text.length() > MAX_TEXT_LENGTH) {
				pageContext.getOut().write(text.substring(0, MAX_TEXT_LENGTH) + "...");
			} else {
				pageContext.getOut().write(text + "...");
			}
		} catch (IOException e) {
			throw new JspException(e);
		}
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
}