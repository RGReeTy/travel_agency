package by.epam.travel_agency.dao;

import by.epam.travel_agency.bean.*;
import by.epam.travel_agency.dao.connection_pool.ConnectionPool;
import by.epam.travel_agency.dao.connection_pool.ConnectionPoolException;
import by.epam.travel_agency.dao.exception.DAOTourException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TourDAOImpl implements TourDAO {

    private static final Logger logger = Logger.getLogger(TourDAOImpl.class);

    private static final String SELECT_ALL_TOURS = "SELECT * FROM bustravelagency.tours";
    private static final String SELECT_ALL_TOURS_JOIN = "SELECT id_Tour, tours.Title, TypeOfTour, Price, " +
            "Size_of_discount, Hot_tour, Number_of_places, Date_start, Date_end, hotel.Title AS 'Hotel'," +
            "Description, tours.Url_wallpaper" +
            "        FROM bustravelagency.tours" +
            "        JOIN typeoftour ON Type=id_TypeOfTour" +
            "        JOIN discount ON tours.id_Discount = discount.id_Discount" +
            "        JOIN hotel ON tours.id_Hotel = hotel.id_Hotel";
    private static final String SELECT_CONCRETE_TOURS_JOIN = "SELECT id_Tour, tours.Title, TypeOfTour, Price, " +
            "Size_of_discount, Hot_tour, Number_of_places, Date_start, Date_end, hotel.Title AS 'Hotel'" +
            ",Description, tours.Url_wallpaper" +
            "        FROM bustravelagency.tours" +
            "        JOIN typeoftour ON Type=id_TypeOfTour" +
            "        JOIN discount ON tours.id_Discount = discount.id_Discount" +
            "        JOIN hotel ON tours.id_Hotel = hotel.id_Hotel WHERE TypeOfTour = ?";
    private static final String SELECT_TOUR_BY_ID = "SELECT id_Tour, tours.Title, TypeOfTour, Price, " +
            "Size_of_discount, Hot_tour, Number_of_places, Date_start, Date_end, hotel.Title AS 'Hotel'" +
            ",Description, tours.Url_wallpaper" +
            "        FROM bustravelagency.tours" +
            "        JOIN typeoftour ON Type=id_TypeOfTour" +
            "        JOIN discount ON tours.id_Discount = discount.id_Discount" +
            "        JOIN hotel ON tours.id_Hotel = hotel.id_Hotel WHERE id_Tour = ?";
    private static final String SELECT_ALL_HOTELS = "SELECT id_Hotel, Title, country, City, Stars, Free_rooms," +
            "Type, Min_price_per_room, Url_wallpaper FROM hotel JOIN nutrition ON Nutrition=id_Nutrition";
    private static final String SELECT_ALL_TOURS_BY_USER_ID = "SELECT tours.id_Tour, tours.Title, TypeOfTour, " +
            "Price, Size_of_discount, Hot_tour, Number_of_places, Date_start, Date_end, hotel.Title AS 'Hotel'," +
            " defrayal.Date_of_payment\n" +
            "FROM bustravelagency.tours JOIN typeoftour ON Type=id_TypeOfTour\n" +
            "JOIN discount ON tours.id_Discount = discount.id_Discount\n" +
            "JOIN defrayal ON defrayal.Id_Tour = tours.id_Tour\n" +
            "JOIN hotel ON tours.id_Hotel = hotel.id_Hotel WHERE Id_User = ?";
    private static final String SELECT_ALL_DEFRAYAL_FOR_USER_BY_USER_ID = "SELECT id_Defrayal, Date_of_payment, Title, Count, Payment_percentage, defrayal.Id_User,\n" +
            "      Login, Size_of_discount FROM bustravelagency.defrayal JOIN tours ON  defrayal.Id_Tour=tours.id_Tour\n" +
            "    JOIN discount ON defrayal.id_Discount=discount.id_Discount " +
            "JOIN users ON defrayal.Id_User=users.id_User WHERE defrayal.Id_User =  ?";
    private static final String SELECT_ALL_DEFRAYAL = "SELECT id_Defrayal, Date_of_payment, Title, Count, Payment_percentage, defrayal.Id_User, Login,\n" +
            "Size_of_discount FROM bustravelagency.defrayal JOIN tours ON defrayal.Id_Tour=tours.id_Tour\n" +
            "    JOIN users ON defrayal.Id_User=users.id_User\n" +
            "JOIN discount ON defrayal.id_Discount=discount.id_Discount ORDER BY Date_of_payment";
    private static final String SELECT_ALL_DEFRAYAL_WHERE_IS_DEBT = "SELECT id_Defrayal, Date_of_payment, Title, Count, Payment_percentage, defrayal.Id_User, Login,\n" +
            "Size_of_discount FROM bustravelagency.defrayal JOIN tours ON defrayal.Id_Tour=tours.id_Tour\n" +
            "JOIN users ON defrayal.Id_User=users.id_User JOIN discount ON defrayal.id_Discount=discount.id_Discount\n" +
            "    WHERE Payment_percentage!=100;";
    private static final String SELECT_ALL_DEFRAYAL_FOR_USER_BY_USER_LOGIN = "SELECT id_Defrayal, Date_of_payment, Title, Count, Payment_percentage, Login,\n" +
            "       Size_of_discount, users.id_User FROM bustravelagency.defrayal JOIN tours ON  defrayal.Id_Tour=tours.id_Tour\n" +
            "    JOIN discount ON defrayal.id_Discount=discount.id_Discount\n" +
            "    JOIN users ON defrayal.Id_User=users.id_User WHERE Login = ?";
    private final static String INSERT_NEW_TOUR = "INSERT INTO bustravelagency.tours(id_Tour, Title, Price, Type," +
            "Hot_tour, Number_of_places, Date_start, Date_end, id_Discount, id_Hotel, Description, Url_wallpaper) " +
            "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
    private final static String INSERT_NEW_DEFRAYAL = "INSERT INTO  defrayal(id_Defrayal, Date_of_payment, Id_Tour, " +
            "Count, Payment_percentage, Id_User, id_Discount) VALUES (?,?,?,?,?,?,?)";
    private final static String INSERT_NEW_DEFRAYAL_MINIMAL_INFO = "INSERT INTO defrayal(id_Defrayal, Id_Tour, " +
            " Annotation) VALUES (?,?,?)";

    private final static String FIND_MAX_VALUE_TOUR_ID = "SELECT MAX(id_Tour) FROM tours";
    private final static String GET_ALL_TYPES_OF_TOURS = "SELECT * FROM typeoftour";
    private final static String SELECT_ALL_DISCOUNTS = "SELECT * FROM discount";


    private final String ANNOTATION = "Annotation";
    private final String CITY = "City";
    private final String COUNT = "Count";
    private final String COUNTRY = "country";
    private final String DATE_END = "Date_end";
    private final String DATE_OF_PAYMENT = "Date_of_payment";
    private final String DATE_PATTERN = "d-MM-yyyy";
    private final String DATE_START = "Date_start";
    private final String DESCRIPTION = "Description";
    private final String FREE_ROOMS = "Free_rooms";
    private final String HOT_TOUR = "Hot_tour";
    private final String HOTEL = "Hotel";
    private final String ID_DEFRAYAL = "id_Defrayal";
    private final String ID_DISCOUNT = "id_Discount";
    private final String ID_HOTEL = "id_Hotel";
    private final String ID_TOUR = "id_Tour";
    private final String ID_TYPE_OF_TOUR = "id_TypeOfTour";
    private final String ID_USER = "id_User";
    private final String LOGIN = "Login";
    private final String MIN_PRICE_PER_ROOM = "Min_price_per_room";
    private final String NUMBER_OF_PLACES = "Number_of_places";
    private final String PAYMENT_PERCENTAGE = "Payment_percentage";
    private final String PRICE = "Price";
    private final String SIZE_OF_DISCOUNT = "Size_of_discount";
    private final String STARS = "Stars";
    private final String TITLE = "Title";
    private final String TYPE = "Type";
    private final String TYPE_OF_TOUR = "TypeOfTour";
    private final String URL_WALLPAPER = "Url_wallpaper";


    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public boolean addNewTour(Tour tour) throws DAOTourException {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        try {
            conn = connectionPool.takeConnection();
            pstmt = conn.prepareStatement(INSERT_NEW_TOUR);
            pstmt.setInt(1, tour.getId());
            pstmt.setString(2, tour.getTitle());
            pstmt.setBigDecimal(3, tour.getPrice());
            pstmt.setInt(4, Integer.parseInt(tour.getTypeOfTour()));
            pstmt.setBoolean(5, tour.isHotTour());
            pstmt.setInt(6, tour.getNumberOfPlaces());
            LocalDate localDateStart = tour.getDateStart();
            LocalDate localDateEnd = tour.getDateEnd();
            String dateStart = localDateStart.format(formatter);
            String dateEnd = localDateEnd.format(formatter);
            pstmt.setString(7, dateStart);
            pstmt.setString(8, dateEnd);
            pstmt.setInt(9, tour.getDiscount());
            pstmt.setInt(10, tour.getHotel().getId());
            pstmt.setString(11, tour.getDescription());
            pstmt.setString(12, tour.getUrlWallpaper());

            int count = pstmt.executeUpdate();
            if (count == 1) {
                flag = true;
                logger.info("Tour was successfully added");
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Can't insert tour." + e);
            throw new DAOTourException(e);
        } finally {
            if (conn != null) {
                connectionPool.closeConnection(conn, pstmt);
            }
        }
        return flag;
    }

    @Override
    public int findMaxValueOfIDTour() throws DAOTourException {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int value = 0;
        try {
            con = connectionPool.takeConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(FIND_MAX_VALUE_TOUR_ID);
            while (rs.next()) {
                value = (rs.getInt(1));
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error at finding max id_tour." + e);
            throw new DAOTourException(e);
        } finally {
            if (con != null) {
                connectionPool.closeConnection(con, stmt, rs);
            }
        }
        logger.info("After find max value: value=" + value);
        return value;
    }

    @Override
    public List<Defrayal> getAllDefrayals() throws DAOTourException {
        Connection con = null;
        Statement stmt = null;
        ResultSet resultSet = null;
        List<Defrayal> defrayalList = new LinkedList<>();
        try {
            con = connectionPool.takeConnection();
            stmt = con.createStatement();
            resultSet = stmt.executeQuery(SELECT_ALL_DEFRAYAL);
            while (resultSet.next()) {
                defrayalList.add(creatingDefrayalFromResultSet(resultSet));
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            throw new DAOTourException(e);
        } finally {
            if (con != null) {
                connectionPool.closeConnection(con, stmt, resultSet);
            }
        }
        logger.info(defrayalList.size());
        return defrayalList;
    }

    @Override
    public List<Defrayal> getAllDefrayalsWhereDebt() throws DAOTourException {
        Connection con = null;
        Statement stmt = null;
        ResultSet resultSet = null;
        List<Defrayal> defrayalList = new LinkedList<>();
        try {
            con = connectionPool.takeConnection();
            stmt = con.createStatement();
            resultSet = stmt.executeQuery(SELECT_ALL_DEFRAYAL_WHERE_IS_DEBT);
            while (resultSet.next()) {
                defrayalList.add(creatingDefrayalFromResultSet(resultSet));
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            throw new DAOTourException(e);
        } finally {
            if (con != null) {
                connectionPool.closeConnection(con, stmt, resultSet);
            }
        }
        logger.info(defrayalList.size());
        return defrayalList;
    }

    @Override
    public Set<Defrayal> getAllDefrayalsByUserId(int id) throws DAOTourException {
        Connection con = null;
        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;
        Set<Defrayal> defrayalSet = new HashSet<>();
        try {
            con = connectionPool.takeConnection();
            prepareStatement = con.prepareStatement(SELECT_ALL_DEFRAYAL_FOR_USER_BY_USER_ID);
            prepareStatement.setInt(1, id);
            resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                defrayalSet.add(creatingDefrayalFromResultSet(resultSet));
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            throw new DAOTourException(e);
        } finally {
            if (con != null) {
                connectionPool.closeConnection(con, prepareStatement, resultSet);
            }
        }
        logger.info(defrayalSet.size());
        return defrayalSet;
    }

    @Override
    public Set<Defrayal> getAllDefrayalsByUserLogin(String login) throws DAOTourException {
        Connection con = null;
        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;
        Set<Defrayal> defrayalSet = new HashSet<>();
        try {
            con = connectionPool.takeConnection();
            prepareStatement = con.prepareStatement(SELECT_ALL_DEFRAYAL_FOR_USER_BY_USER_LOGIN);
            prepareStatement.setString(1, login);
            resultSet = prepareStatement.executeQuery();
            logger.info("be4 while + login" + login);
            while (resultSet.next()) {
                defrayalSet.add(creatingDefrayalFromResultSet(resultSet));
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            throw new DAOTourException(e);
        } finally {
            if (con != null) {
                connectionPool.closeConnection(con, prepareStatement, resultSet);
            }
        }
        logger.info(defrayalSet.size());
        return defrayalSet;
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
            if (con != null) {
                connectionPool.closeConnection(con, prepareStatement, resultSet);
            }
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
            if (con != null) {
                connectionPool.closeConnection(con, pstmt, resultSet);
            }
        }
        logger.info(tourSet.size());
        return tourSet;
    }

    @Override
    public HashMap<Integer, String> getAllTourTypes() throws DAOTourException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        HashMap<Integer, String> hashMap = new HashMap<>();
        try {
            con = connectionPool.takeConnection();
            pstmt = con.prepareStatement(GET_ALL_TYPES_OF_TOURS);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                hashMap.put(resultSet.getInt(ID_TYPE_OF_TOUR), resultSet.getString(TYPE_OF_TOUR));
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            throw new DAOTourException(e);
        } finally {
            if (con != null) {
                connectionPool.closeConnection(con, pstmt, resultSet);
            }
        }
        logger.info(hashMap.size());
        return hashMap;
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
            if (con != null) {
                connectionPool.closeConnection(con, prepareStatement, resultSet);
            }
        }
        logger.info(tourSet.size());
        return tourSet;
    }

    @Override
    public Tour getTourById(int id_tour) throws DAOTourException {
        Connection con = null;
        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;
        Tour tour = new Tour();
        try {
            con = connectionPool.takeConnection();
            prepareStatement = con.prepareStatement(SELECT_TOUR_BY_ID);
            prepareStatement.setInt(1, id_tour);
            resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                tour = creatingTourFromResultSet(resultSet);
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            throw new DAOTourException(e);
        } finally {
            if (con != null) {
                connectionPool.closeConnection(con, prepareStatement, resultSet);
            }
        }
        return tour;
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
            if (con != null) {
                connectionPool.closeConnection(con, pstmt, resultSet);
            }
        }
        logger.info("getAllHotels size:" + hotelSet.size());
        return hotelSet;
    }

    @Override
    public HashMap<Integer, Integer> getDiscountsList() throws DAOTourException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        HashMap<Integer, Integer> discount = new HashMap<>();
        try {
            con = connectionPool.takeConnection();
            pstmt = con.prepareStatement(SELECT_ALL_DISCOUNTS);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                discount.put(resultSet.getInt(ID_DISCOUNT), resultSet.getInt(SIZE_OF_DISCOUNT));
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            throw new DAOTourException(e);
        } finally {
            if (con != null) {
                connectionPool.closeConnection(con, pstmt, resultSet);
            }
        }
        logger.info("getDiscountsList size:" + discount.size());
        return discount;
    }

    @Override
    public boolean addNewDefrayal(Defrayal defrayal) throws DAOTourException {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        try {
            conn = connectionPool.takeConnection();
            pstmt = conn.prepareStatement(INSERT_NEW_DEFRAYAL);
            pstmt.setInt(1, defrayal.getId());
            LocalDate localDatePayment = defrayal.getDateOfPayment();
            String dateOfPayment = localDatePayment.format(formatter);
            pstmt.setString(2, dateOfPayment);
            pstmt.setInt(3, defrayal.getTour().getId());
            pstmt.setBigDecimal(4, defrayal.getCount());
            pstmt.setInt(5, defrayal.getPaymentPercentage());
            pstmt.setInt(6, defrayal.getUser().getId_user());
            pstmt.setInt(6, defrayal.getDiscount());

            int count = pstmt.executeUpdate();
            if (count == 1) {
                flag = true;
                logger.info("Defrayal was successfully added");
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Can't insert defrayal." + e);
            throw new DAOTourException(e);
        } finally {
            if (conn != null) {
                connectionPool.closeConnection(conn, pstmt);
            }
        }
        return flag;
    }

    @Override
    public boolean addNewDefrayalMinimalInfo(Defrayal defrayal) throws DAOTourException {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = connectionPool.takeConnection();
            pstmt = conn.prepareStatement(INSERT_NEW_DEFRAYAL_MINIMAL_INFO);
            pstmt.setInt(1, defrayal.getId());
            pstmt.setInt(2, defrayal.getTour().getId());
            pstmt.setString(3, defrayal.getAnnotation());

            int count = pstmt.executeUpdate();
            if (count == 1) {
                flag = true;
                logger.info("Defrayal was successfully added");
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Can't insert defrayal." + e);
            throw new DAOTourException(e);
        } finally {
            if (conn != null) {
                connectionPool.closeConnection(conn, pstmt);
            }
        }
        return flag;
    }


    private Tour creatingTourFromResultSet(ResultSet resultSet) throws SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        Tour tour = new Tour();
        Hotel hotel = new Hotel();
        tour.setId(resultSet.getInt(ID_TOUR));
        tour.setTitle(resultSet.getString(TITLE));
        tour.setTypeOfTour(resultSet.getString(TYPE_OF_TOUR));
        tour.setPrice(resultSet.getBigDecimal(PRICE));
        tour.setDiscount(resultSet.getInt(SIZE_OF_DISCOUNT));
        tour.setHotTour(resultSet.getBoolean(HOT_TOUR));
        tour.setNumberOfPlaces(resultSet.getInt(NUMBER_OF_PLACES));
        tour.setDateStart(LocalDate.parse(resultSet.getString(DATE_START), formatter));
        tour.setDateEnd(LocalDate.parse(resultSet.getString(DATE_END), formatter));
        hotel.setTitle(resultSet.getString(HOTEL));
        tour.setHotel(hotel);
        tour.setDescription(resultSet.getString(DESCRIPTION));
        tour.setUrlWallpaper(resultSet.getString(URL_WALLPAPER));
        return tour;
    }

    private Hotel creatingHotelFromResultSet(ResultSet resultSet) throws SQLException {
        Hotel hotel = new Hotel();
        hotel.setId(resultSet.getInt(ID_HOTEL));
        hotel.setTitle(resultSet.getString(TITLE));
        hotel.setCountry(resultSet.getString(COUNTRY));
        hotel.setCity(resultSet.getString(CITY));
        hotel.setStars(resultSet.getByte(STARS));
        hotel.setFreeRooms(resultSet.getInt(FREE_ROOMS));
        hotel.setMinPricePerRoom(resultSet.getBigDecimal(MIN_PRICE_PER_ROOM));
        String temp = resultSet.getString(TYPE);
        Nutrition nutrition = Nutrition.valueOf(temp.trim().replace(' ', '_').toUpperCase());
        hotel.setNutrition(nutrition.nutrition());
        hotel.setUrlWallpaper(resultSet.getString(URL_WALLPAPER));
        return hotel;
    }

    private Defrayal creatingDefrayalFromResultSet(ResultSet resultSet) throws SQLException {
        Defrayal defrayal = new Defrayal();
        Tour tour = new Tour();
        User user = new User();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        defrayal.setId(resultSet.getInt(ID_DEFRAYAL));
        defrayal.setDateOfPayment(LocalDate.parse(resultSet.getString(DATE_OF_PAYMENT), formatter));
        tour.setTitle(resultSet.getString(TITLE));
        defrayal.setTour(tour);
        defrayal.setCount(resultSet.getBigDecimal(COUNT));
        defrayal.setPaymentPercentage(resultSet.getInt(PAYMENT_PERCENTAGE));
        user.setId_user(resultSet.getInt(ID_USER));
        user.setLogin(resultSet.getString(LOGIN));
        defrayal.setUser(user);
        defrayal.setDiscount(resultSet.getInt(SIZE_OF_DISCOUNT));
        defrayal.setAnnotation(resultSet.getString(ANNOTATION));
        return defrayal;
    }

}
