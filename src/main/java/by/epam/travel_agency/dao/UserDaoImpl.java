package by.epam.travel_agency.dao;

import by.epam.travel_agency.bean.User;
import by.epam.travel_agency.dao.connection_pool.ConnectionPool;
import by.epam.travel_agency.dao.connection_pool.ConnectionPoolException;
import by.epam.travel_agency.dao.exception.DAOUserException;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static by.epam.travel_agency.service.util.FinalPriceMaker.countNumeralValueOfDiscount;

public class UserDaoImpl implements UserDao {

    private static final Logger logger = Logger.getLogger(UserDaoImpl.class);

    private final static String LOGIN = "SELECT * FROM bustravelagency.users WHERE Login = ? AND Password = ?";
    // private final static String INSERT = "INSERT INTO bustravelagency.users(Login, Password, Firstname, Lastname) VALUES(?,?,?,?)";
    private final static String INSERT_FULL_INFO = "INSERT INTO bustravelagency.users(id_User, Login, Password, Email, Firstname, Lastname, Phone, id_Discount, Level_access) VALUES(?,?,?,?,?,?,?,?,?)";
    private static final String SELECT_USERS_BY_LOGIN = "SELECT * FROM bustravelagency.users WHERE Login = ?";
    private static final String COUNT_ALL_USERS = "SELECT COUNT(*) FROM bustravelagency.users";
    private static final String SELECT_ID_LOGIN_LA = "SELECT Id_User, Login, Level_access FROM users ORDER BY Level_access";
    private static final String SELECT_USERS_BY_ID_USER = "SELECT * FROM bustravelagency.users WHERE id_User= ?";
    private static final String COUNT_USERS_BY_LEVEL_ACCESS = "SELECT bustravelagency.users.Level_access, " +
            "COUNT(bustravelagency.users.Level_access) AS Count FROM users\n" +
            "GROUP BY Level_access ORDER BY Level_access";
    private static final String UPDATE_USER_STATUS = "UPDATE users SET Level_access=? WHERE id_User=?";
    private static final String COUNT_TOTAL_MONEY_SPENT = "SELECT Count, Size_of_discount AS Discount FROM request\n" +
            "JOIN discount ON request.id_Discount = discount.id_Discount WHERE Id_User = ?";


//	private static final String SQL_SELECT_USERS = "SELECT * FROM final_project.users";
//	private static final String SQL_SELECT_USERS_BY_ID_USER = "SELECT * FROM final_project.users WHERE id= ?";
//	private static final String SQL_DELETE_USERS = "DELETE FROM final_project.users WHERE id= ?";
//	private static final String SQL_INSERT_USERS = "INSERT INTO final_project.users VALUES (?,?,?,?,?,?)";
//	private static final String SQL_SELECT_USERS_BY_LOGIN_AND_PASSWORD = "SELECT * FROM final_project.users JOIN final_project.access_level ON (users.access_level_id=access_level.id) WHERE users.login= ? AND users.password= ?";
//	private static final String SQL_SELECT_USERS_BY_LOGIN = "SELECT * FROM final_project.users WHERE login= ?";
//	private static final String SQL_UPDATE_COUNT = "UPDATE final_project.users SET account=? WHERE id=?";

    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static UserDaoImpl instance = new UserDaoImpl();

