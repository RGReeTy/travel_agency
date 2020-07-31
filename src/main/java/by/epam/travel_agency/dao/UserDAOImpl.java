package by.epam.travel_agency.dao;

import by.epam.travel_agency.bean.User;
import by.epam.travel_agency.dao.connection_pool.ConnectionPool;
import by.epam.travel_agency.dao.connection_pool.ConnectionPoolException;
import by.epam.travel_agency.dao.connection_pool.ConnectionPoolImpl;
import by.epam.travel_agency.dao.exception.DAOUserException;
import by.epam.travel_agency.dao.exception.GetIncorrectParameterException;
import by.epam.travel_agency.dao.paramName.UserDAOParam;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static by.epam.travel_agency.service.util.FinalPriceMaker.countNumeralValueOfDiscount;

/**
 * The type User dao.
 */
public class UserDAOImpl implements UserDAO {

    private static final Logger logger = Logger.getLogger(UserDAOImpl.class);

    private final String COUNT = "Count";
    private final String DISCOUNT = "Discount";
    private final String EMAIL = "Email";
    private final String FIRSTNAME = "Firstname";
    private final String ID_DISCOUNT = "id_Discount";
    private final String ID_USER = "id_User";
    private final String LASTNAME = "Lastname";
    private final String LEVEL_ACCESS = "Level_access";
    private final String LOGIN = "Login";
    private final String PASSWORD = "Password";
    private final String PHONE = "Phone";
    private final String SIZE_OF_DISCOUNT = "Size_of_discount";

    private ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

    @Override
    public boolean addNewUserToDB(User user) throws DAOUserException {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = connectionPool.takeConnection();
            pstmt = conn.prepareStatement(UserDAOParam.INSERT_FULL_INFO);
            pstmt.setInt(1, user.getIdUser());
            pstmt.setString(2, user.getLogin());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getEmail());
            pstmt.setString(5, user.getFirstname());
            pstmt.setString(6, user.getLastname());
            pstmt.setString(7, user.getPhone());
            pstmt.setInt(8, user.getIdDiscount());
            pstmt.setInt(9, user.getLevelAccess());

