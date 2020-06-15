package by.epam.travel_agency.dao.factory;

import by.epam.travel_agency.dao.factory.impl.SQLDAOFactory;

public class DAOFactoryProvider {

    private static final DAOFactory sqlDaoFactory = SQLDAOFactory.getInstance();
    private static final DAOFactoryProvider daoFactoryProvider = new DAOFactoryProvider();

    private DAOFactoryProvider() {
    }

    public static DAOFactoryProvider getInstance() {
        return daoFactoryProvider;
    }


    public static DAOFactory getSqlDaoFactory() {
        return sqlDaoFactory;
    }
}
