package by.epam.travel_agency.dao;

import by.epam.travel_agency.bean.Defrayal;
import by.epam.travel_agency.bean.Hotel;
import by.epam.travel_agency.bean.Tour;
import by.epam.travel_agency.bean.User;
import by.epam.travel_agency.dao.connection_pool.ConnectionPoolException;
import by.epam.travel_agency.dao.exception.DAOTourException;
import by.epam.travel_agency.dao.exception.GetIncorrectParameterException;
import by.epam.travel_agency.dao.factory.DAOFactory;
import by.epam.travel_agency.dao.factory.DAOFactoryProvider;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import resources.ConnectionPoolResourceForTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Set;


public class TourDAOImplTest {

    private static ConnectionPoolResourceForTest mockedConnectionPool;
    private final DAOFactory daoFactory = DAOFactoryProvider.getSqlDaoFactory();
    private TourDAO testTourDAO = daoFactory.getTourDao();

    public static Hotel HOTEL = new Hotel();
    public static Defrayal DEFRAYAL = new Defrayal();
    public static Tour TOUR = new Tour();

    @Before
    public void setUp() {
        mockedConnectionPool = ConnectionPoolResourceForTest.getInstance();
    }


    @AfterClass
    public static void disposeConnectionPool() {
        try {
            mockedConnectionPool.dispose();
        } catch (ConnectionPoolException e) {
            System.out.println(e);
        }
    }


    @Test
    public void addNewTourTest_ShouldReturnTrue() throws DAOTourException {
        TOUR.setId(9999);
        TOUR.setTitle("9999");
        TOUR.setPrice(BigDecimal.valueOf(9999));
        TOUR.setTypeOfTour("9999");
        TOUR.setHotTour(true);
        TOUR.setNumberOfPlaces(9999);
        TOUR.setDateStart(LocalDate.now());
        TOUR.setDateEnd(LocalDate.now());
        TOUR.setDiscount(0);
        TOUR.setHotel(HOTEL);
        TOUR.setDescription("9999");
        TOUR.setUrlWallpaper("9999");

        boolean actualTestResult = testTourDAO.addNewTour(TOUR);
        Assert.assertTrue(actualTestResult);
    }

    @Test
    public void findMaxValueOfIDTourTest_ShouldReturnInt() throws DAOTourException {
        int count = testTourDAO.findMaxValueOfIDTour();
        Assert.assertTrue(count > 0);
    }

    @Test
    public void findMaxValueOfIDDefrayalTest_ShouldReturnInt() throws DAOTourException {
        int count = testTourDAO.findMaxValueOfIDDefrayal();
        Assert.assertTrue(count > 0);
    }

    @Test
    public void getAllDefrayalsTest_ShouldReturnNotEmptyList() throws DAOTourException {
        List<Defrayal> defrayalList = testTourDAO.getAllDefrayals();
        Assert.assertTrue(defrayalList != null & !defrayalList.isEmpty());
    }

    @Test
    public void getAllDefrayalsByUserIdTest_ShouldReturnNumber() throws DAOTourException, GetIncorrectParameterException {
        int idUser = 2;
        int expected = 2;
        Set<Defrayal> defrayalSet = testTourDAO.getAllDefrayalsByUserId(idUser);
        Assert.assertEquals(defrayalSet.size(), expected);
    }

    @Test
    public void getAllDefrayalsByUserIdTest_ShouldReturnZero() throws DAOTourException, GetIncorrectParameterException {
        int idUser = 7;
        int expected = 0;
        Set<Defrayal> defrayalSet = testTourDAO.getAllDefrayalsByUserId(idUser);
        Assert.assertEquals(defrayalSet.size(), expected);
    }

    @Test(expected = GetIncorrectParameterException.class)
    public void getAllDefrayalsByUserIdTest_ShouldThrownAnException() throws DAOTourException, GetIncorrectParameterException {
        int idUser = -500;
        testTourDAO.getAllDefrayalsByUserId(idUser);
    }

    @Test
    public void getAllDefrayalsByUserLoginTest_ShouldReturnTrue() throws DAOTourException, GetIncorrectParameterException {
        String login = "AlexAlex";
        Set<Defrayal> defrayalSet = testTourDAO.getAllDefrayalsByUserLogin(login);
        Assert.assertFalse(defrayalSet.isEmpty());
    }

