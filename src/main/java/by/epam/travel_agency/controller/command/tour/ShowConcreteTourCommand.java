package by.epam.travel_agency.controller.command.tour;

import by.epam.travel_agency.bean.Tour;
import by.epam.travel_agency.constant.MessageKey;
import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.service.manager.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

public class ShowConcreteTourCommand implements Command {
    private static final Logger logger = Logger.getLogger(ShowConcreteTourCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String typeOfTour = request.getParameter("type");
        Set<Tour> tourSet = TOUR_RECEIVER.getConcreteTypeTours(typeOfTour);
        if (tourSet == null) {
            request.setAttribute("message", MessageKey.SHOW_ALL_TOURS_ERROR);
            return ConfigurationManager.getProperty("path.page.error");
        }
        request.setAttribute("tours", tourSet);
        return ConfigurationManager.getProperty("path.page.tours");
    }
}
