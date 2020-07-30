package by.epam.travel_agency.service.receiver;

import by.epam.travel_agency.bean.Defrayal;
import by.epam.travel_agency.bean.Hotel;
import by.epam.travel_agency.bean.Tour;
import by.epam.travel_agency.bean.User;

import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * The interface Tour service.
 */
public interface TourService {

    /**
     * Gets all tours.
     *
     * @return the all tours
     * @throws ReceiverException the receiver exception
     */
    Set<Tour> getAllTours() throws ReceiverException;

    /**
     * Gets concrete type tours.
     *
     * @param typeOfTour the type of tour
     * @return the concrete type tours
     * @throws ReceiverException the receiver exception
     */
    Set<Tour> getConcreteTypeTours(String typeOfTour) throws ReceiverException;

    /**
     * Gets all hotels.
     *
     * @return the all hotels
     * @throws ReceiverException the receiver exception
     */
    Set<Hotel> getAllHotels() throws ReceiverException;

    /**
     * Gets all defrayals for user.
     *
     * @param user the user
     * @return the all defrayals for user
     * @throws ReceiverException the receiver exception
     */
    Set<Defrayal> getAllDefrayalsForUser(User user) throws ReceiverException;

    /**
     * Gets all defrayals.
     *
     * @return the all defrayals
     * @throws ReceiverException the receiver exception
     */
    List<Defrayal> getAllDefrayals() throws ReceiverException;

    /**
     * Gets all defrayals where is debt.
     *
     * @return the all defrayals where is debt
     * @throws ReceiverException the receiver exception
     */
    List<Defrayal> getAllDefrayalsWhereIsDebt() throws ReceiverException;

    /**
     * Add new tour to db boolean.
     *
     * @param tour the tour
     * @return the boolean
     * @throws ReceiverException the receiver exception
     */
    boolean addNewTourToDB(Tour tour) throws ReceiverException;

    /**
     * Gets all tour types from db.
     *
     * @return the all tour types from db
     * @throws ReceiverException the receiver exception
     */
    Map<Integer, String> getAllTourTypesFromDB() throws ReceiverException;

    /**
     * Gets discount map from db.
     *
     * @return the discount map from db
     * @throws ReceiverException the receiver exception
     */
    Map<Integer, Integer> getDiscountMapFromDB() throws ReceiverException;

    /**
     * Gets tour by id.
     *
     * @param idTour the id tour
     * @return the tour by id
     * @throws ReceiverException the receiver exception
     */
    Tour getTourById(int idTour) throws ReceiverException;

    /**
     * Add new defrayal boolean.
     *
     * @param defrayal the defrayal
     * @return the boolean
     * @throws ReceiverException the receiver exception
     */
    boolean addNewDefrayal(Defrayal defrayal) throws ReceiverException;

    /**
     * Gets defrayal by id.
     *
     * @param defrayalId the defrayal id
     * @return the defrayal by id
     * @throws ReceiverException the receiver exception
     */
    Defrayal getDefrayalById(int defrayalId) throws ReceiverException;

    /**
     * Update defrayal by id boolean.
     *
     * @param defrayalId the defrayal id
     * @return the boolean
     * @throws ReceiverException the receiver exception
     */
    boolean updateDefrayalById(int defrayalId) throws ReceiverException;


}