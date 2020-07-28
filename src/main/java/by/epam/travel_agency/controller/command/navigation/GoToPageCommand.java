package by.epam.travel_agency.controller.command.navigation;

import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.controller.paramName.RequestParameterName;
import by.epam.travel_agency.service.util.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The type Go to page command.
 */
public class GoToPageCommand implements Command {

    private static final Logger logger = Logger.getLogger(GoToPageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String page = request.getParameter(RequestParameterName.PAGE);

        logger.info("GoToPageCommand: " + page);


        forwardToPage(request, response, ConfigurationManager.getProperty(page));

    }
}
