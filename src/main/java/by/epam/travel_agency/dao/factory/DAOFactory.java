package by.epam.travel_agency.dao.factory;

import by.epam.travel_agency.dao.TourDAO;
import by.epam.travel_agency.dao.UserDAO;

/**
 * The interface Dao factory.
 */
public interface DAOFactory {

    /**
     * Gets user dao.
     *
     * @return the user dao
     */
    UserDAO getUserDao();

    /**
     * Gets tour dao.
     *
     * @return the tour dao
     */
    TourDAO getTourDao();
}
