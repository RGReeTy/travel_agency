package by.epam.travel_agency.dao;

import by.epam.travel_agency.bean.Tour;
import by.epam.travel_agency.bean.User;

import java.util.Set;


public interface TourDao<T> {

	Set<Tour> showAllTours();

}