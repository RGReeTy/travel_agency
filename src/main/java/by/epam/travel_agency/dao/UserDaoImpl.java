package by.epam.travel_agency.dao;

import by.epam.travel_agency.bean.User;
import by.epam.travel_agency.dao.connection_pool.ConnectionPool;
import by.epam.travel_agency.dao.connection_pool.ConnectionPoolException;
import by.epam.travel_agency.dao.connection_pool.ConnectionPoolFactory;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.*;

public class UserDaoImpl implements UserDao {

    private static final Logger logger = Logger.getLogger(UserDaoImpl.class);

    private final static String LOGIN = "SELECT * FROM bustravelagency.users WHERE Login = ? AND Password = ?";
    private final static String INSERT = "INSERT INTO bustravelagency.users(Login, Password, Firstname, Lastname) VALUES(?,?,?,?)";
    private final static String INSERT_FULL_INFO = "INSERT INTO bustravelagency.users(id_User, Login, Password, Firstname, Lastname, Phone, id_Discount, Level_access) VALUES(?,?,?,?,?,?,?,?)";
    private static final String SELECT_USERS_BY_LOGIN = "SELECT * FROM bustravelagency.users WHERE Login = ?";
    private static final String COUNT_ALL_USERS = "SELECT COUNT(*) FROM bustravelagency.users";
    private static final String SELECT_ID_LOGIN_LA = "SELECT Id_User, Login, Level_access FROM users ORDER BY Level_access";
    private static final String SELECT_USERS_BY_ID_USER = "SELECT * FROM bustravelagency.users WHERE id_User= ?";
    private static final String COUNT_USERS_BY_LEVEL_ACCESS = "SELECT bustravelagency.users.Level_access, " +
            "COUNT(bustravelagency.users.Level_access) AS Count FROM users\n" +
            "GROUP BY Level_access ORDER BY Level_access";

//	private static final String SQL_SELECT_USERS = "SELECT * FROM final_project.users";
//	private static final String SQL_SELECT_USERS_BY_ID_USER = "SELECT * FROM final_project.users WHERE id= ?";
//	private static final String SQL_DELETE_USERS = "DELETE FROM final_project.users WHERE id= ?";
//	private static final String SQL_INSERT_USERS = "INSERT INTO final_project.users VALUES (?,?,?,?,?,?)";
//	private static final String SQL_SELECT_USERS_BY_LOGIN_AND_PASSWORD = "SELECT * FROM final_project.users JOIN final_project.access_level ON (users.access_level_id=access_level.id) WHERE users.login= ? AND users.password= ?";
//	private static final String SQL_SELECT_USERS_BY_LOGIN = "SELECT * FROM final_project.users WHERE login= ?";
//	private static final String SQL_UPDATE_COUNT = "UPDATE final_project.users SET account=? WHERE id=?";