    @Test
    public void getAllDefrayalsByUserLoginTest_ShouldGetEmptySet() throws DAOTourException, GetIncorrectParameterException {
        String login = "RandomName9000";
        Set<Defrayal> defrayalSet = testTourDAO.getAllDefrayalsByUserLogin(login);
        Assert.assertTrue(defrayalSet.isEmpty());
    }

    @Test(expected = GetIncorrectParameterException.class)
    public void getAllDefrayalsByUserLoginTest_ShouldThrownAnException() throws DAOTourException, GetIncorrectParameterException {
        String login = null;
        testTourDAO.getAllDefrayalsByUserLogin(login);
    }


    @Test
    public void getAllToursTest_ShouldGetNotEmptySet() throws DAOTourException {
        Set<Tour> tourSet = testTourDAO.getAllTours();
        Assert.assertFalse(tourSet.isEmpty());
    }

    public void getAllTourTypesTest_ShouldReturnNotEmptyMap() throws DAOTourException {
        HashMap<Integer, String> hashMap = testTourDAO.getAllTourTypes();
        Assert.assertFalse(hashMap.isEmpty());
    }

    @Test
    public void getConcreteTypeToursTest_ShouldGetNotEmptySet() throws DAOTourException, GetIncorrectParameterException {
        String type = "vacation";
        Set<Tour> tourSet = testTourDAO.getConcreteTypeTours(type);
        Assert.assertFalse(tourSet.isEmpty());
    }

    @Test
    public void getConcreteTypeToursTest_ShouldGetEmptySet() throws DAOTourException, GetIncorrectParameterException {
        String type = "DestroyDeathStar";
        Set<Tour> tourSet = testTourDAO.getConcreteTypeTours(type);
        Assert.assertTrue(tourSet.isEmpty());
    }

    @Test(expected = GetIncorrectParameterException.class)
    public void getConcreteTypeToursTestTest_ShouldThrownAnException() throws DAOTourException, GetIncorrectParameterException {
        String type = null;
        testTourDAO.getConcreteTypeTours(type);
    }


    @Test
    public void getTourByIdTest_ShouldGetTour() throws DAOTourException, GetIncorrectParameterException {
        int idTour = 2222;
        Tour tour = testTourDAO.getTourById(idTour);
        Assert.assertNotNull(tour);
    }

    @Test(expected = GetIncorrectParameterException.class)
    public void getTourByIdTest_ShouldThrownAnException() throws DAOTourException, GetIncorrectParameterException {
        int idTour = -1999;
        testTourDAO.getTourById(idTour);
    }

    @Test
    public void getAllHotelsTest_ShouldGetNotEmptySet() throws DAOTourException {
        Set<Hotel> hotelSet = testTourDAO.getAllHotels();
        Assert.assertFalse(hotelSet.isEmpty());
    }

    @Test
    public void getDiscountsListTest_ShouldGetEmptySet() throws DAOTourException {
        HashMap<Integer, Integer> discount = testTourDAO.getDiscountsList();
        Assert.assertFalse(discount.isEmpty());
    }

    @Test
    public void addNewDefrayalTest_ShouldReturnTrue() throws DAOTourException {
        DEFRAYAL.setId(9999);
        DEFRAYAL.setDateOfPayment(LocalDate.now());
        DEFRAYAL.setTour(TOUR);
        DEFRAYAL.setCount(BigDecimal.valueOf(9999));
        DEFRAYAL.setPaymentPercentage(100);

        User user = new User();
        user.setIdUser(9999);
        DEFRAYAL.setUser(user);
        DEFRAYAL.setDiscount(1);
        DEFRAYAL.setFinalCount(DEFRAYAL.getCount());
        DEFRAYAL.setAnnotation("DELETE AFTER TEST");

        boolean actualTestResult = testTourDAO.addNewDefrayal(DEFRAYAL);
        Assert.assertTrue(actualTestResult);
    }

    @Test
    public void getDefrayalByIdTest_ShouldReturnDefrayal() throws DAOTourException, GetIncorrectParameterException {
        Defrayal defrayal = testTourDAO.getDefrayalById(123123);

        Assert.assertNotNull(defrayal);
    }

    @Test(expected = GetIncorrectParameterException.class)
    public void getDefrayalByIdTest_ShouldThrownAnException() throws DAOTourException, GetIncorrectParameterException {

        testTourDAO.getDefrayalById(-1999);
    }

}
