package by.epam.travel_agency.controller.command.page;

import by.epam.travel_agency.bean.Hotel;
import by.epam.travel_agency.bean.User;
import by.epam.travel_agency.constant.MessageKey;
import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.service.manager.ConfigurationManager;
import by.epam.travel_agency.service.receiver.ReceiverException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Set;

import static by.epam.travel_agency.service.validation.UserValidator.checkUserIsManager;

public class GoToCreateNewTourCommand implements Command {

    private static final Logger logger = Logger.getLogger(GoToCreateNewTourCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        HashMap<Integer, String> typeOfTourMap = null;
        Set<Hotel> hotelSet = null;
        HashMap<Integer, Integer> discounts = null;

        if (checkUserIsManager(user)) {
            try {
                typeOfTourMap = TOUR_RECEIVER.getAllTourTypesFromDB();
                hotelSet = TOUR_RECEIVER.getAllHotels();
                discounts = TOUR_RECEIVER.getDiscountMapFromDB();
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