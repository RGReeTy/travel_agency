package by.epam.travel_agency.dao;

import by.epam.travel_agency.bean.Defrayal;
import by.epam.travel_agency.bean.Hotel;
import by.epam.travel_agency.bean.Tour;
import by.epam.travel_agency.dao.exception.DAOTourException;

import java.util.HashMap;
import java.util.List;
import java.util.Set;


public interface TourDAO {

    boolean addNewTour(Tour tour) throws DAOTourException;

    Set<Tour> getAllTours() throws DAOTourException;

    Set<Tour> getConcreteTypeTours(String typeOfTour) throws DAOTourException;

    int findMaxValueOfIDTour() throws DAOTourException;

    HashMap<Integer, String> getAllTourTypes() throws DAOTourException;

    Tour getTourById(int id_tour) throws DAOTourException;


    Set<Hotel> getAllHotels() throws DAOTourException;


    boolean addNewDefrayal(Defrayal defrayal) throws DAOTourException;

    List<Defrayal> getAllDefrayals() throws DAOTourException;

    List<Defrayal> getAllDefrayalsWhereDebt() throws DAOTourException;

    Set<Defrayal> getAllDefrayalsByUserId(int id) throws DAOTourException;

    Set<Defrayal> getAllDefrayalsByUserLogin(String login) throws DAOTourException;


    HashMap<Integer, Integer> getDiscountsList() throws DAOTourException;


}