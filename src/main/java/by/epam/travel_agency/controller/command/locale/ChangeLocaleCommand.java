package by.epam.travel_agency.controller.command.locale;

import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.controller.param_name.RequestParameterName;
import by.epam.travel_agency.service.util.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeLocaleCommand implements Command {

    private static final Logger logger = Logger.getLogger(ChangeLocaleCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String language = request.getParameter(RequestParameterName.LOCALE);
        String page = request.getParameter(RequestParameterName.PAGE);

        request.getSession().setAttribute(RequestParameterName.LOCALE, language);

        logger.info("language = " + language + ", page = " + page);


        if (page != null) {
            response.sendRedirect(request.getContextPath() + ConfigurationManager.getProperty(RequestParameterName.PAGE_MAIN));
        } else {
            response.sendRedirect(request.getContextPath());
        }
    }
}
