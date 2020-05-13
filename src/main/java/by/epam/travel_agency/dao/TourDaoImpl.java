package by.epam.travel_agency.dao;

import by.epam.travel_agency.bean.Hotel;
import by.epam.travel_agency.bean.Tour;
import by.epam.travel_agency.dao.connection_pool.ConnectionPool;
import by.epam.travel_agency.dao.connection_pool.ConnectionPoolException;
import by.epam.travel_agency.dao.connection_pool.ConnectionPoolFactory;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

public class TourDaoImpl implements TourDao {

    private static final Logger logger = Logger.getLogger(TourDaoImpl.class);

    private static final String SELECT_ALL_TOURS = "SELECT * FROM bustravelagency.tours";
    private static final String SELECT_ALL_TOURS_JOIN = "SELECT id_Tour, tours.Title, TypeOfTour, Price, " +
            "Size_of_discount, Hot_tour, Number_of_places, Date_start, Date_end, hotel.Title AS 'Hotel'" +
            "        FROM bustravelagency.tours" +
            "        JOIN typeoftour ON Type=id_TypeOfTour" +
            "        JOIN discount ON tours.id_Discount = discount.id_Discount" +
            "        JOIN hotel ON tours.id_Hotel = hotel.id_Hotel";

    private static TourDaoImpl instance = new TourDaoImpl();

    private TourDaoImpl() {
    }

    public static TourDaoImpl getInstance() {
        return instance;
    }


    @Override
    public Set<Tour> showAllTours() {
        ConnectionPoolFactory connectionPoolFactory = ConnectionPoolFactory.getInstance();
        ConnectionPool connectionPool = connectionPoolFactory.getConnectionPool();
        Connection con = null;
        Set<Tour> tourSet = new HashSet<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
        try {
            connectionPool.initPoolData();
            con = connectionPool.takeConnection();
        } catch (ConnectionPoolException e) {
            logger.debug(e);
        }
        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(SELECT_ALL_TOURS_JOIN);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                Tour tour = new Tour();
                Hotel hotel = new Hotel();
                tour.setId(resultSet.getInt("id_Tour"));
                tour.setTitle(resultSet.getString("Title"));
                tour.setTypeOfTour(resultSet.getString("TypeOfTour"));
                tour.setPrice(resultSet.getBigDecimal("Price"));
                tour.setDiscount(resultSet.getInt("Size_of_discount"));
                tour.setHotTour(resultSet.getBoolean("Hot_tour"));
                tour.setNumberOfPlaces(resultSet.getInt("Number_of_places"));
                tour.setDateStart(LocalDate.parse(resultSet.getString("Date_start"), formatter));
                tour.setDateEnd(LocalDate.parse(resultSet.getString("Date_end"), formatter));
                hotel.setTitle(resultSet.getString("Hotel"));
                tour.setHotel(hotel);
                tourSet.add(tour);
                logger.info(tour);
            }
        } catch (SQLException e) {
            logger.debug(e);
        }
        logger.info(tourSet.size());
        return tourSet;
    }

}
