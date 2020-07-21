package by.epam.travel_agency.service.receiver;

import by.epam.travel_agency.bean.Defrayal;
import by.epam.travel_agency.bean.Hotel;
import by.epam.travel_agency.bean.Tour;
import by.epam.travel_agency.bean.User;

import java.util.List;
import java.util.Map;
import java.util.Set;


public interface TourService {

    Set<Tour> getAllTours() throws ReceiverException;

    Set<Tour> getConcreteTypeTours(String typeOfTour) throws ReceiverException;

    Set<Hotel> getAllHotels() throws ReceiverException;

    Set<Defrayal> getAllDefrayalsForUser(User user) throws ReceiverException;

    List<Defrayal> getAllDefrayals() throws ReceiverException;

    List<Defrayal> getAllDefrayalsWhereIsDebt() throws ReceiverException;

    boolean addNewTourToDB(Tour tour) throws ReceiverException;

    Map<Integer, String> getAllTourTypesFromDB() throws ReceiverException;

    Map<Integer, Integer> getDiscountMapFromDB() throws ReceiverException;

    Tour getTourById(int id_tour) throws ReceiverException;

    boolean addNewDefrayal(Defrayal defrayal) throws ReceiverException;

    boolean addNewDefrayalMinimalInfo(Defrayal defrayal) throws ReceiverException;

}