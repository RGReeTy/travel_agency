package by.epam.travel_agency.controller.command.ajax.impl;

import by.epam.travel_agency.bean.Defrayal;
import by.epam.travel_agency.controller.command.ajax.AjaxCommand;
import by.epam.travel_agency.controller.paramName.RequestParameterName;
import by.epam.travel_agency.service.factory.ServiceFactory;
import by.epam.travel_agency.service.receiver.ReceiverException;
import by.epam.travel_agency.service.receiver.TourService;
import by.epam.travel_agency.service.receiver.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.epam.travel_agency.controller.util.EntityBuilderHelper.createDefrayalForAnonim;

public class CreateNewDefrayalFromAnonim implements AjaxCommand {

    private static final Logger logger = Logger.getLogger(CreateNewDefrayalFromAnonim.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String answer = RequestParameterName.OK;

        TourService tourService = ServiceFactory.getInstance().getTourService();
        UserService userService = ServiceFactory.getInstance().getUserService();

        String name = request.getParameter(RequestParameterName.NAME);
        String phone = request.getParameter(RequestParameterName.PHONE);
        int tour_id = Integer.parseInt(request.getParameter(RequestParameterName.ID_TOUR).trim());

        if (name == null & phone == null & tour_id == 0) {
            answer = "All fields are empty!";
        } else {

            Defrayal defrayal = createDefrayalForAnonim(name, phone, tour_id);

            try {
                defrayal.setCount(tourService.getTourById(tour_id).getPrice());
                userService.addNewUser(defrayal.getUser());
                tourService.addNewDefrayal(defrayal);
            } catch (ReceiverException e) {
                logger.error(e);
                response.setStatus(500);
            }
        }
        return answer;
    }

}
