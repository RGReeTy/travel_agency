package by.epam.travel_agency.service.receiver;

import by.epam.travel_agency.bean.Hotel;
import by.epam.travel_agency.bean.Request;
import by.epam.travel_agency.bean.Tour;
import by.epam.travel_agency.bean.User;
import by.epam.travel_agency.dao.TourDAO;
import by.epam.travel_agency.dao.exception.DAOTourException;
import by.epam.travel_agency.dao.factory.DAOFactory;
import by.epam.travel_agency.dao.factory.DAOFactoryProvider;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static by.epam.travel_agency.service.util.FinalPriceMaker.*;


public class TourServiceImpl implements TourService {

    private static final Logger logger = Logger.getLogger(TourServiceImpl.class);

    private final DAOFactory daoFactory = DAOFactoryProvider.getSqlDaoFactory();
    private TourDAO tourDao = daoFactory.getTourDao();

    private static final String IS_OK = "ok";

    @Override
    public Set<Tour> getAllTours() throws ReceiverException {
        try {
            return tourDao.getAllTours();
        } catch (DAOTourException e) {
            throw new ReceiverException(e);
        }
    }

    @Override
    public Set<Tour> getConcreteTypeTours(String typeOfTour) throws ReceiverException {
        try {
            return tourDao.getConcreteTypeTours(typeOfTour);
        } catch (DAOTourException e) {
            throw new ReceiverException(e);
        }
    }

    @Override
    public Set<Hotel> getAllHotels() throws ReceiverException {
        try {
            return tourDao.getAllHotels();
        } catch (DAOTourException e) {
            throw new ReceiverException(e);
        }
    }

    @Override
    public Set<Request> getAllRequestsForUser(User user) throws ReceiverException {
        Set<Request> requestSet;
        logger.info("getAllRequestsForUser start working");
        try {
            if (user.getId_user() != 0) {
                requestSet = tourDao.getAllRequestsByUserId(user.getId_user());
            } else {
                requestSet = tourDao.getAllRequestsByUserLogin(user.getLogin());
            }
        } catch (DAOTourException e) {
            throw new ReceiverException(e);
        }
        countFinalPriceForSet(requestSet);
        logger.info("Number of requests: " + requestSet.size());
        logger.info("getAllRequestsForUser end working");

        return requestSet;
    }

    @Override
    public List<Request> getAllRequests() throws ReceiverException {
        List<Request> requestList;
        try {
            requestList = tourDao.getAllRequests();
            logger.info(requestList.size());
        } catch (DAOTourException e) {
            logger.error(e);
            throw new ReceiverException(e);
        }
        countFinalPriceForList(requestList);
        return requestList;
    }

    @Override
    public List<Request> getAllRequestsWhereIsDebt() throws ReceiverException {
        List<Request> requestList;
        try {
            requestList = tourDao.getAllRequestsWhereDebt();
        } catch (DAOTourException e) {
            logger.error(e);
            throw new ReceiverException(e);
        }
        countFinalPriceForList(requestList);
        deleteCompleteRequest(requestList);
        return requestList;
    }

    @Override
    public boolean addNewTourToDB(Tour tour) throws ReceiverException {
        logger.info("addNewTourToDB is start");
        boolean isSuccessfullyCreateNewTour = false;

        try {
            tour.setId(tourDao.findMaxValueOfIDTour() + 1);
            isSuccessfullyCreateNewTour = tourDao.addNewTour(tour);
        } catch (DAOTourException e) {
            logger.error(e);
            throw new ReceiverException(e);
        }
        logger.info(isSuccessfullyCreateNewTour);
        return isSuccessfullyCreateNewTour;
    }

    @Override
    public Map<Integer, String> getAllTourTypesFromDB() throws ReceiverException {
        try {
            return tourDao.getAllTourTypes();
        } catch (DAOTourException e) {
            logger.error(e);
            throw new ReceiverException(e);
        }
    }

    @Override
    public Map<Integer, Integer> getDiscountMapFromDB() throws ReceiverException {
        try {
            return tourDao.getDiscountsList();
        } catch (DAOTourException e) {
            logger.error(e);
            throw new ReceiverException(e);
        }
    }

}