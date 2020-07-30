package by.epam.travel_agency.controller.command.user;

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

/**
 * The type Change level access command.
 */
public class ChangeLevelAccessCommand implements Command {

    private static final Logger logger = Logger.getLogger(ChangeLevelAccessCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        int userChangeStatus = Integer.parseInt(request.getParameter(RequestParameterName.USER_STATUS));
        int userId = Integer.parseInt(request.getParameter(RequestParameterName.USER_ID));

        UserService userService = ServiceFactory.getInstance().getUserService();

        logger.info("ChangeLevelAccessCommand's params:" + userId + " " + userChangeStatus);

        try {
            if (userService.updateUserStatusByID(userId, userChangeStatus)) {
                request.setAttribute(RequestParameterName.USERS_LIST, userService.findAllUsers());
                forwardToPage(request, response, ConfigurationManager.getProperty(RequestParameterName.PAGE_ADMIN_CONTROL));
            }
        } catch (ReceiverException e) {
            logger.error(e);
            request.setAttribute(RequestParameterName.MESSAGE, MessageKey.DATABASE_ERROR);
            response.sendRedirect(ConfigurationManager.getProperty(RequestParameterName.PAGE_ERROR));
        }
    }
}
