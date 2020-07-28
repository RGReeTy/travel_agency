package by.epam.travel_agency.controller.command.user;

import by.epam.travel_agency.bean.User;
import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.controller.paramName.MessageKey;
import by.epam.travel_agency.controller.paramName.RequestParameterName;
import by.epam.travel_agency.service.factory.ServiceFactory;
import by.epam.travel_agency.service.receiver.ReceiverException;
import by.epam.travel_agency.service.receiver.UserService;
import by.epam.travel_agency.service.util.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The type Log in command.
 */
public class LogInCommand implements Command {

    private static final Logger logger = Logger.getLogger(LogInCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String login = request.getParameter(RequestParameterName.PARAM_NAME_LOGIN);
        String password = request.getParameter(RequestParameterName.PARAM_NAME_PASSWORD);

        UserService userService = ServiceFactory.getInstance().getUserService();
        HttpSession session = request.getSession();

        try {
            User user = userService.findUserByLoginAndPassword(login, password);
            if (user != null) {
                session.setAttribute(RequestParameterName.USER, user);
                response.sendRedirect(request.getContextPath());
            } else {
                request.setAttribute(RequestParameterName.MESSAGE, MessageKey.LOG_IN_ERROR);
                forwardToPage(request, response, ConfigurationManager.getProperty(RequestParameterName.PAGE_ERROR));
            }
        } catch (ReceiverException | ServletException e) {
            logger.error("Error at LogInCommand: " + e);
            response.sendRedirect(ConfigurationManager.getProperty(RequestParameterName.PAGE_ERROR));
        }
    }
}