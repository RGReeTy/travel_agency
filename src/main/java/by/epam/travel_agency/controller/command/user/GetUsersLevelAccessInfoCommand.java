package by.epam.travel_agency.controller.command.user;

import by.epam.travel_agency.bean.User;
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
import java.util.List;

public class GetUsersLevelAccessInfoCommand implements Command {

    private static final Logger logger = Logger.getLogger(GetUsersLevelAccessInfoCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();
        List<User> userList = null;
        try {
            userList = userService.receiverUserFindAll();
        } catch (ReceiverException e) {
            logger.error(e);
        }

        if (userList == null || userList.isEmpty()) {
            request.setAttribute(RequestParameterName.MESSAGE, MessageKey.USERS_LIST_IS_EMPTY);
            response.sendRedirect(ConfigurationManager.getProperty(RequestParameterName.PAGE_ERROR));
        } else {
            request.setAttribute(RequestParameterName.USERS_LIST, userList);
            forwardToPage(request, response, ConfigurationManager.getProperty(RequestParameterName.PAGE_ADMIN_CONTROL));
        }
    }
}
