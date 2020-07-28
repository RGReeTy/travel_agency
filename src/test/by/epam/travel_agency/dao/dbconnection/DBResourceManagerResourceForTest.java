package by.epam.travel_agency.dao.dbconnection;

import java.util.ResourceBundle;

/**
 * The type Db resource manager.
 */
public class DBResourceManagerResourceForTest {

    private final static DBResourceManagerResourceForTest instance = new DBResourceManagerResourceForTest();

    private ResourceBundle bundle = ResourceBundle.getBundle("testdb");

    public static DBResourceManagerResourceForTest getInstance() {
        return instance;
    }

    public String getValue(String key) {
        return bundle.getString(key);
    }

}
