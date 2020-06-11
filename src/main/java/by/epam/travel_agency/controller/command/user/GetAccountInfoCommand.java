package by.epam.travel_agency.controller.command.user;

import by.epam.travel_agency.bean.User;
import by.epam.travel_agency.constant.MessageKey;
import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.service.util.ConfigurationManager;
import by.epam.travel_agency.service.receiver.ReceiverException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public class GetAccountInfoCommand implements Command {
    private static final Logger logger = Logger.getLogger(GetAccountInfoCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        int user_id = Integer.parseInt(request.getParameter("user_id"));
        User user = new User();
        BigDecimal totalMoneySpent = BigDecimal.valueOf(0);
        try {
            user = USER_RECEIVER.receiverUserFindById(user_id);
            totalMoneySpent = USER_RECEIVER.countingTotalMoneySpentForUserID(user_id);
        } catch (ReceiverException e) {
            logger.debug(e);
        }
        logger.info(user);
        if (user == null) {
            request.setAttribute("message", MessageKey.USER_ERROR);
            return ConfigurationManager.getProperty("path.page.error");
        } else {
            request.setAttribute("user_info", user);
            request.setAttribute("totalMoneySpent", totalMoneySpent);
        }
        return ConfigurationManager.getProperty("path.page.manager_user_info");
    }
}