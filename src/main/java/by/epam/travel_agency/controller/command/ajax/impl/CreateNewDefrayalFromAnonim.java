package by.epam.travel_agency.controller.command.ajax.impl;

import by.epam.travel_agency.bean.Defrayal;
import by.epam.travel_agency.bean.Tour;
import by.epam.travel_agency.controller.command.ajax.AjaxCommand;
import by.epam.travel_agency.controller.param_name.RequestParameterName;
import by.epam.travel_agency.service.factory.ServiceFactory;
import by.epam.travel_agency.service.receiver.ReceiverException;
import by.epam.travel_agency.service.receiver.TourService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreateNewDefrayalFromAnonim implements AjaxCommand {

    private static final Logger logger = Logger.getLogger(CreateNewDefrayalFromAnonim.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String answer = RequestParameterName.OK;

        TourService tourService = ServiceFactory.getInstance().getTourService();

        String name = request.getParameter(RequestParameterName.NAME);
        String phone = request.getParameter(RequestParameterName.PHONE);
        int tour_id = Integer.parseInt(request.getParameter(RequestParameterName.ID_TOUR));

        logger.info(name + " ==== " + phone + " ==== " + tour_id);

        if (name == null & phone == null & tour_id == 0) {
            answer = "All fields are empty!";
        } else {
            Tour tour = new Tour();
            tour.setId(tour_id);
            Defrayal defrayal = new Defrayal();
            defrayal.setTour(tour);
            defrayal.setAnnotation(name + ":" + phone);
            try {
                tourService.addNewDefrayalMinimalInfo(defrayal);
            } catch (ReceiverException e) {
                logger.error(e);
                response.setStatus(500);
            }
        }
        return answer;
    }
}
