package by.epam.travel_agency.controller.command.tour;

import by.epam.travel_agency.bean.Tour;
import by.epam.travel_agency.constant.MessageKey;
import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.service.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

public class ShowAllTourCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        Set<Tour> tourSet = TOUR_RECEIVER.getAllTours();
        if (tourSet == null) {
            request.setAttribute("message", MessageKey.SHOW_ALL_TOURS_ERROR);
            return ConfigurationManager.getProperty("path.page.error");
        }
        request.setAttribute("tours", tourSet);
        return ConfigurationManager.getProperty("path.page.tours");
    }
}
