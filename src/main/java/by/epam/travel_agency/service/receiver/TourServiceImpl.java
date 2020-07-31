package by.epam.travel_agency.service.receiver;

import by.epam.travel_agency.bean.Defrayal;
import by.epam.travel_agency.bean.Hotel;
import by.epam.travel_agency.bean.Tour;
import by.epam.travel_agency.bean.User;
import by.epam.travel_agency.controller.paramName.RequestParameterName;
import by.epam.travel_agency.dao.TourDAO;
import by.epam.travel_agency.dao.exception.DAOTourException;
import by.epam.travel_agency.dao.exception.GetIncorrectParameterException;
import by.epam.travel_agency.dao.factory.DAOFactory;
import by.epam.travel_agency.dao.factory.DAOFactoryProvider;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static by.epam.travel_agency.service.util.FinalPriceMaker.*;
import static by.epam.travel_agency.service.validation.ParamValidator.notEmpty;
import static by.epam.travel_agency.service.validation.ParamValidator.validatePositiveNumber;


/**
 * The type Tour service.
 */
public class TourServiceImpl implements TourService {

    private static final Logger logger = Logger.getLogger(TourServiceImpl.class);

    private final DAOFactory daoFactory = DAOFactoryProvider.getSqlDaoFactory();
    private TourDAO tourDao = daoFactory.getTourDao();

    private final int INCREMENT = 1;

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
        notEmpty(typeOfTour);
        try {
            return tourDao.getConcreteTypeTours(typeOfTour);
        } catch (DAOTourException | GetIncorrectParameterException e) {
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

        try {
            if (user.getIdUser() != 0) {
                defrayalSet = tourDao.getAllDefrayalsByUserId(user.getIdUser());
            } else {
                defrayalSet = tourDao.getAllDefrayalsByUserLogin(user.getLogin());
            }
        } catch (DAOTourException | GetIncorrectParameterException e) {
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
        logger.info(defrayalList.size());

        countFinalPriceForList(defrayalList);
        deleteCompleteRequest(defrayalList);

        logger.info(defrayalList.size());
        return defrayalList;
    }

    @Override
    public boolean addNewTourToDB(Tour tour) throws ReceiverException {
        boolean isSuccessfullyCreateNewTour = false;

        try {
            tour.setId(tourDao.findMaxValueOfIDTour() + INCREMENT);
            isSuccessfullyCreateNewTour = tourDao.addNewTour(tour);
        } catch (DAOTourException e) {
            logger.error(e);
            throw new ReceiverException(e);
        }
        logger.info(isSuccessfullyCreateNewTour);
        return isSuccessfullyCreateNewTour;
    }

    public boolean addNewDefrayal(Defrayal defrayal) throws ReceiverException {
        boolean isSuccessfullyCreateNewDefrayal = false;

        try {
            defrayal.setId(tourDao.findMaxValueOfIDDefrayal() + INCREMENT);

            logger.info("addNewDefrayal = defrayal set id = " + defrayal.getId());

            isSuccessfullyCreateNewDefrayal = tourDao.addNewDefrayal(defrayal);
        } catch (DAOTourException e) {
            logger.error(e);
            throw new ReceiverException(e);
        }
        logger.info(isSuccessfullyCreateNewDefrayal);

        return isSuccessfullyCreateNewDefrayal;
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

    @Override
    public Tour getTourById(int idTour) throws ReceiverException {
        try {
            if (validatePositiveNumber(idTour)) {

                return tourDao.getTourById(idTour);

            } else {

                throw new ReceiverException("Id tour must be more than zero!");
            }

        } catch (DAOTourException | GetIncorrectParameterException e) {

            logger.error(e);

            throw new ReceiverException(e);
        }
    }

    @Override
    public Defrayal getDefrayalById(int defrayalId) throws ReceiverException {

        try {
            if (validatePositiveNumber(defrayalId)) {

                return tourDao.getDefrayalById(defrayalId);

            } else {

                throw new ReceiverException("Id tour must be more than zero!");
            }

        } catch (DAOTourException | GetIncorrectParameterException e) {

            logger.error(e);

            throw new ReceiverException(e);
        }
    }

    @Override
    public boolean updateDefrayalById(int defrayalId) throws ReceiverException {
        final int HUNDRED = 100;

        if (!validatePositiveNumber(defrayalId)) {
            throw new ReceiverException("Incorrect parameter");
        }

        Defrayal defrayal = getDefrayalById(defrayalId);

        if (defrayal != null) {
            defrayal.setPaymentPercentage(HUNDRED);
            defrayal.setAnnotation(RequestParameterName.CONFIRMED);
        } else {
            throw new ReceiverException("Defrayal from DB == null!");
        }

        logger.info("service updateDefrayalById. Defrayal =  " + defrayal.toString() + "; anno = " + defrayal.getAnnotation());

        try {
            return tourDao.updateDefrayalById(defrayal);
        } catch (DAOTourException e) {
            logger.error(e);
            throw new ReceiverException(e);
        }
    }

}