package by.epam.travel_agency.service.receiver;

import by.epam.travel_agency.bean.Defrayal;
import by.epam.travel_agency.bean.Hotel;
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
    public Set<Defrayal> getAllDefrayalsForUser(User user) throws ReceiverException {
        Set<Defrayal> defrayalSet;
        logger.info("getAllRequestsForUser start working");
        try {
            if (user.getId_user() != 0) {
                defrayalSet = tourDao.getAllDefrayalsByUserId(user.getId_user());
            } else {
                defrayalSet = tourDao.getAllDefrayalsByUserLogin(user.getLogin());
            }
        } catch (DAOTourException e) {
            throw new ReceiverException(e);
        }
        countFinalPriceForSet(defrayalSet);
        logger.info("Number of requests: " + defrayalSet.size());
        logger.info("getAllRequestsForUser end working");

        return defrayalSet;
    }

    @Override
    public List<Defrayal> getAllDefrayals() throws ReceiverException {
        List<Defrayal> defrayalList;
        try {
            defrayalList = tourDao.getAllDefrayals();
            logger.info(defrayalList.size());
        } catch (DAOTourException e) {
            logger.error(e);
            throw new ReceiverException(e);
        }
        countFinalPriceForList(defrayalList);
        return defrayalList;
    }

    @Override
    public List<Defrayal> getAllDefrayalsWhereIsDebt() throws ReceiverException {
        List<Defrayal> defrayalList;
        try {
            defrayalList = tourDao.getAllDefrayalsWhereDebt();
        } catch (DAOTourException e) {
            logger.error(e);
            throw new ReceiverException(e);
        }
        countFinalPriceForList(defrayalList);
        deleteCompleteRequest(defrayalList);
        return defrayalList;
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