    public UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        return instance;
    }

    @Override
    public boolean addNewUserToDB(User user) throws DAOUserException {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = connectionPool.takeConnection();
            pstmt = conn.prepareStatement(INSERT_FULL_INFO);
            pstmt.setInt(1, user.getId_user());
            pstmt.setString(2, user.getLogin());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getEmail());
            pstmt.setString(5, user.getFirstname());
            pstmt.setString(6, user.getLastname());
            pstmt.setString(7, user.getPhone());
            pstmt.setInt(8, user.getId_discount());
            pstmt.setInt(9, user.getLevel_access());

            int count = pstmt.executeUpdate();
            if (count == 1) {
                flag = true;
                logger.info("User was succesfully cr8");
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.debug("Can't insert user." + e);
            throw new DAOUserException(e);
        } finally {
            assert conn != null;
            connectionPool.closeConnection(conn, pstmt);
        }
        return flag;
    }

    @Override
    public User findEntityById(int id_user) throws DAOUserException {
        logger.info("findEntityById message");

        User user = null;
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionPool.takeConnection();
            prepareStatement = connection.prepareStatement(SELECT_USERS_BY_ID_USER);
            prepareStatement.setInt(1, id_user);
            resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId_user(id_user);
                user.setLogin(resultSet.getString("Login"));
                user.setEmail(resultSet.getString("Email"));
                user.setFirstname(resultSet.getString("Firstname"));
                user.setLastname(resultSet.getString("Lastname"));
                user.setPhone(resultSet.getString("Phone"));
                user.setId_discount(resultSet.getInt("id_Discount"));
                user.setLevel_access(resultSet.getInt("Level_access"));

                logger.info("User was creating: " + user.toString());
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.debug("Can't select user by id." + e);
            throw new DAOUserException(e);
        } finally {
            assert connection != null;
            connectionPool.closeConnection(connection, prepareStatement, resultSet);
        }
        return user;
    }

    @Override
    public User findEntityByLoginAndPassword(String login, String password) throws DAOUserException {
        User user = null;
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.takeConnection();
            prepareStatement = connection.prepareStatement(LOGIN);
            prepareStatement.setString(1, login);
            prepareStatement.setString(2, password);
            resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId_user(resultSet.getInt("id_User"));
                user.setLogin(resultSet.getString("Login"));
                user.setEmail(resultSet.getString("Email"));
                user.setPhone(resultSet.getString("Phone"));
                user.setFirstname(resultSet.getString("Firstname"));
                user.setLastname(resultSet.getString("Lastname"));
                user.setLevel_access(resultSet.getInt("Level_access"));

                logger.info("findEntityByLoginAndPassword message - User was creating");
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.debug("Can't select user by login and password." + e);
            throw new DAOUserException(e);
        } finally {
            assert connection != null;
            connectionPool.closeConnection(connection, prepareStatement, resultSet);
        }
        logger.info(user != null ? user.toString() : "user is null!");
        return user;
    }

    @Override
    public boolean findEntityByLogin(String login) throws DAOUserException {
        logger.info("findEntityByLogin start");
        boolean isExist = false;
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.takeConnection();
            prepareStatement = connection.prepareStatement(SELECT_USERS_BY_LOGIN);
            prepareStatement.setString(1, login);
            resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                isExist = true;
            } else {
                logger.debug("resultSet is null");
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.debug("Can't select user by login. " + e);
            throw new DAOUserException(e);
        } finally {
            assert connection != null;
            connectionPool.closeConnection(connection, prepareStatement, resultSet);
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
            rs = stmt.executeQuery(COUNT_ALL_USERS);

            while (rs.next()) {
                count = (rs.getInt(1));
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.debug("Can't insert user." + e);
            throw new DAOUserException(e);
        } finally {
            assert con != null;
            connectionPool.closeConnection(con, stmt, rs);
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
            rs = stmt.executeQuery(COUNT_USERS_BY_LEVEL_ACCESS);
            while (rs.next()) {
                levelAccess = rs.getInt("Level_access");
                count = rs.getInt("Count");
                usersByLevelAccess.put(levelAccess, count);
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.debug("Can't insert user." + e);
            throw new DAOUserException(e);
        } finally {
            assert con != null;
            connectionPool.closeConnection(con, stmt, rs);
        }
        logger.info("before returning HashMap: size = " + usersByLevelAccess.size());
        return usersByLevelAccess;
    }

    @Override
    public List<User> getAllUsersForChangingLevelAccess() throws DAOUserException {
        Connection con = null;
        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;
        List<User> userList = new ArrayList<>();
        try {
            con = connectionPool.takeConnection();
            prepareStatement = con.prepareStatement(SELECT_ID_LOGIN_LA);
            resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId_user(resultSet.getInt("Id_User"));
                user.setLogin(resultSet.getString("Login"));
                user.setLevel_access(resultSet.getInt("Level_access"));
                userList.add(user);
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.debug(e);
            throw new DAOUserException(e);
        } finally {
            assert con != null;
            connectionPool.closeConnection(con, prepareStatement, resultSet);
        }
        logger.info(userList.size());
        return userList;
    }

    @Override
    public boolean updateUserStatus(int id_user, int status) throws DAOUserException {
        boolean operationSuccess = false;
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        try {
            connection = connectionPool.takeConnection();
            prepareStatement = connection.prepareStatement(UPDATE_USER_STATUS);
            prepareStatement.setInt(1, status);
            prepareStatement.setInt(2, id_user);
            if (prepareStatement.executeUpdate() == 1) {
                operationSuccess = true;
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.debug("Operation UPDATE is broke: " + e);
            throw new DAOUserException(e);
        } finally {
            assert connection != null;
            connectionPool.closeConnection(connection, prepareStatement);
        }
        logger.info(operationSuccess);
        return operationSuccess;
    }

    @Override
    public BigDecimal countTotalMoneySpent(int id_user) throws DAOUserException {
        Connection con = null;
        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;
        BigDecimal total = BigDecimal.ZERO;
        try {
            con = connectionPool.takeConnection();
            prepareStatement = con.prepareStatement(COUNT_TOTAL_MONEY_SPENT);
            prepareStatement.setInt(1, id_user);
            resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                BigDecimal count = resultSet.getBigDecimal("Count");
                int discount = resultSet.getInt("Discount");
                total = total.add(count.subtract(countNumeralValueOfDiscount(count, discount)));
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.debug(e);
            throw new DAOUserException(e);
        } finally {
            assert con != null;
            connectionPool.closeConnection(con, prepareStatement, resultSet);
        }
        logger.info("Total BigDecimal = " + total.toString());
        return total;
    }

}