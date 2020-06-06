package by.epam.travel_agency.dao;

import by.epam.travel_agency.bean.Hotel;
import by.epam.travel_agency.bean.Request;
import by.epam.travel_agency.bean.Tour;
import by.epam.travel_agency.dao.exception.DAOTourException;

import java.util.HashMap;
import java.util.List;
import java.util.Set;


public interface TourDao<T> {

    Set<Tour> getAllTours() throws DAOTourException;

    Set<Tour> getConcreteTypeTours(String typeOfTour) throws DAOTourException;

    Set<Hotel> getAllHotels() throws DAOTourException;

    List<Request> getAllRequests() throws DAOTourException;

    List<Request> getAllRequestsWhereDebt() throws DAOTourException;

    Set<Request> getAllRequestsByUserId(int id) throws DAOTourException;

    Set<Request> getAllRequestsByUserLogin(String login) throws DAOTourException;

    int findMaxValueOfIDTour() throws DAOTourException;

    boolean addNewTour(Tour tour) throws DAOTourException;

    HashMap<Integer, String> getAllTourTypes() throws DAOTourException;

    HashMap<Integer, Integer> getDiscountsList() throws DAOTourException;
}