package by.epam.travel_agency.service.factory;

import by.epam.travel_agency.service.receiver.TourService;
import by.epam.travel_agency.service.receiver.TourServiceImpl;
import by.epam.travel_agency.service.receiver.UserService;
import by.epam.travel_agency.service.receiver.UserServiceImpl;

/**
 * The type Service factory.
 */
public final class ServiceFactory {

    private static final ServiceFactory instance = new ServiceFactory();

    private final TourService testService = new TourServiceImpl();
    private final UserService userService = new UserServiceImpl();

    private ServiceFactory() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static ServiceFactory getInstance() {
        return instance;
    }

    /**
     * Gets tour service.
     *
     * @return the tour service
     */
    public TourService getTourService() {
        return testService;
    }

    /**
     * Gets user service.
     *
     * @return the user service
     */
    public UserService getUserService() {
        return userService;
    }

}
