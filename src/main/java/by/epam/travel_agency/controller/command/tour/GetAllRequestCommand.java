package by.epam.travel_agency.controller.command.tour;

import by.epam.travel_agency.bean.Request;
import by.epam.travel_agency.bean.User;
import by.epam.travel_agency.controller.MessageKey;
import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.service.factory.ServiceFactory;
import by.epam.travel_agency.service.receiver.ReceiverException;
import by.epam.travel_agency.service.receiver.TourService;
import by.epam.travel_agency.service.util.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GetAllRequestCommand implements Command {
    private static final Logger logger = Logger.getLogger(GetAllRequestCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        TourService tourService = serviceFactory.getTourService();

        User user = (User) request.getSession().getAttribute("user");
        logger.info(user);
        if (user == null) {
            request.setAttribute("message", MessageKey.LOG_IN_ERROR);
            return ConfigurationManager.getProperty("path.page.error");
        } else {
            List<Request> requestList;
            try {
                requestList = tourService.getAllRequests();
                request.setAttribute("requestsForManager", requestList);
            } catch (ReceiverException e) {
                logger.debug(e);
            }
        }
        return ConfigurationManager.getProperty("path.page.payment_history");
    }
}