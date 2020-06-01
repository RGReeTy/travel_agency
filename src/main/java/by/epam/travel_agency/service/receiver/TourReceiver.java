package by.epam.travel_agency.service.receiver;

import by.epam.travel_agency.bean.Hotel;
import by.epam.travel_agency.bean.Request;
import by.epam.travel_agency.bean.Tour;
import by.epam.travel_agency.bean.User;
import by.epam.travel_agency.dao.TourDaoImpl;
import by.epam.travel_agency.dao.exception.DAOTourException;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Set;

import static by.epam.travel_agency.service.manager.FinalPriceMaker.countFinalPriceForList;
import static by.epam.travel_agency.service.manager.FinalPriceMaker.countFinalPriceForSet;

public class TourReceiver {

    private static final Logger logger = Logger.getLogger(TourReceiver.class);

    private static final String IS_OK = "ok";

    private TourDaoImpl tourDao = TourDaoImpl.getInstance();

    private static TourReceiver instance = new TourReceiver();

    private TourReceiver() {
    }

    public static TourReceiver getInstance() {
        return instance;
    }

    public Set<Tour> getAllTours() throws ReceiverException {
        try {
            return instance.tourDao.getAllTours();
        } catch (DAOTourException e) {
            throw new ReceiverException(e);
        }
    }

    public Set<Tour> getConcreteTypeTours(String typeOfTour) throws ReceiverException {
        try {
            return instance.tourDao.getConcreteTypeTours(typeOfTour);
        } catch (DAOTourException e) {
            throw new ReceiverException(e);
        }
    }

    public Set<Hotel> getAllHotels() throws ReceiverException {
        try {
            return instance.tourDao.getAllHotels();
        } catch (DAOTourException e) {
            throw new ReceiverException(e);
        }
    }

    public Set<Request> getAllRequestsForUser(User user) throws ReceiverException {
        Set<Request> requestSet;
        try {
            if (user.getLogin() == null) {
                requestSet = instance.tourDao.getAllRequestsByUserId(user.getId_user());
            } else {
                requestSet = instance.tourDao.getAllRequestsByUserLogin(user.getLogin());
            }
        } catch (DAOTourException e) {
            throw new ReceiverException(e);
        }
        countFinalPriceForSet(requestSet);
        return requestSet;
    }

    public List<Request> getAllRequests() throws ReceiverException {
        List<Request> requestList;
        try {
            requestList = instance.tourDao.getAllRequests();
            logger.info(requestList.size());
        } catch (DAOTourException e) {
            logger.error(e);
            throw new ReceiverException(e);
        }
        countFinalPriceForList(requestList);
        return requestList;
    }


}