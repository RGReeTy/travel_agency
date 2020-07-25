package by.epam.travel_agency.controller.command.ajax.impl;

import by.epam.travel_agency.controller.command.ajax.AjaxCommand;
import by.epam.travel_agency.controller.paramName.RequestParameterName;
import by.epam.travel_agency.service.factory.ServiceFactory;
import by.epam.travel_agency.service.receiver.ReceiverException;
import by.epam.travel_agency.service.receiver.TourService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConfirmThePayment implements AjaxCommand {

    private static final Logger logger = Logger.getLogger(ConfirmThePayment.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String answer = RequestParameterName.OK;

        TourService tourService = ServiceFactory.getInstance().getTourService();

        String defrayalIDtemp = request.getParameter(RequestParameterName.DEFRAYAL_ID);
        String defrayalNote = request.getParameter(RequestParameterName.DEFRAYAL_NOTE);

        if (defrayalNote == null) {
            defrayalNote = "Confirmed";
        }

        if (defrayalIDtemp == null) {
            answer = "Can't find ID defrayal!";
        } else {
            logger.info("ConfirmThePayment command starts. defrayal ID and NOTE are: " + defrayalIDtemp + " and " + defrayalNote);
            try {
                int idDefrayal = Integer.parseInt(defrayalIDtemp);
                tourService.updateDefrayalById(idDefrayal, defrayalNote);
            } catch (ReceiverException e) {
                logger.error(e);
                response.setStatus(500);
            }

        }
        return answer;
    }
}
