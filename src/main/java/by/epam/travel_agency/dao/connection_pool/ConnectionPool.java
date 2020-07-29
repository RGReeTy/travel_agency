package by.epam.travel_agency.dao.connection_pool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public interface ConnectionPool {

    void initPool() throws ConnectionPoolException;

    void dispose() throws ConnectionPoolException;

    Connection takeConnection() throws ConnectionPoolException;

    void closeConnection(Connection con, Statement stm, ResultSet resultSet);

    void closeConnection(Connection con, Statement stm);
}
