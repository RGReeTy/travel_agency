package by.epam.travel_agency.controller.command.tour;

import by.epam.travel_agency.bean.Tour;
import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.service.factory.ServiceFactory;
import by.epam.travel_agency.service.receiver.ReceiverException;
import by.epam.travel_agency.service.receiver.TourService;
import by.epam.travel_agency.service.util.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static by.epam.travel_agency.service.util.EntityBuilderHelper.makeTourFromRequest;

public class CreateNewTourCommand implements Command {

    private static final Logger logger = Logger.getLogger(CreateNewTourCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        TourService tourService = serviceFactory.getTourService();

        String response = ConfigurationManager.getProperty("path.page.error");
        try {
            Tour tour = makeTourFromRequest(request);
            tourService.addNewTourToDB(tour);

        } catch (ReceiverException e) {
            logger.error("Error catches at CreateNewTourCommand: " + e);
        }
        return response;
    }
}