package by.epam.travel_agency.dao.dbconnection;

import by.epam.travel_agency.dao.TourDAO;
import by.epam.travel_agency.dao.connection_pool.ConnectionPool;
import by.epam.travel_agency.dao.factory.DAOFactoryProvider;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.internal.util.reflection.Whitebox;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;


@RunWith(Theories.class)
public class TourDAOImplTest {

    private static final Logger logger = Logger.getLogger(TourDAOImplTest.class);

    @Mock
    private static ConnectionPool mockedConnectionPool;

    private static TourDAO testTourDAO  = DAOFactoryProvider.getSqlDaoFactory().getTourDao();

}
