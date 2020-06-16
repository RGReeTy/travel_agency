package by.epam.travel_agency.controller.command.user;

import by.epam.travel_agency.controller.MessageKey;
import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.service.factory.ServiceFactory;
import by.epam.travel_agency.service.receiver.ReceiverException;
import by.epam.travel_agency.service.receiver.UserService;
import by.epam.travel_agency.service.util.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ChangeLevelAccessCommand implements Command {

    private static final Logger logger = Logger.getLogger(ChangeLevelAccessCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        int user_id = Integer.parseInt(request.getParameter("user_id"));
        int user_change_status = Integer.parseInt(request.getParameter("user_status"));

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();

        logger.info(user_id + " " + user_change_status);

        try {
            if (userService.updateUserStatusByID(user_id, user_change_status)) {
                request.setAttribute("userList", userService.receiverUserFindAll());
            }
        } catch (ReceiverException e) {
            logger.debug(e);
            request.setAttribute("message", MessageKey.DATABASE_ERROR);
            return ConfigurationManager.getProperty("path.page.error");
        }
        return ConfigurationManager.getProperty("path.page.admin_control");
    }
}
