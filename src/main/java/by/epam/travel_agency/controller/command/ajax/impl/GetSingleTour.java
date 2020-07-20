package by.epam.travel_agency.controller.command.ajax.impl;

import by.epam.travel_agency.bean.Tour;
import by.epam.travel_agency.bean.User;
import by.epam.travel_agency.controller.command.ajax.AjaxCommand;
import by.epam.travel_agency.controller.param_name.RequestParameterName;
import by.epam.travel_agency.service.factory.ServiceFactory;
import by.epam.travel_agency.service.receiver.ReceiverException;
import by.epam.travel_agency.service.receiver.TourService;
import by.epam.travel_agency.service.receiver.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

import static by.epam.travel_agency.service.util.FinalPriceMaker.countFinalPriceHavingPriceAndDiscount;

public class GetSingleTour implements AjaxCommand {
    private static final Logger logger = Logger.getLogger(GetSingleTour.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String answer = RequestParameterName.OK;

        TourService tourService = ServiceFactory.getInstance().getTourService();
        UserService userService = ServiceFactory.getInstance().getUserService();
        HttpSession session = request.getSession();


        int id_tour = Integer.parseInt(request.getParameter(RequestParameterName.ID_TOUR));
        User user = (User) session.getAttribute(RequestParameterName.USER);
        BigDecimal personalCount;

        if (id_tour != 0) {
            try {
                Tour tour = tourService.getTourById(id_tour);
                personalCount = tour.getPrice();
                if (user != null) {
                    int userDiscount = userService.getDiscountByID(user.getId_discount());
                    int maxDiscount = Math.max(tour.getDiscount(), userDiscount);
                    //int temp = tour.getDiscount() >= userDiscount ? tour.getDiscount() : userDiscount;
                    personalCount = countFinalPriceHavingPriceAndDiscount(tour.getPrice(), maxDiscount);
                }

                request.setAttribute(RequestParameterName.TOUR, tour);
                request.setAttribute(RequestParameterName.PERSONAL_COUNT, personalCount);

                logger.info(tour.toString() + "=====" + personalCount);
            } catch (ReceiverException e) {
                logger.error(e);
                response.setStatus(500);
            }
        } else {
            logger.error("Id_tour came as null!");
            response.setStatus(404);
        }
        return answer;
    }
}