    private static UserDaoImpl instance = new UserDaoImpl();

    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        return instance;
    }

    @Override
    public boolean addNewUserToDB(User user) {

        boolean flag = false;
        ConnectionPoolFactory connectionPoolFactory = ConnectionPoolFactory.getInstance();
        ConnectionPool connectionPool = connectionPoolFactory.getConnectionPool();
        Connection con = null;
        try {
            connectionPool.initPoolData();
            con = connectionPool.takeConnection();
        } catch (ConnectionPoolException e) {
            logger.debug(e);
        }

        PreparedStatement pstmt = null;

        try {
            pstmt = con.prepareStatement(INSERT_FULL_INFO);
            pstmt.setInt(1, user.getId_user());
            pstmt.setString(2, user.getLogin());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getFirstname());
            pstmt.setString(5, user.getLastname());
            pstmt.setString(6, user.getPhone());
            pstmt.setInt(7, user.getId_discount());
            pstmt.setInt(8, user.getLevel_access());

            logger.info("pstm and all set's are cr8");


            int count = pstmt.executeUpdate();
            logger.debug("inserts " + count + " rows");
            if (count == 1) {
                flag = true;
                logger.info("User was succesfully cr8");
            }
        } catch (SQLException e) {
            logger.debug("Can't insert user." + e);
        } finally {
            connectionPool.dispose();
            return flag;
        }
    }

    @Override
    public User findEntityById(int id_user) {
        logger.info("findEntityById message");

        User user = null;
        ConnectionPoolFactory connectionPoolFactory = ConnectionPoolFactory.getInstance();
        ConnectionPool connectionPool = connectionPoolFactory.getConnectionPool();
        try {
            connectionPool.initPoolData();
            Connection connection = connectionPool.takeConnection();
            PreparedStatement prepareStatement = connection.prepareStatement(SELECT_USERS_BY_ID_USER);

            prepareStatement.setInt(1, id_user);

            ResultSet resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId_user(id_user);
                user.setLogin(resultSet.getString("Login"));
                user.setFirstname(resultSet.getString("Firstname"));
                user.setLastname(resultSet.getString("Lastname"));
                user.setPhone(resultSet.getString("Phone"));
                user.setId_discount(resultSet.getInt("id_Discount"));
                user.setLevel_access(resultSet.getInt("Level_access"));


                logger.info("User was creating: " + user.toString());
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.debug("Can't select user by id." + e);
        } finally {
            //connectionPool.freeConnection(connection);
        }
        return user;
    }

    @Override
    public User findEntityByLoginAndPassword(String login, String password) {

        logger.info("findEntityByLoginAndPassword message");

        User user = null;
        ConnectionPoolFactory connectionPoolFactory = ConnectionPoolFactory.getInstance();
        ConnectionPool connectionPool = connectionPoolFactory.getConnectionPool();
        try {
            logger.info("Before initPoolData");

            connectionPool.initPoolData();

            logger.info("After initPoolData");

            Connection connection = connectionPool.takeConnection();
            PreparedStatement prepareStatement = connection.prepareStatement(LOGIN);

            logger.info("After pstm");

            prepareStatement.setString(1, login);
            prepareStatement.setString(2, password);

            logger.info("result set message " + login + " " + password);

            ResultSet resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setLogin(resultSet.getString("Login"));
                user.setPassword(resultSet.getString("Password"));
                user.setFirstname(resultSet.getString("Firstname"));
                user.setLastname(resultSet.getString("Lastname"));

                logger.info("findEntityByLoginAndPassword message - User was creating");
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.debug("Can't select user by login and password." + e);
        } finally {
            //connectionPool.freeConnection(connection);
        }
        logger.info(user.toString());
        return user;
    }

    @Override
    public boolean findEntityByLogin(String login) {
        logger.info("findEntityByLogin start");
        boolean isExist = false;
        ConnectionPoolFactory connectionPoolFactory = ConnectionPoolFactory.getInstance();
        ConnectionPool connectionPool = connectionPoolFactory.getConnectionPool();
        logger.debug("ConnectionPool factory");
        try {
            connectionPool.initPoolData();
            Connection connection = connectionPool.takeConnection();
            PreparedStatement prepareStatement = connection.prepareStatement(SELECT_USERS_BY_LOGIN);
            prepareStatement.setString(1, login);
            ResultSet resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                isExist = true;
            } else {
                logger.debug("resultSet is null");
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.debug("Can't select user by login. " + e);
        } finally {
            // connectionPool.freeConnection(connection);
        }
        logger.info(isExist);
        return isExist;
    }

    @Override
    public int countAllRows() {
        ConnectionPoolFactory connectionPoolFactory = ConnectionPoolFactory.getInstance();
        ConnectionPool connectionPool = connectionPoolFactory.getConnectionPool();
        Connection con = null;
        int count = 0;
        try {
            connectionPool.initPoolData();
            con = connectionPool.takeConnection();
        } catch (ConnectionPoolException e) {
            logger.debug(e);
        }

        Statement stmt = null;

        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(COUNT_ALL_USERS);

            while (rs.next()) {
                count = (rs.getInt(1));
            }
        } catch (SQLException e) {
            logger.debug("Can't insert user." + e);
        } finally {
            //connectionPool.dispose();
        }
        logger.info("After count all users: count=" + count);
        return count;
    }

    @Override
    public HashMap<Integer, Integer> countAllUsersByLevelAccess() {
        ConnectionPoolFactory connectionPoolFactory = ConnectionPoolFactory.getInstance();
        ConnectionPool connectionPool = connectionPoolFactory.getConnectionPool();
        Connection con = null;
        HashMap<Integer, Integer> usersByLevelAccess = new HashMap<>();
        int levelAccess = 0;
        int count = 0;

        try {
            connectionPool.initPoolData();
            con = connectionPool.takeConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(COUNT_USERS_BY_LEVEL_ACCESS);
            while (rs.next()) {
                levelAccess = rs.getInt("Level_access");
                count = rs.getInt("Count");
                usersByLevelAccess.put(levelAccess, count);
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.debug("Can't insert user." + e);
        } finally {
            //connectionPool.dispose();
        }
        logger.info("before returning HashMap: size = " + usersByLevelAccess.size());
        return usersByLevelAccess;
    }

    @Override
    public List<User> getAllUsersForChangingLevelAccess() {
        ConnectionPoolFactory connectionPoolFactory = ConnectionPoolFactory.getInstance();
        ConnectionPool connectionPool = connectionPoolFactory.getConnectionPool();
        Connection con = null;
        List<User> userList = new ArrayList<>();
        try {
            connectionPool.initPoolData();
            con = connectionPool.takeConnection();
            PreparedStatement prepareStatement = con.prepareStatement(SELECT_ID_LOGIN_LA);
            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId_user(resultSet.getInt("Id_User"));
                user.setLogin(resultSet.getString("Login"));
                user.setLevel_access(resultSet.getInt("Level_access"));
                userList.add(user);
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.debug(e);
        } finally {
            //connectionPool.free
        }
        logger.info(userList.size());
        return userList;
    }

    ;

}