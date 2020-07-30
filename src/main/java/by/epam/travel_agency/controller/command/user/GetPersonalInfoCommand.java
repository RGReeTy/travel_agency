package by.epam.travel_agency.controller.command.user;

import by.epam.travel_agency.bean.Defrayal;
import by.epam.travel_agency.bean.User;
import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.controller.paramName.MessageKey;
import by.epam.travel_agency.controller.paramName.RequestParameterName;
import by.epam.travel_agency.service.factory.ServiceFactory;
import by.epam.travel_agency.service.receiver.ReceiverException;
import by.epam.travel_agency.service.receiver.TourService;
import by.epam.travel_agency.service.receiver.UserService;
import by.epam.travel_agency.service.util.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Set;

/**
 * The type Get personal info command.
 */
public class GetPersonalInfoCommand implements Command {
    private static final Logger logger = Logger.getLogger(GetPersonalInfoCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        TourService tourService = ServiceFactory.getInstance().getTourService();
        UserService userService = ServiceFactory.getInstance().getUserService();

        User user = (User) request.getSession().getAttribute(RequestParameterName.USER);

        try {
            Set<Defrayal> defrayals = tourService.getAllDefrayalsForUser(user);
            BigDecimal totalMoneySpent = userService.countingTotalMoneySpentForUserID(user.getIdUser());
            request.setAttribute(RequestParameterName.REQUESTS, defrayals);
            request.setAttribute(RequestParameterName.USER, user);
            request.setAttribute(RequestParameterName.MONEY_SPENT, totalMoneySpent);
            forwardToPage(request, response, ConfigurationManager.getProperty(RequestParameterName.PAGE_ACCOUNT));
        } catch (ReceiverException e) {
            logger.error("Error catches at GetPersonalInfoCommand: " + e);
            request.setAttribute(RequestParameterName.MESSAGE, MessageKey.USER_ERROR);
            response.sendRedirect(ConfigurationManager.getProperty(RequestParameterName.PAGE_ERROR));
        }
    }
}