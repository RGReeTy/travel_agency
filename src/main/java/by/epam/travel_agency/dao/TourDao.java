package by.epam.travel_agency.dao;

import by.epam.travel_agency.bean.Hotel;
import by.epam.travel_agency.bean.Request;
import by.epam.travel_agency.bean.Tour;

import java.util.Set;


public interface TourDao<T> {

    Set<Tour> showAllTours() throws DAOTourException;

    Set<Tour> showConcreteTypeTours(String typeOfTour) throws DAOTourException;

    Set<Hotel> showAllHotels() throws DAOTourException;

    Set<Request> getAllRequestsByUserId(int id) throws DAOTourException;

    Set<Request> getAllRequestsByUserLogin(String login) throws DAOTourException;
}