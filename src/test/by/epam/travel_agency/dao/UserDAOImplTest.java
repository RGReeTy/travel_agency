package by.epam.travel_agency.dao;

import by.epam.travel_agency.bean.User;
import by.epam.travel_agency.dao.connection_pool.ConnectionPoolException;
import by.epam.travel_agency.dao.exception.DAOUserException;
import by.epam.travel_agency.dao.exception.GetIncorrectParameterException;
import by.epam.travel_agency.dao.factory.DAOFactory;
import by.epam.travel_agency.dao.factory.DAOFactoryProvider;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import resources.ConnectionPoolResourceForTest;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

public class UserDAOImplTest {


    private static ConnectionPoolResourceForTest mockedConnectionPool;
    private final DAOFactory daoFactory = DAOFactoryProvider.getSqlDaoFactory();
    private UserDAO testUserDAO = daoFactory.getUserDao();

    public static User USER = new User();

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

    //@Ignore
    @Test
    public void addNewUserToDBTest_ShouldReturnTrue() throws DAOUserException {
        USER.setIdUser(12345);
        USER.setLogin("TESTUSER123456");
        USER.setPassword("TESTUSER123456");
        USER.setEmail("testuser123456@gmail.com");
        USER.setFirstname("TESTUSER123456");
        USER.setLastname("TESTUSER123456");
        USER.setPhone("+375291234567");
        USER.setIdDiscount(2);
        USER.setLevelAccess(1);

        boolean actualTestResult = testUserDAO.addNewUserToDB(USER);
        Assert.assertTrue(actualTestResult);
    }

    @Test
    public void findUserByLoginTest_ShouldReturnNotNull() throws GetIncorrectParameterException, DAOUserException {
        String login = "AlexAlex";
        User user = testUserDAO.findUserByLogin(login);
        Assert.assertNotNull(user);
    }

    @Test(expected = GetIncorrectParameterException.class)
    public void findUserByLoginTest_ShouldThrownAnException() throws GetIncorrectParameterException, DAOUserException {
        String login = "";
        User user = testUserDAO.findUserByLogin(login);
        Assert.assertNotNull(user);
    }

    @Test
    public void findEntityByLoginTest_ShouldReturnTrue() throws GetIncorrectParameterException, DAOUserException {
        String login = "AlexAlex";
        boolean actual = testUserDAO.findEntityByLogin(login);
        Assert.assertTrue(actual);
    }

    @Test
    public void findEntityByLoginTest_ShouldReturnFalse() throws GetIncorrectParameterException, DAOUserException {
        String login = "Afdosjnbdfnbklsdf";
        boolean actual = testUserDAO.findEntityByLogin(login);
        Assert.assertFalse(actual);
    }

    @Test(expected = GetIncorrectParameterException.class)
    public void findEntityByLoginTest_ShouldThrownAnException() throws GetIncorrectParameterException, DAOUserException {
        String login = "";
        testUserDAO.findEntityByLogin(login);
    }

    @Test
    public void countAllRowsTest_ShouldReturnIntMoreThanZero() throws DAOUserException {
        int count = testUserDAO.countAllRows();
        Assert.assertTrue(count > 0);
    }

    @Test
    public void countAllUsersByLevelAccessTest_ShouldReturnNotEmptyHashMap() throws DAOUserException {
        HashMap<Integer, Integer> usersByLevelAccess = testUserDAO.countAllUsersByLevelAccess();
        Assert.assertNotNull(usersByLevelAccess);
        Assert.assertFalse(usersByLevelAccess.isEmpty());
    }

    @Test
    public void findEntityByIdTest_ShouldReturnUser() throws GetIncorrectParameterException, DAOUserException {
        User user = testUserDAO.findEntityById(12345);
        Assert.assertNotNull(user);
    }

    @Test(expected = GetIncorrectParameterException.class)
    public void findEntityByIdTest_ShouldThrownAnException() throws GetIncorrectParameterException, DAOUserException {
        testUserDAO.findEntityById(-256);
    }

    @Test
    public void getAllUsersForChangingLevelAccessTest_ShouldReturnUserList() throws DAOUserException {
        List<User> userList = testUserDAO.getAllUsersForChangingLevelAccess();
        Assert.assertNotNull(userList);
        Assert.assertFalse(userList.isEmpty());
    }

    @Test(expected = GetIncorrectParameterException.class)
    public void updateUserStatusTest_ShouldThrownAnException() throws GetIncorrectParameterException, DAOUserException {
        int idUser = -5;
        int status = -5;
        testUserDAO.updateUserStatus(idUser, status);
    }

    @Test
    public void updateUserInfoTest_ShouldReturnTrue() throws DAOUserException {
        boolean success = testUserDAO.updateUserInfo(USER);
        Assert.assertTrue(success);
    }

    @Test
    public void countTotalMoneySpentTest_ShouldReturnBigDecimal() throws GetIncorrectParameterException, DAOUserException {
        BigDecimal bigDecimal = testUserDAO.countTotalMoneySpent(USER.getIdUser());
        Assert.assertNotNull(bigDecimal);
    }

    @Test(expected = GetIncorrectParameterException.class)
    public void getDiscountByIDTest_ShouldReturnThrownAnException() throws GetIncorrectParameterException, DAOUserException {
        testUserDAO.getDiscountByID(-5);
    }
}