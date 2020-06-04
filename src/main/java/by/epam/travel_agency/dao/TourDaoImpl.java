package by.epam.travel_agency.dao;

import by.epam.travel_agency.bean.*;
import by.epam.travel_agency.dao.connectionPool.ConnectionPool;
import by.epam.travel_agency.dao.connectionPool.ConnectionPoolException;
import by.epam.travel_agency.dao.exception.DAOTourException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
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
    private static final String SELECT_CONCRETE_TOURS_JOIN = "SELECT id_Tour, tours.Title, TypeOfTour, Price, " +
            "Size_of_discount, Hot_tour, Number_of_places, Date_start, Date_end, hotel.Title AS 'Hotel'" +
            "        FROM bustravelagency.tours" +
            "        JOIN typeoftour ON Type=id_TypeOfTour" +
            "        JOIN discount ON tours.id_Discount = discount.id_Discount" +
            "        JOIN hotel ON tours.id_Hotel = hotel.id_Hotel WHERE TypeOfTour = ?";
    private static final String SELECT_ALL_HOTELS = "SELECT id_Hotel, Title, country, City, Stars, Free_rooms," +
            "Type FROM hotel JOIN nutrition ON Nutrition=id_Nutrition";
    private static final String SELECT_ALL_TOURS_BY_USER_ID = "SELECT tours.id_Tour, tours.Title, TypeOfTour, " +
            "Price, Size_of_discount, Hot_tour, Number_of_places, Date_start, Date_end, hotel.Title AS 'Hotel'," +
            " request.Date_of_payment\n" +
            "FROM bustravelagency.tours JOIN typeoftour ON Type=id_TypeOfTour\n" +
            "JOIN discount ON tours.id_Discount = discount.id_Discount\n" +
            "JOIN request ON request.Id_Tour = tours.id_Tour\n" +
            "JOIN hotel ON tours.id_Hotel = hotel.id_Hotel WHERE Id_User = ?";
    private static final String SELECT_ALL_REQUEST_FOR_USER_BY_USER_ID = "SELECT id_Request, Date_of_payment, Title, Count, Payment_percentage, request.Id_User,\n" +
            "      Login, Size_of_discount FROM bustravelagency.request JOIN tours ON  request.Id_Tour=tours.id_Tour\n" +
            "    JOIN discount ON request.id_Discount=discount.id_Discount " +
            "JOIN users ON request.Id_User=users.id_User WHERE request.Id_User =  ?";
    private static final String SELECT_ALL_REQUEST = "SELECT id_Request, Date_of_payment, Title, Count, Payment_percentage, request.Id_User, Login,\n" +
            "Size_of_discount FROM bustravelagency.request JOIN tours ON request.Id_Tour=tours.id_Tour\n" +
            "    JOIN users ON request.Id_User=users.id_User\n" +
            "JOIN discount ON request.id_Discount=discount.id_Discount ORDER BY Date_of_payment";
    private static final String SELECT_ALL_REQUEST_WHERE_IS_DEBT = "SELECT id_Request, Date_of_payment, Title, Count, Payment_percentage, request.Id_User, Login,\n" +
            "Size_of_discount FROM bustravelagency.request JOIN tours ON request.Id_Tour=tours.id_Tour\n" +
            "JOIN users ON request.Id_User=users.id_User JOIN discount ON request.id_Discount=discount.id_Discount\n" +
            "    WHERE Payment_percentage!=100;";
    private static final String SELECT_ALL_REQUEST_FOR_USER_BY_USER_LOGIN = "SELECT id_Request, Date_of_payment, Title, Count, Payment_percentage, Login,\n" +
            "       Size_of_discount, users.id_User FROM bustravelagency.request JOIN tours ON  request.Id_Tour=tours.id_Tour\n" +
            "    JOIN discount ON request.id_Discount=discount.id_Discount\n" +
            "    JOIN users ON request.Id_User=users.id_User WHERE Login = ?";

    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static TourDaoImpl instance = new TourDaoImpl();

    private TourDaoImpl() {
    }

    public static TourDaoImpl getInstance() {
        return instance;
    }

    @Override
    public List<Request> getAllRequests() throws DAOTourException {
        Connection con = null;
        Statement stmt = null;
        ResultSet resultSet = null;
        List<Request> requestList = new LinkedList<>();
        try {
            con = connectionPool.takeConnection();
            stmt = con.createStatement();
            resultSet = stmt.executeQuery(SELECT_ALL_REQUEST);
            while (resultSet.next()) {
                requestList.add(creatingRequestFromResultSet(resultSet));
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            throw new DAOTourException(e);
        } finally {
            assert con != null;
            connectionPool.closeConnection(con, stmt, resultSet);
        }
        logger.info(requestList.size());
        return requestList;
    }

    @Override
    public List<Request> getAllRequestsWhereDebt() throws DAOTourException {
        Connection con = null;
        Statement stmt = null;
        ResultSet resultSet = null;
        List<Request> requestList = new LinkedList<>();
        try {
            con = connectionPool.takeConnection();
            stmt = con.createStatement();
            resultSet = stmt.executeQuery(SELECT_ALL_REQUEST_WHERE_IS_DEBT);
            while (resultSet.next()) {
                requestList.add(creatingRequestFromResultSet(resultSet));
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            throw new DAOTourException(e);
        } finally {
            assert con != null;
            connectionPool.closeConnection(con, stmt, resultSet);
        }
        logger.info(requestList.size());
        return requestList;
    }

    @Override
    public Set<Request> getAllRequestsByUserId(int id) throws DAOTourException {
        Connection con = null;
        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;
        Set<Request> requestSet = new HashSet<>();
        try {
            con = connectionPool.takeConnection();
            prepareStatement = con.prepareStatement(SELECT_ALL_REQUEST_FOR_USER_BY_USER_ID);
            prepareStatement.setInt(1, id);
            resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                requestSet.add(creatingRequestFromResultSet(resultSet));
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            throw new DAOTourException(e);
        } finally {
            assert con != null;
            connectionPool.closeConnection(con, prepareStatement, resultSet);
        }
        logger.info(requestSet.size());
        return requestSet;
    }

    @Override
    public Set<Request> getAllRequestsByUserLogin(String login) throws DAOTourException {
        Connection con = null;
        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;
        Set<Request> requestSet = new HashSet<>();
        try {
            con = connectionPool.takeConnection();
            prepareStatement = con.prepareStatement(SELECT_ALL_REQUEST_FOR_USER_BY_USER_LOGIN);
            prepareStatement.setString(1, login);
            resultSet = prepareStatement.executeQuery();
            logger.info("be4 while + login" + login);
            while (resultSet.next()) {
                requestSet.add(creatingRequestFromResultSet(resultSet));
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            throw new DAOTourException(e);
        } finally {
            assert con != null;
            connectionPool.closeConnection(con, prepareStatement, resultSet);
        }
        logger.info(requestSet.size());
        return requestSet;
    }

    public Set<Tour> getAllToursByUserId(int id) throws DAOTourException {
        Connection con = null;
        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;
        Set<Tour> tourSet = new HashSet<>();
        try {
            con = connectionPool.takeConnection();
            prepareStatement = con.prepareStatement(SELECT_ALL_TOURS_BY_USER_ID);
            prepareStatement.setInt(1, id);
            resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                tourSet.add(creatingTourFromResultSet(resultSet));
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            throw new DAOTourException(e);
        } finally {
            assert con != null;
            connectionPool.closeConnection(con, prepareStatement, resultSet);
        }
        logger.info(tourSet.size());
        return tourSet;
    }

    @Override
    public Set<Tour> getAllTours() throws DAOTourException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        Set<Tour> tourSet = new HashSet<>();
        try {
            con = connectionPool.takeConnection();
            pstmt = con.prepareStatement(SELECT_ALL_TOURS_JOIN);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                tourSet.add(creatingTourFromResultSet(resultSet));
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            throw new DAOTourException(e);
        } finally {
            assert con != null;
            connectionPool.closeConnection(con, pstmt, resultSet);
        }
        logger.info(tourSet.size());
        return tourSet;
    }

    @Override
    public Set<Tour> getConcreteTypeTours(String typeOfTour) throws DAOTourException {
        Connection con = null;
        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;
        Set<Tour> tourSet = new HashSet<>();
        try {
            con = connectionPool.takeConnection();
            prepareStatement = con.prepareStatement(SELECT_CONCRETE_TOURS_JOIN);
            prepareStatement.setString(1, typeOfTour);
            resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                tourSet.add(creatingTourFromResultSet(resultSet));
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            throw new DAOTourException(e);
        } finally {
            assert con != null;
            connectionPool.closeConnection(con, prepareStatement, resultSet);
        }
        logger.info(tourSet.size());
        return tourSet;
    }

    @Override
    public Set<Hotel> getAllHotels() throws DAOTourException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        Set<Hotel> hotelSet = new HashSet<>();
        try {
            con = connectionPool.takeConnection();
            pstmt = con.prepareStatement(SELECT_ALL_HOTELS);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                hotelSet.add(creatingHotelFromResultSet(resultSet));
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            throw new DAOTourException(e);
        } finally {
            assert con != null;
            connectionPool.closeConnection(con, pstmt, resultSet);
        }
        logger.info(hotelSet.size());
        return hotelSet;
    }


    private Tour creatingTourFromResultSet(ResultSet resultSet) throws SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
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
        // logger.info(tour);
        return tour;
    }

    private Hotel creatingHotelFromResultSet(ResultSet resultSet) throws SQLException {
        Hotel hotel = new Hotel();
        hotel.setId(resultSet.getInt("id_Hotel"));
        hotel.setTitle(resultSet.getString("Title"));
        hotel.setCountry(resultSet.getString("country"));
        hotel.setCity(resultSet.getString("City"));
        hotel.setStars(resultSet.getByte("Stars"));
        hotel.setFreeRooms(resultSet.getInt("Free_rooms"));
        String temp = resultSet.getString("Type");
        Nutrition nutrition = Nutrition.valueOf(temp.trim().replace(' ', '_').toUpperCase());
        hotel.setNutrition(nutrition.nutrition());
        return hotel;
    }

    private Request creatingRequestFromResultSet(ResultSet resultSet) throws SQLException {
        Request request = new Request();
        Tour tour = new Tour();
        User user = new User();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
        request.setId(resultSet.getInt("id_Request"));
        request.setDateOfPayment(LocalDate.parse(resultSet.getString("Date_of_payment"), formatter));
        tour.setTitle(resultSet.getString("Title"));
        request.setTour(tour);
        request.setCount(resultSet.getBigDecimal("Count"));
        request.setPaymentPercentage(resultSet.getInt("Payment_percentage"));
        user.setId_user(resultSet.getInt("id_User"));
        user.setLogin(resultSet.getString("Login"));
        request.setUser(user);
        request.setDiscount(resultSet.getInt("Size_of_discount"));
        return request;
    }

}