            int count = pstmt.executeUpdate();
            if (count == 1) {
                flag = true;
                logger.info("User was succesfully cr8");
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Can't insert user." + e);
            throw new DAOUserException(e);
        } finally {
            if (conn != null) {
                connectionPool.closeConnection(conn, pstmt);
            }
        }
        return flag;
    }

    @Override
    public User findUserByLogin(String login) throws DAOUserException, GetIncorrectParameterException {

        if (login == null || login.isEmpty()) {
            throw new GetIncorrectParameterException();
        }

        User user = null;
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.takeConnection();
            prepareStatement = connection.prepareStatement(UserDAOParam.SELECT_BY_LOGIN);
            prepareStatement.setString(1, login);
            resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setIdUser(resultSet.getInt(ID_USER));
                user.setLogin(resultSet.getString(LOGIN));
                user.setPassword(resultSet.getString(PASSWORD));
                user.setEmail(resultSet.getString(EMAIL));
                user.setPhone(resultSet.getString(PHONE));
                user.setFirstname(resultSet.getString(FIRSTNAME));
                user.setLastname(resultSet.getString(LASTNAME));
                user.setLevelAccess(resultSet.getInt(LEVEL_ACCESS));
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Can't select user by login and password." + e);
            throw new DAOUserException(e);
        } finally {
            if (connection != null) {
                connectionPool.closeConnection(connection, prepareStatement, resultSet);
            }
        }
        // logger.info(user != null ? user.toString() : "user is null!");
        return user;
    }

    @Override
    public boolean findEntityByLogin(String login) throws DAOUserException, GetIncorrectParameterException {

        if (login == null || login.isEmpty()) {
            throw new GetIncorrectParameterException();
        }

        boolean isExist = false;
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.takeConnection();
            prepareStatement = connection.prepareStatement(UserDAOParam.SELECT_USERS_BY_LOGIN);
            prepareStatement.setString(1, login);
            resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                isExist = true;
            } else {
                logger.error("resultSet is null");
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Can't select user by login. " + e);
            throw new DAOUserException(e);
        } finally {
            if (connection != null) {
                connectionPool.closeConnection(connection, prepareStatement, resultSet);
            }
        }
        logger.info(isExist);
        return isExist;
    }

    @Override
    public int countAllRows() throws DAOUserException {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int count = 0;
        try {
            con = connectionPool.takeConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(UserDAOParam.COUNT_ALL_USERS);

            while (rs.next()) {
                count = (rs.getInt(1));
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Can't insert user." + e);
            throw new DAOUserException(e);
        } finally {
            if (con != null) {
                connectionPool.closeConnection(con, stmt, rs);
            }
        }
        logger.info("After count all users: count=" + count);
        return count;
    }

    @Override
    public HashMap<Integer, Integer> countAllUsersByLevelAccess() throws DAOUserException {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        HashMap<Integer, Integer> usersByLevelAccess = new HashMap<>();
        int levelAccess;
        int count;
        try {
            con = connectionPool.takeConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(UserDAOParam.COUNT_USERS_BY_LEVEL_ACCESS);
            while (rs.next()) {
                levelAccess = rs.getInt(LEVEL_ACCESS);
                count = rs.getInt(COUNT);
                usersByLevelAccess.put(levelAccess, count);
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Can't insert user." + e);
            throw new DAOUserException(e);
        } finally {
            if (con != null) {
                connectionPool.closeConnection(con, stmt, rs);
            }
        }
        logger.info("before returning HashMap: size = " + usersByLevelAccess.size());
        return usersByLevelAccess;
    }

    @Override
    public User findEntityById(int idUser) throws DAOUserException, GetIncorrectParameterException {

        if (idUser < 0) {
            throw new GetIncorrectParameterException();
        }

        User user = null;
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionPool.takeConnection();
            prepareStatement = connection.prepareStatement(UserDAOParam.SELECT_USERS_BY_ID_USER);
            prepareStatement.setInt(1, idUser);
            resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setIdUser(idUser);
                user.setLogin(resultSet.getString(LOGIN));
                user.setEmail(resultSet.getString(EMAIL));
                user.setFirstname(resultSet.getString(FIRSTNAME));
                user.setLastname(resultSet.getString(LASTNAME));
                user.setPhone(resultSet.getString(PHONE));
                user.setIdDiscount(resultSet.getInt(ID_DISCOUNT));
                user.setLevelAccess(resultSet.getInt(LEVEL_ACCESS));

                logger.info("User was creating: " + user.toString());
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Can't select user by id." + e);
            throw new DAOUserException(e);
        } finally {
            if (connection != null) {
                connectionPool.closeConnection(connection, prepareStatement, resultSet);
            }
        }
        return user;
    }

    @Override
    public List<User> getAllUsersForChangingLevelAccess() throws DAOUserException {
        Connection con = null;
        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;
        List<User> userList = new ArrayList<>();
        try {
            con = connectionPool.takeConnection();
            prepareStatement = con.prepareStatement(UserDAOParam.SELECT_ID_LOGIN_LA);
            resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setIdUser(resultSet.getInt(ID_USER));
                user.setLogin(resultSet.getString(LOGIN));
                user.setLevelAccess(resultSet.getInt(LEVEL_ACCESS));
                userList.add(user);
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            throw new DAOUserException(e);
        } finally {
            if (con != null) {
                connectionPool.closeConnection(con, prepareStatement, resultSet);
            }
        }
        logger.info(userList.size());
        return userList;
    }

    @Override
    public boolean updateUserStatus(int idUser, int status) throws DAOUserException, GetIncorrectParameterException {

        if (idUser < 0 || status < 0) {
            throw new GetIncorrectParameterException();
        }

        boolean operationSuccess = false;
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        try {
            connection = connectionPool.takeConnection();
            prepareStatement = connection.prepareStatement(UserDAOParam.UPDATE_USER_STATUS);
            prepareStatement.setInt(1, status);
            prepareStatement.setInt(2, idUser);
            if (prepareStatement.executeUpdate() == 1) {
                operationSuccess = true;
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Operation UPDATE is broke: " + e);
            throw new DAOUserException(e);
        } finally {
            if (connection != null) {
                connectionPool.closeConnection(connection, prepareStatement);
            }
        }
        logger.info(operationSuccess);
        return operationSuccess;
    }

    public boolean updateUserInfo(User user) throws DAOUserException {
        logger.info("updateUserInfo: " + user.toString());

        boolean operationSuccess = false;
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        try {
            connection = connectionPool.takeConnection();
            prepareStatement = connection.prepareStatement(UserDAOParam.UPDATE_USER_INFO);
            prepareStatement.setString(1, user.getFirstname());
            prepareStatement.setString(2, user.getLastname());
            prepareStatement.setString(3, user.getEmail());
            prepareStatement.setString(4, user.getPhone());
            prepareStatement.setString(5, user.getLogin());
            if (prepareStatement.executeUpdate() == 1) {
                operationSuccess = true;
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Operation UPDATE is broke: " + e);
            throw new DAOUserException(e);
        } finally {
            if (connection != null) {
                connectionPool.closeConnection(connection, prepareStatement);
            }
        }
        logger.info(operationSuccess);
        return operationSuccess;
    }

    @Override
    public BigDecimal countTotalMoneySpent(int idUser) throws DAOUserException, GetIncorrectParameterException {

        if (idUser < 0) {
            throw new GetIncorrectParameterException();
        }

        Connection con = null;
        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;
        BigDecimal total = BigDecimal.ZERO;
        try {
            con = connectionPool.takeConnection();
            prepareStatement = con.prepareStatement(UserDAOParam.COUNT_TOTAL_MONEY_SPENT);
            prepareStatement.setInt(1, idUser);
            resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                BigDecimal count = resultSet.getBigDecimal(COUNT);
                int discount = resultSet.getInt(DISCOUNT);
                total = total.add(count.subtract(countNumeralValueOfDiscount(count, discount)));
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            throw new DAOUserException(e);
        } finally {
            if (con != null) {
                connectionPool.closeConnection(con, prepareStatement, resultSet);
            }
        }
        logger.info("Total BigDecimal = " + total.toString());
        return total;
    }

    @Override
    public int getDiscountByID(int idDiscount) throws DAOUserException, GetIncorrectParameterException {

        if (idDiscount < 0) {
            throw new GetIncorrectParameterException();
        }
        int sizeOfDiscount = 0;
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.takeConnection();
            prepareStatement = connection.prepareStatement(UserDAOParam.SELECT_DISCOUNT_BY_ID);
            prepareStatement.setInt(1, idDiscount);
            resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                sizeOfDiscount = resultSet.getInt(SIZE_OF_DISCOUNT);
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Can't select user by login and password." + e);
            throw new DAOUserException(e);
        } finally {
            if (connection != null) {
                connectionPool.closeConnection(connection, prepareStatement, resultSet);
            }
        }
        return sizeOfDiscount;
    }

}