package by.epam.travel_agency.dao;

import by.epam.travel_agency.bean.User;
import by.epam.travel_agency.dao.connection_pool.ConnectionPool;
import by.epam.travel_agency.dao.connection_pool.ConnectionPoolException;
import by.epam.travel_agency.dao.connection_pool.ConnectionPoolFactory;
import org.apache.log4j.Logger;

import java.sql.*;

public class UserDaoImpl implements UserDao<User> {

    private static final Logger logger = Logger.getLogger(UserDaoImpl.class);

    private final static String LOGIN = "SELECT * FROM bustravelagency.users WHERE Login = ? AND Password = ?";
    private final static String INSERT = "INSERT INTO bustravelagency.users(Login, Password, Firstname, Lastname) VALUES(?,?,?,?)";
    private final static String INSERT_FULL_INFO = "INSERT INTO bustravelagency.users(id_User, Login, Password, Firstname, Lastname, Phone, id_Discount, Level_access) VALUES(?,?,?,?,?,?,?,?)";
    private static final String SELECT_USERS_BY_LOGIN = "SELECT * FROM bustravelagency.users WHERE Login = ?";
    private static final String COUNT_ALL_USERS = "SELECT COUNT(*) FROM bustravelagency.users";
    private static final String SELECT_USERS_BY_ID_USER = "SELECT * FROM bustravelagency.users WHERE id= ?";

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


//    private Order parseOrder(ResultSet resultSet) throws SQLException {
//        int idOrder = resultSet.getInt("id");
//        String dateOrder = resultSet.getString("CreatedAt");
//        int countOrder = resultSet.getInt("Count");
//        return new Order(idOrder, dateOrder, countOrder);
//
//    }


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
            logger.debug("after ConnectionPool take connection");
            PreparedStatement prepareStatement = connection.prepareStatement(SELECT_USERS_BY_LOGIN);
            logger.debug("after pstm preload");
            prepareStatement.setString(1, login);
            logger.debug("after pstm setString " + login);
            ResultSet resultSet = prepareStatement.executeQuery();
            logger.debug("after resultSet executeQuery");
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


}