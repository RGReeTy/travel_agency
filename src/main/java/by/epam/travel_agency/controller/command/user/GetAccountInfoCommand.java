package by.epam.travel_agency.controller.command.user;

import by.epam.travel_agency.bean.User;
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
import java.math.BigDecimal;

/**
 * The type Get account info command.
 */
public class GetAccountInfoCommand implements Command {
    private static final Logger logger = Logger.getLogger(GetAccountInfoCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserService userService = ServiceFactory.getInstance().getUserService();

        int userId = Integer.parseInt(request.getParameter(RequestParameterName.USER_ID));

        try {
            User user = userService.findUserById(userId);
            BigDecimal totalMoneySpent = userService.countingTotalMoneySpentForUserID(userId);

            if (user != null) {
                logger.info(user);

                request.setAttribute(RequestParameterName.USER_INFO, user);
                request.setAttribute(RequestParameterName.MONEY_SPENT, totalMoneySpent);

                forwardToPage(request, response, ConfigurationManager.getProperty(RequestParameterName.PAGE_MANAGER_USER_INFO));
            }
        } catch (ReceiverException e) {
            logger.error(e);
            request.setAttribute(RequestParameterName.MESSAGE, MessageKey.USER_ERROR);
        }
        response.sendRedirect(ConfigurationManager.getProperty(RequestParameterName.PAGE_ERROR));
    }
}