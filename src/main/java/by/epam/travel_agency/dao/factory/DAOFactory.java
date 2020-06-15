package by.epam.travel_agency.dao.factory;

import by.epam.travel_agency.dao.TourDAO;
import by.epam.travel_agency.dao.UserDAO;

public interface DAOFactory {

    UserDAO getUserDao();

    TourDAO getTourDao();
}
