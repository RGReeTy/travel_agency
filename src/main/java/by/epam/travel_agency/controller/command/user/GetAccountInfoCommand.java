package by.epam.travel_agency.controller.command.user;

import by.epam.travel_agency.bean.Request;
import by.epam.travel_agency.bean.User;
import by.epam.travel_agency.constant.MessageKey;
import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.service.manager.ConfigurationManager;
import by.epam.travel_agency.service.receiver.ReceiverException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

public class GetAccountInfoCommand implements Command {
    private static final Logger logger = Logger.getLogger(GetAccountInfoCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        logger.info(user);
        if (user == null) {
            request.setAttribute("message", MessageKey.LOG_IN_ERROR);
            return ConfigurationManager.getProperty("path.page.error");
        } else {
            Set<Request> requestSet;
            try {
                requestSet = TOUR_RECEIVER.getAllRequestsForUser(user);
                request.setAttribute("requests", requestSet);
            } catch (ReceiverException e) {
                logger.debug(e);
            }
        }
        return ConfigurationManager.getProperty("path.page.account");
    }
}