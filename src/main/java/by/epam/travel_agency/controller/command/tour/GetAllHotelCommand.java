package by.epam.travel_agency.controller.command.tour;

import by.epam.travel_agency.bean.Hotel;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

/**
 * The type Get all hotel command.
 */
public class GetAllHotelCommand implements Command {

    private static final Logger logger = Logger.getLogger(GetAllHotelCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        TourService tourService = ServiceFactory.getInstance().getTourService();
        HttpSession session = request.getSession();

        try {
            Set<Hotel> hotelSet = tourService.getAllHotels();
            if (hotelSet != null) {
                session.setAttribute(RequestParameterName.HOTELS, hotelSet);
            }

        } catch (ReceiverException e) {
            logger.error(e);
            request.setAttribute(RequestParameterName.MESSAGE, MessageKey.SHOW_ALL_HOTELS_ERROR);
            response.sendRedirect(ConfigurationManager.getProperty(RequestParameterName.PAGE_ERROR));
        }
        forwardToPage(request, response, ConfigurationManager.getProperty(RequestParameterName.PAGE_HOTELS));
    }
}
