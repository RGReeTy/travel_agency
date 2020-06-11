package by.epam.travel_agency.controller.command.tour;

import by.epam.travel_agency.bean.Request;
import by.epam.travel_agency.bean.User;
import by.epam.travel_agency.constant.MessageKey;
import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.service.util.ConfigurationManager;
import by.epam.travel_agency.service.receiver.ReceiverException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GetPaymentDebtCommand implements Command {
    private static final Logger logger = Logger.getLogger(GetPaymentDebtCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            request.setAttribute("message", MessageKey.LOG_IN_ERROR);
            return ConfigurationManager.getProperty("path.page.error");
        } else {
            List<Request> requestList;
            try {
                requestList = TOUR_RECEIVER.getAllRequestsWhereIsDebt();
                request.setAttribute("requestsForManager", requestList);
            } catch (ReceiverException e) {
                logger.debug(e);
            }
        }
        return ConfigurationManager.getProperty("path.page.payment_history");
    }
}