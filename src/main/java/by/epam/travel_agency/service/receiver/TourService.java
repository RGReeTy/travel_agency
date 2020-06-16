package by.epam.travel_agency.service.receiver;

import by.epam.travel_agency.bean.Hotel;
import by.epam.travel_agency.bean.Request;
import by.epam.travel_agency.bean.Tour;
import by.epam.travel_agency.bean.User;

import java.util.List;
import java.util.Map;
import java.util.Set;


public interface TourService {

    Set<Tour> getAllTours() throws ReceiverException;

    Set<Tour> getConcreteTypeTours(String typeOfTour) throws ReceiverException;

    Set<Hotel> getAllHotels() throws ReceiverException;

    Set<Request> getAllRequestsForUser(User user) throws ReceiverException;

    List<Request> getAllRequests() throws ReceiverException;

    List<Request> getAllRequestsWhereIsDebt() throws ReceiverException;

    boolean addNewTourToDB(Tour tour) throws ReceiverException;

    Map<Integer, String> getAllTourTypesFromDB() throws ReceiverException;

    Map<Integer, Integer> getDiscountMapFromDB() throws ReceiverException;

}