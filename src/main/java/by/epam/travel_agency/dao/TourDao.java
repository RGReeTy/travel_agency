package by.epam.travel_agency.dao;

import by.epam.travel_agency.bean.Hotel;
import by.epam.travel_agency.bean.Request;
import by.epam.travel_agency.bean.Tour;

import java.util.Set;


public interface TourDao<T> {

    Set<Tour> showAllTours();

    Set<Tour> showConcreteTypeTours(String typeOfTour);

    Set<Hotel> showAllHotels();

    Set<Request> getAllRequestsByUserId(int id);

    Set<Request> getAllRequestsByUserLogin(String login);
}