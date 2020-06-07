package by.epam.travel_agency.controller.command.tour;

import by.epam.travel_agency.bean.Tour;
import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.service.manager.ConfigurationManager;
import by.epam.travel_agency.service.receiver.ReceiverException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static by.epam.travel_agency.service.manager.EntityBuilderHelper.makeTourFromRequest;

public class CreateNewTourCommand implements Command {

    private static final Logger logger = Logger.getLogger(CreateNewTourCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String response = ConfigurationManager.getProperty("path.page.error");
        try {
            Tour tour = makeTourFromRequest(request);
            if (tour != null) {
                TOUR_RECEIVER.addNewTourToDB(tour);
            }


        } catch (ReceiverException e) {
            logger.error("Error catches at RegisterCommand: " + e);
        }
        return response;
    }
}