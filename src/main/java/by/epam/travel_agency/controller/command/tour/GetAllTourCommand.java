package by.epam.travel_agency.controller.command.tour;

import by.epam.travel_agency.bean.Tour;
import by.epam.travel_agency.constant.MessageKey;
import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.service.util.ConfigurationManager;
import by.epam.travel_agency.service.receiver.ReceiverException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

public class GetAllTourCommand implements Command {
    private static final Logger logger = Logger.getLogger(GetAllTourCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        Set<Tour> tourSet = null;
        try {
            tourSet = TOUR_RECEIVER.getAllTours();
        } catch (ReceiverException e) {
            logger.debug(e);
        }
        if (tourSet == null || tourSet.isEmpty()) {
            request.setAttribute("message", MessageKey.SHOW_ALL_TOURS_ERROR);
            return ConfigurationManager.getProperty("path.page.error");
        }
        request.setAttribute("tours", tourSet);
        return ConfigurationManager.getProperty("path.page.tours");
    }
}
