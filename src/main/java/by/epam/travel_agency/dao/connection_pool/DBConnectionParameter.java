package by.epam.travel_agency.dao.connection_pool;

public enum DBConnectionParameter {

    DRIVER("driver"),
    URL("url"),
    USER("user"),
    PASSWORD("password"),
    POOL_SIZE("pool_size");

    private String key;

    DBConnectionParameter(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

}
