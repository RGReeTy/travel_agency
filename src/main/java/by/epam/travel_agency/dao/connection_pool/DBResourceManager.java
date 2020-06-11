package by.epam.travel_agency.dao.connectionPool;

import java.util.ResourceBundle;

public class DBResourceManager {

    private static DBResourceManager instance;
    private ResourceBundle bundle = ResourceBundle.getBundle("connection_pool");

    private DBResourceManager() {
    }

    public static DBResourceManager getInstance() {

        if (instance == null) {
            instance = new DBResourceManager();
        }

        return instance;
    }

    String getValue(String key) {
        return bundle.getString(key);
    }
}
