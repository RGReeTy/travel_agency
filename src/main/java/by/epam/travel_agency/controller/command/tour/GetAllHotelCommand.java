package by.epam.travel_agency.controller.command.tour;

import by.epam.travel_agency.bean.Hotel;
import by.epam.travel_agency.constant.MessageKey;
import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.service.util.ConfigurationManager;
import by.epam.travel_agency.service.receiver.ReceiverException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

public class GetAllHotelCommand implements Command {

    private static final Logger logger = Logger.getLogger(GetAllHotelCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        Set<Hotel> hotelSet = null;
        try {
            hotelSet = TOUR_RECEIVER.getAllHotels();
        } catch (ReceiverException e) {
            logger.debug(e);
        }
        if (hotelSet == null || hotelSet.isEmpty()) {
            request.setAttribute("message", MessageKey.SHOW_ALL_HOTELS_ERROR);
            return ConfigurationManager.getProperty("path.page.error");
        }
        request.setAttribute("hotels", hotelSet);
        return ConfigurationManager.getProperty("path.page.hotel");
    }
}
