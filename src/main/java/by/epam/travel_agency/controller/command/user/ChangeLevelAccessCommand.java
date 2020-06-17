package by.epam.travel_agency.controller.command.user;

import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.controller.param_name.MessageKey;
import by.epam.travel_agency.controller.param_name.RequestParameterName;
import by.epam.travel_agency.service.factory.ServiceFactory;
import by.epam.travel_agency.service.receiver.ReceiverException;
import by.epam.travel_agency.service.receiver.UserService;
import by.epam.travel_agency.service.util.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeLevelAccessCommand implements Command {

    private static final Logger logger = Logger.getLogger(ChangeLevelAccessCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        int user_id = Integer.parseInt(request.getParameter(RequestParameterName.USER_ID));
        int user_change_status = Integer.parseInt(request.getParameter(RequestParameterName.USER_STATUS));

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();

        logger.info(user_id + " " + user_change_status);

        try {
            if (userService.updateUserStatusByID(user_id, user_change_status)) {
                request.setAttribute(RequestParameterName.USERS_LIST, userService.receiverUserFindAll());
                forwardToPage(request, response, ConfigurationManager.getProperty(RequestParameterName.PAGE_ADMIN_CONTROL));
            }
        } catch (ReceiverException e) {
            logger.error(e);
            request.setAttribute(RequestParameterName.MESSAGE, MessageKey.DATABASE_ERROR);
            response.sendRedirect(ConfigurationManager.getProperty(RequestParameterName.PAGE_ERROR));
        }
    }
}
