package by.epam.travel_agency.dao.factory;

import by.epam.travel_agency.dao.factory.impl.SQLDAOFactory;

/**
 * The type Dao factory provider.
 */
public class DAOFactoryProvider {

    private static final DAOFactory sqlDaoFactory = SQLDAOFactory.getInstance();
    private static final DAOFactoryProvider daoFactoryProvider = new DAOFactoryProvider();

    private DAOFactoryProvider() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static DAOFactoryProvider getInstance() {
        return daoFactoryProvider;
    }


    /**
     * Gets sql dao factory.
     *
     * @return the sql dao factory
     */
    public static DAOFactory getSqlDaoFactory() {
        return sqlDaoFactory;
    }
}
