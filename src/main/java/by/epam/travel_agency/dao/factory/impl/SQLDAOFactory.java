package by.epam.travel_agency.dao.factory.impl;

import by.epam.travel_agency.dao.TourDAO;
import by.epam.travel_agency.dao.TourDAOImpl;
import by.epam.travel_agency.dao.UserDAO;
import by.epam.travel_agency.dao.UserDAOImpl;
import by.epam.travel_agency.dao.factory.DAOFactory;

/**
 * The type Sqldao factory.
 */
public class SQLDAOFactory implements DAOFactory {

    private final static SQLDAOFactory sqlDAOFactory = new SQLDAOFactory();
    private final static UserDAO userDao = new UserDAOImpl();
    private final static TourDAO tourDao = new TourDAOImpl();


    private SQLDAOFactory() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static SQLDAOFactory getInstance() {
        return sqlDAOFactory;
    }

    @Override
    public UserDAO getUserDao() {
        return userDao;
    }

    @Override
    public TourDAO getTourDao() {
        return tourDao;
    }
}
