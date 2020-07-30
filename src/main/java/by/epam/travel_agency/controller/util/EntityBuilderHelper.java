package by.epam.travel_agency.controller.util;

import by.epam.travel_agency.bean.Defrayal;
import by.epam.travel_agency.bean.Hotel;
import by.epam.travel_agency.bean.Tour;
import by.epam.travel_agency.bean.User;
import by.epam.travel_agency.service.receiver.ReceiverException;
import by.epam.travel_agency.service.validation.UserValidator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The type Entity builder helper.
 */
public class EntityBuilderHelper {

    private static final Logger logger = Logger.getLogger(EntityBuilderHelper.class);

    private static final String SUCCESS = "ok";
    private static final String MESSAGE = "message";
    private static final String PARAM_MARKER_UNPAID = "unpaid";

    /**
     * Make tour from HttpServletRequest .
     *
     * @param request the request
     * @return the tour
     */
    public static Tour makeTourFromRequest(HttpServletRequest request) {
        final String PARAM_HOTEL_ID = "hotel";
        final String PARAM_TOUR_TITLE = "title";
        final String PARAM_TOUR_PRICE = "price";
        final String PARAM_TOUR_TYPE = "typeOfTour";
        final String PARAM_TOUR_HOTTOUR = "hotTour";
        final String PARAM_TOUR_PLACES = "numberOfPlaces";
        final String PARAM_TOUR_DATESTART = "dateStart";
        final String PARAM_TOUR_DATEEND = "dateEnd";
        final String PARAM_TOUR_DISCOUNT = "discount";
        final String PARAM_TOUR_DESCRIPTION = "description";
        final String PARAM_TOUR_URL_WALLP = "url_wallpaper";
        final String PATTERN_LOCALDATE = "yyyy-MM-d";

        int hotel_id = Integer.parseInt(request.getParameter(PARAM_HOTEL_ID));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_LOCALDATE);
        DecimalFormat format = (DecimalFormat) DecimalFormat.getInstance();
        format.setParseBigDecimal(true);
        Tour tour = new Tour();
        Hotel hotel = new Hotel();
        hotel.setId(hotel_id);

        String title = request.getParameter(PARAM_TOUR_TITLE);
        BigDecimal price = (BigDecimal) format.parse(request.getParameter(PARAM_TOUR_PRICE), new ParsePosition(0));
        String typeOfTour = request.getParameter(PARAM_TOUR_TYPE);
        boolean hotTour = Boolean.parseBoolean(request.getParameter(PARAM_TOUR_HOTTOUR));
        int numberOfPlaces = Integer.parseInt(request.getParameter(PARAM_TOUR_PLACES));
        LocalDate dateStart = LocalDate.parse(request.getParameter(PARAM_TOUR_DATESTART), formatter);
        LocalDate dateEnd = LocalDate.parse(request.getParameter(PARAM_TOUR_DATEEND), formatter);
        int discount = Integer.parseInt(request.getParameter(PARAM_TOUR_DISCOUNT));
        String description = request.getParameter(PARAM_TOUR_DESCRIPTION);
        String url_wallpaper = request.getParameter(PARAM_TOUR_URL_WALLP);

        tour.setTitle(title);
        tour.setPrice(price);
        tour.setTypeOfTour(typeOfTour);
        tour.setHotTour(hotTour);
        tour.setNumberOfPlaces(numberOfPlaces);
        tour.setDateStart(dateStart);
        tour.setDateEnd(dateEnd);
        tour.setDiscount(discount);
        tour.setDescription(description);
        tour.setUrlWallpaper(url_wallpaper);
        tour.setHotel(hotel);

        return tour;
    }

    /**
     * Creat new user from HttpServletRequest.
     *
     * @param request the request
     * @return the user
     * @throws ReceiverException the receiver exception
     */
    public static User creatNewUserFromRequest(HttpServletRequest request) throws ReceiverException {

        final String PARAM_NAME_LOGIN = "login";
        final String PARAM_NAME_PASSWORD = "password";
        final String PARAM_NAME_EMAIL = "email";
        final String PARAM_NAME_FIRSTNAME = "firstname";
        final String PARAM_NAME_LASTNAME = "lastname";
        final String PARAM_NAME_PHONE = "phone";

        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        String email = request.getParameter(PARAM_NAME_EMAIL);
        String firstname = request.getParameter(PARAM_NAME_FIRSTNAME);
        String lastname = request.getParameter(PARAM_NAME_LASTNAME);
        String phone = request.getParameter(PARAM_NAME_PHONE);

        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setEmail(email);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setPhone(phone);
        user.setIdDiscount(1);
        user.setLevelAccess(2);

        String validationMessage = UserValidator.validateUserToMatchThePattern(user);

        if (!validationMessage.equals(SUCCESS)) {
            request.setAttribute(MESSAGE, validationMessage);
            logger.info(validationMessage);
        }
        return user;
    }

    /**
     * Create defrayal for anonim defrayal.
     *
     * @param name    the name
     * @param phone   the phone
     * @param tour_id the tour id
     * @return the defrayal
     */
    public static Defrayal createDefrayalForAnonim(String name, String phone, int tour_id) {
        final int PARAM_LEVEL_ACCESS = 2;
        final int PARAM_DISCOUNT = 1;
        final int PARAM_ZERO_PAYMENT = 0;

        Defrayal defrayal = new Defrayal();
        User user = new User();

        user.setFirstname(name);
        user.setLastname(name);
        user.setPhone(phone);
        user.setLogin(name);
        user.setPassword(name);
        user.setLevelAccess(PARAM_LEVEL_ACCESS);
        user.setIdDiscount(PARAM_DISCOUNT);

        Tour tour = new Tour();
        tour.setId(tour_id);

        defrayal.setTour(tour);
        defrayal.setAnnotation(PARAM_MARKER_UNPAID);
        defrayal.setUser(user);
        defrayal.setDiscount(PARAM_DISCOUNT);
        defrayal.setPaymentPercentage(PARAM_ZERO_PAYMENT);
        defrayal.setDateOfPayment(LocalDate.now());

        return defrayal;
    }

    /**
     * Create defrayal for user defrayal.
     *
     * @param user    the user
     * @param tour_id the tour id
     * @return the defrayal
     */
    public static Defrayal createDefrayalForUser(User user, int tour_id) {
        final int PARAM_DISCOUNT = 1;
        final int PARAM_ZERO_PAYMENT = 0;

        Defrayal defrayal = new Defrayal();

        Tour tour = new Tour();
        tour.setId(tour_id);

        defrayal.setDateOfPayment(LocalDate.now());
        defrayal.setTour(tour);
        defrayal.setPaymentPercentage(PARAM_ZERO_PAYMENT);
        defrayal.setDiscount(PARAM_DISCOUNT);
        defrayal.setUser(user);
        defrayal.setAnnotation(PARAM_MARKER_UNPAID);

        return defrayal;
    }
}
