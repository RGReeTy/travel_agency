package by.epam.travel_agency.controller.command;

import by.epam.travel_agency.controller.paramName.RequestParameterName;
import by.epam.travel_agency.service.util.ConfigurationManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EmptyCommand implements Command {

    /**
     * EmptyCommand will be executed if a servlet has been accessed without a command.
     *
     * @param request HttpServletRequest request
     *
     */

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        forwardToPage(request, response, ConfigurationManager.getProperty(RequestParameterName.PAGE_MAIN));
    }
}