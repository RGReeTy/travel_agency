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
import java.io.IOException;
import java.util.List;

/**
 * The type Get users level access info command.
 */
public class GetUsersLevelAccessInfoCommand implements Command {

    private static final Logger logger = Logger.getLogger(GetUsersLevelAccessInfoCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserService userService = ServiceFactory.getInstance().getUserService();

        try {
            List<User> userList = userService.findAllUsers();
            if (userList != null) {
                request.setAttribute(RequestParameterName.USERS_LIST, userList);
                forwardToPage(request, response, ConfigurationManager.getProperty(RequestParameterName.PAGE_ADMIN_CONTROL));
            }

        } catch (ReceiverException e) {
            request.setAttribute(RequestParameterName.MESSAGE, MessageKey.USERS_LIST_IS_EMPTY);
            response.sendRedirect(ConfigurationManager.getProperty(RequestParameterName.PAGE_ERROR));
        }
    }
}
