package by.epam.travel_agency.service.factory;

import by.epam.travel_agency.service.receiver.TourService;
import by.epam.travel_agency.service.receiver.TourServiceImpl;
import by.epam.travel_agency.service.receiver.UserService;
import by.epam.travel_agency.service.receiver.UserServiceImpl;

public final class ServiceFactory {

    private static final ServiceFactory instance = new ServiceFactory();

    private final TourService testService = new TourServiceImpl();
    private final UserService userService = new UserServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public TourService getTourService() {
        return testService;
    }

    public UserService getUserService() {
        return userService;
    }

}
