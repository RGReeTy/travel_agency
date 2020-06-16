package by.epam.travel_agency.controller.command.user;

import by.epam.travel_agency.bean.Request;
import by.epam.travel_agency.bean.User;
import by.epam.travel_agency.controller.MessageKey;
import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.service.factory.ServiceFactory;
import by.epam.travel_agency.service.receiver.ReceiverException;
import by.epam.travel_agency.service.receiver.TourService;
import by.epam.travel_agency.service.receiver.UserService;
import by.epam.travel_agency.service.util.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Set;

public class GetPersonalInfoCommand implements Command {
    private static final Logger logger = Logger.getLogger(GetPersonalInfoCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        TourService tourService = serviceFactory.getTourService();
        UserService userService = serviceFactory.getUserService();

        User user = (User) request.getSession().getAttribute("user");
        logger.info(user);
        if (user == null) {
            request.setAttribute("message", MessageKey.LOG_IN_ERROR);
            return ConfigurationManager.getProperty("path.page.error");
        } else {
            Set<Request> requests;
            try {
                requests = tourService.getAllRequestsForUser(user);
                BigDecimal totalMoneySpent = userService.countingTotalMoneySpentForUserID(user.getId_user());
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