package by.epam.travel_agency.controller.command.user;

import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.controller.paramName.RequestParameterName;
import by.epam.travel_agency.service.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The type Log out command.
 */
public class LogOutCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.removeAttribute(RequestParameterName.USER);
        response.sendRedirect(request.getContextPath() + ConfigurationManager.getProperty(RequestParameterName.PAGE_MAIN));
    }
}
