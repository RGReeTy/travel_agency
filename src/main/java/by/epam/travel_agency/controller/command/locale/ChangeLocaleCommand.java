package by.epam.travel_agency.controller.command.locale;

import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.controller.paramName.RequestParameterName;
import by.epam.travel_agency.service.util.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The type Change locale command.
 */
public class ChangeLocaleCommand implements Command {

    private static final Logger logger = Logger.getLogger(ChangeLocaleCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String language = request.getParameter(RequestParameterName.LOCALE);
        String page = request.getParameter(RequestParameterName.PAGE);

        request.getSession().setAttribute(RequestParameterName.LOCALE, language);

        if (page != null) {
            forwardToPage(request, response, ConfigurationManager.getProperty(page));
        } else {
            response.sendRedirect(request.getContextPath());
        }
    }
}
