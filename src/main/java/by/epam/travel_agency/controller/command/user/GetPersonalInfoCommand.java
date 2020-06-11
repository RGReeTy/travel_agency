package by.epam.travel_agency.controller.command.user;

import by.epam.travel_agency.bean.Request;
import by.epam.travel_agency.bean.User;
import by.epam.travel_agency.constant.MessageKey;
import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.service.util.ConfigurationManager;
import by.epam.travel_agency.service.receiver.ReceiverException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Set;

public class GetPersonalInfoCommand implements Command {
    private static final Logger logger = Logger.getLogger(GetPersonalInfoCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        BigDecimal totalMoneySpent = BigDecimal.valueOf(0);
        logger.info(user);
        if (user == null) {
            request.setAttribute("message", MessageKey.LOG_IN_ERROR);
            return ConfigurationManager.getProperty("path.page.error");
        } else {
            Set<Request> requests;
            try {
                requests = TOUR_RECEIVER.getAllRequestsForUser(user);
                totalMoneySpent = USER_RECEIVER.countingTotalMoneySpentForUserID(user.getId_user());
                request.setAttribute("requests", requests);
                request.setAttribute("user", user);
                request.setAttribute("totalMoneySpent", totalMoneySpent);
            } catch (ReceiverException e) {
                logger.debug(e);
            }
        }
        return ConfigurationManager.getProperty("path.page.account");
    }
}