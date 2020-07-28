package by.epam.travel_agency.controller.command.navigation;

import by.epam.travel_agency.bean.Hotel;
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
import java.util.Map;
import java.util.Set;

import static by.epam.travel_agency.service.validation.UserValidator.checkUserIsManager;

/**
 * The type Go to create new tour command.
 */
public class GoToCreateNewTourCommand implements Command {

    private static final Logger logger = Logger.getLogger(GoToCreateNewTourCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        TourService tourService = ServiceFactory.getInstance().getTourService();

        User user = (User) request.getSession().getAttribute(RequestParameterName.USER);

        if (user == null) {

            request.setAttribute(RequestParameterName.MESSAGE, MessageKey.ILLEGAL_LEVEL_ACCESS);
            response.sendRedirect(ConfigurationManager.getProperty(RequestParameterName.PAGE_ERROR));

        } else {

            if (checkUserIsManager(user)) {

                try {
                    Map<Integer, String> typeOfTourMap = tourService.getAllTourTypesFromDB();
                    Set<Hotel> hotelSet = tourService.getAllHotels();
                    Map<Integer, Integer> discounts = tourService.getDiscountMapFromDB();

                    request.setAttribute(RequestParameterName.TYPE_OF_TOUR_MAP, typeOfTourMap);
                    request.setAttribute(RequestParameterName.HOTEL_SET, hotelSet);
                    request.setAttribute(RequestParameterName.DISCOUNTS, discounts);

                    forwardToPage(request, response, ConfigurationManager.getProperty(RequestParameterName.PAGE_MANAGER_NEW_TOUR));

                } catch (ReceiverException e) {
                    logger.error(e);
                    request.setAttribute(RequestParameterName.MESSAGE, MessageKey.ILLEGAL_LEVEL_ACCESS);
                    response.sendRedirect(ConfigurationManager.getProperty(RequestParameterName.PAGE_ERROR));
                }
            }
        }
    }
}