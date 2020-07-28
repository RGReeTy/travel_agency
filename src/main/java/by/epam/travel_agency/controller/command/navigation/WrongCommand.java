package by.epam.travel_agency.controller.command.navigation;

import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.controller.paramName.RequestParameterName;
import by.epam.travel_agency.service.util.ConfigurationManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The type Wrong command.
 */
public class WrongCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        forwardToPage(request, response, ConfigurationManager.getProperty(RequestParameterName.PAGE_MAIN));
    }
}
