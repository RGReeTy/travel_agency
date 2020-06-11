package by.epam.travel_agency.controller.command.navigation;

import by.epam.travel_agency.bean.User;
import by.epam.travel_agency.constant.MessageKey;
import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.service.util.ConfigurationManager;
import by.epam.travel_agency.service.receiver.ReceiverException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

import static by.epam.travel_agency.service.validation.UserValidator.checkUserIsAdmin;
import static by.epam.travel_agency.service.validation.UserValidator.checkUserIsManager;

public class GoToControlPageCommand implements Command {

    private static final Logger logger = Logger.getLogger(GoToControlPageCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (checkUserIsManager(user)) {
            return ConfigurationManager.getProperty("path.page.manager");

        } else if (checkUserIsAdmin(user)) {
            HashMap<String, Integer> usersByLevelAccess = null;
            try {
                usersByLevelAccess = USER_RECEIVER.countAllUsersByLevelAccessMap();
            } catch (ReceiverException e) {
                logger.debug(e);
            }

            if (usersByLevelAccess == null || usersByLevelAccess.isEmpty()) {
                request.setAttribute("message", MessageKey.USERS_LIST_IS_EMPTY);
            } else {
                request.setAttribute("usersByLevelAccess", usersByLevelAccess);
                return ConfigurationManager.getProperty("path.page.admin");
            }

        } else {
            request.setAttribute("message", MessageKey.ILLEGAL_LEVEL_ACCESS);
        }
        return ConfigurationManager.getProperty("path.page.error");

    }
}
