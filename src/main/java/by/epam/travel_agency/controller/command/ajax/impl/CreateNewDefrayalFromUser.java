package by.epam.travel_agency.controller.command.ajax.impl;

import by.epam.travel_agency.bean.Defrayal;
import by.epam.travel_agency.bean.User;
import by.epam.travel_agency.controller.command.ajax.AjaxCommand;
import by.epam.travel_agency.controller.paramName.RequestParameterName;
import by.epam.travel_agency.service.factory.ServiceFactory;
import by.epam.travel_agency.service.receiver.ReceiverException;
import by.epam.travel_agency.service.receiver.TourService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.epam.travel_agency.controller.util.EntityBuilderHelper.createDefrayalForUser;

/**
 * The type Create new defrayal from user.
 */
public class CreateNewDefrayalFromUser implements AjaxCommand {

    private static final Logger logger = Logger.getLogger(CreateNewDefrayalFromUser.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String answer = RequestParameterName.OK;

        TourService tourService = ServiceFactory.getInstance().getTourService();

        int tourId = Integer.parseInt(request.getParameter(RequestParameterName.ID_TOUR).trim());
        User user = (User) request.getSession().getAttribute(RequestParameterName.USER);

        Defrayal defrayal = createDefrayalForUser(user, tourId);

        try {
            defrayal.setCount(tourService.getTourById(tourId).getPrice());
            tourService.addNewDefrayal(defrayal);
        } catch (ReceiverException e) {
            logger.error(e);
            response.setStatus(500);
        }

        return answer;
    }

}
