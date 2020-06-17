package by.epam.travel_agency.controller.command.tour;

import by.epam.travel_agency.bean.Request;
import by.epam.travel_agency.bean.User;
import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.controller.param_name.MessageKey;
import by.epam.travel_agency.controller.param_name.RequestParameterName;
import by.epam.travel_agency.service.factory.ServiceFactory;
import by.epam.travel_agency.service.receiver.ReceiverException;
import by.epam.travel_agency.service.receiver.TourService;
import by.epam.travel_agency.service.util.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetAllRequestCommand implements Command {
    private static final Logger logger = Logger.getLogger(GetAllRequestCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        TourService tourService = serviceFactory.getTourService();

        User user = (User) request.getSession().getAttribute(RequestParameterName.USER);

        logger.info(user);

        if (user != null) {
            try {
                List<Request> requestList = tourService.getAllRequests();
                request.setAttribute(RequestParameterName.DEBTS, requestList);
                forwardToPage(request, response, ConfigurationManager.getProperty(RequestParameterName.PAGE_PAYMENT_HISTORY));
            } catch (ReceiverException e) {
                logger.error(e);
                request.setAttribute(RequestParameterName.MESSAGE, MessageKey.DATABASE_ERROR);
                response.sendRedirect(ConfigurationManager.getProperty(RequestParameterName.PAGE_ERROR));
            }
        }
    }
}