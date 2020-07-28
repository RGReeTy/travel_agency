package by.epam.travel_agency.controller.command.tour;

import by.epam.travel_agency.bean.Defrayal;
import by.epam.travel_agency.bean.User;
import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.controller.paramName.MessageKey;
import by.epam.travel_agency.controller.paramName.RequestParameterName;
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

/**
 * The type Get all defrayal command.
 */
public class GetAllDefrayalCommand implements Command {
    private static final Logger logger = Logger.getLogger(GetAllDefrayalCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        TourService tourService = ServiceFactory.getInstance().getTourService();

        User user = (User) request.getSession().getAttribute(RequestParameterName.USER);

        logger.info(user);

        if (user != null) {
            try {
                List<Defrayal> defrayalList = tourService.getAllDefrayals();
                request.setAttribute(RequestParameterName.DEBTS, defrayalList);
                forwardToPage(request, response, ConfigurationManager.getProperty(RequestParameterName.PAGE_PAYMENT_HISTORY));
            } catch (ReceiverException e) {
                logger.error(e);
                request.setAttribute(RequestParameterName.MESSAGE, MessageKey.DATABASE_ERROR);
                response.sendRedirect(ConfigurationManager.getProperty(RequestParameterName.PAGE_ERROR));
            }
        }
    }
}