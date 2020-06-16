package by.epam.travel_agency.controller.command.navigation;

import by.epam.travel_agency.bean.Hotel;
import by.epam.travel_agency.bean.User;
import by.epam.travel_agency.controller.MessageKey;
import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.service.factory.ServiceFactory;
import by.epam.travel_agency.service.receiver.ReceiverException;
import by.epam.travel_agency.service.receiver.TourService;
import by.epam.travel_agency.service.util.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Set;

import static by.epam.travel_agency.service.validation.UserValidator.checkUserIsManager;

public class GoToCreateNewTourCommand implements Command {

    private static final Logger logger = Logger.getLogger(GoToCreateNewTourCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        TourService tourService = serviceFactory.getTourService();

        User user = (User) request.getSession().getAttribute("user");
        Map<Integer, String> typeOfTourMap = null;
        Set<Hotel> hotelSet = null;
        Map<Integer, Integer> discounts = null;

        if (checkUserIsManager(user)) {
            try {
                typeOfTourMap = tourService.getAllTourTypesFromDB();
                hotelSet = tourService.getAllHotels();
                discounts = tourService.getDiscountMapFromDB();
            } catch (ReceiverException e) {
                logger.debug(e);
                return ConfigurationManager.getProperty("path.page.error");
            }
        } else {
            request.setAttribute("message", MessageKey.ILLEGAL_LEVEL_ACCESS);
        }
        request.setAttribute("typeOfTourMap", typeOfTourMap);
        request.setAttribute("hotelSet", hotelSet);
        request.setAttribute("discounts", discounts);
        return ConfigurationManager.getProperty("path.page.manager_new_tour");
    }
}