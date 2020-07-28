package by.epam.travel_agency.controller.command.navigation;

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
import java.io.IOException;
import java.util.Map;

import static by.epam.travel_agency.service.validation.UserValidator.checkUserIsAdmin;
import static by.epam.travel_agency.service.validation.UserValidator.checkUserIsManager;

/**
 * The type Go to control page command.
 */
public class GoToControlPageCommand implements Command {

    private static final Logger logger = Logger.getLogger(GoToControlPageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        UserService userService = ServiceFactory.getInstance().getUserService();

        User user = (User) request.getSession().getAttribute(RequestParameterName.USER);

        if (user == null) {
            request.setAttribute(RequestParameterName.MESSAGE, MessageKey.ILLEGAL_LEVEL_ACCESS);
            response.sendRedirect(ConfigurationManager.getProperty(RequestParameterName.PAGE_ERROR));
        } else {

            if (checkUserIsManager(user)) {
                forwardToPage(request, response, ConfigurationManager.getProperty(RequestParameterName.PAGE_MANAGER));

            } else if (checkUserIsAdmin(user)) {

                try {

                    Map<String, Integer> usersByLevelAccess = userService.countAllUsersByLevelAccessMap();

                    if (usersByLevelAccess == null || usersByLevelAccess.isEmpty()) {
                        request.setAttribute(RequestParameterName.MESSAGE, MessageKey.USERS_LIST_IS_EMPTY);

                    } else {
                        request.setAttribute(RequestParameterName.USERS_BY_LEVEL_ACCESS, usersByLevelAccess);
                        forwardToPage(request, response, ConfigurationManager.getProperty(RequestParameterName.PAGE_ADMIN));
                    }

                } catch (ReceiverException e) {
                    logger.error(e);
                    request.setAttribute(RequestParameterName.MESSAGE, MessageKey.ILLEGAL_LEVEL_ACCESS);
                    response.sendRedirect(ConfigurationManager.getProperty(RequestParameterName.PAGE_ERROR));
                }
            }
        }
    }
}
