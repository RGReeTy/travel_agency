package by.epam.travel_agency.dao;

import by.epam.travel_agency.bean.Defrayal;
import by.epam.travel_agency.bean.Hotel;
import by.epam.travel_agency.bean.Tour;
import by.epam.travel_agency.dao.exception.DAOTourException;
import by.epam.travel_agency.dao.exception.GetIncorrectParameterException;

import java.util.HashMap;
import java.util.List;
import java.util.Set;


/**
 * The interface Tour dao.
 */
public interface TourDAO {

    /**
     * Add new tour boolean.
     *
     * @param tour the tour
     * @return the boolean
     * @throws DAOTourException the dao tour exception
     */
    boolean addNewTour(Tour tour) throws DAOTourException;

    /**
     * Gets all tours.
     *
     * @return the all tours
     * @throws DAOTourException the dao tour exception
     */
    Set<Tour> getAllTours() throws DAOTourException;

    /**
     * Gets concrete type tours.
     *
     * @param typeOfTour the type of tour
     * @return the concrete type tours
     * @throws DAOTourException the dao tour exception
     */
    Set<Tour> getConcreteTypeTours(String typeOfTour) throws DAOTourException, GetIncorrectParameterException;

    /**
     * Find max value of id tour int.
     *
     * @return the int
     * @throws DAOTourException the dao tour exception
     */
    int findMaxValueOfIDTour() throws DAOTourException;

    /**
     * Find max value of id defrayal int.
     *
     * @return the int
     * @throws DAOTourException the dao tour exception
     */
    int findMaxValueOfIDDefrayal() throws DAOTourException;

    /**
     * Gets all tour types.
     *
     * @return the all tour types
     * @throws DAOTourException the dao tour exception
     */
    HashMap<Integer, String> getAllTourTypes() throws DAOTourException;

    /**
     * Gets tour by id.
     *
     * @param idTour the id tour
     * @return the tour by id
     * @throws DAOTourException the dao tour exception
     */
    Tour getTourById(int idTour) throws DAOTourException, GetIncorrectParameterException;


    /**
     * Gets all hotels.
     *
     * @return the all hotels
     * @throws DAOTourException the dao tour exception
     */
    Set<Hotel> getAllHotels() throws DAOTourException;

    /**
     * Add new defrayal boolean.
     *
     * @param defrayal the defrayal
     * @return the boolean
     * @throws DAOTourException the dao tour exception
     */
    boolean addNewDefrayal(Defrayal defrayal) throws DAOTourException;

    /**
     * Gets all defrayals.
     *
     * @return the all defrayals
     * @throws DAOTourException the dao tour exception
     */
    List<Defrayal> getAllDefrayals() throws DAOTourException;

    /**
     * Gets all defrayals where debt.
     *
     * @return the all defrayals where debt
     * @throws DAOTourException the dao tour exception
     */
    List<Defrayal> getAllDefrayalsWhereDebt() throws DAOTourException;

    /**
     * Gets all defrayals by user id.
     *
     * @param id the id
     * @return the all defrayals by user id
     * @throws DAOTourException the dao tour exception
     */
    Set<Defrayal> getAllDefrayalsByUserId(int id) throws DAOTourException, GetIncorrectParameterException;

    /**
     * Gets all defrayals by user login.
     *
     * @param login the login
     * @return the all defrayals by user login
     * @throws DAOTourException the dao tour exception
     */
    Set<Defrayal> getAllDefrayalsByUserLogin(String login) throws DAOTourException, GetIncorrectParameterException;

    /**
     * Gets defrayal by id.
     *
     * @param defrayalId the defrayal id
     * @return the defrayal by id
     * @throws DAOTourException the dao tour exception
     */
    Defrayal getDefrayalById(int defrayalId) throws DAOTourException, GetIncorrectParameterException;

    /**
     * Update defrayal by id boolean.
     *
     * @param defrayal the defrayal
     * @return the boolean
     * @throws DAOTourException the dao tour exception
     */
    boolean updateDefrayalById(Defrayal defrayal) throws DAOTourException;


    /**
     * Gets discounts list.
     *
     * @return the discounts list
     * @throws DAOTourException the dao tour exception
     */
    HashMap<Integer, Integer> getDiscountsList() throws DAOTourException;


}