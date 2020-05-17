package by.epam.travel_agency.controller.command.tour;

import by.epam.travel_agency.bean.Hotel;
import by.epam.travel_agency.bean.Tour;
import by.epam.travel_agency.constant.MessageKey;
import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.service.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

public class ShowAllHotelCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        Set<Hotel> hotelSet = TOUR_RECEIVER.getAllHotels();
        if (hotelSet == null) {
            request.setAttribute("message", MessageKey.SHOW_ALL_HOTELS_ERROR);
            return ConfigurationManager.getProperty("path.page.error");
        }
        request.setAttribute("hotels", hotelSet);
        return ConfigurationManager.getProperty("path.page.hotel");
    }
}
