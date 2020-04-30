package by.epam.travel_agency.dao;

import by.epam.travel_agency.bean.User;
import by.epam.travel_agency.dao.connection_pool.ConnectionPool;
import by.epam.travel_agency.dao.connection_pool.ConnectionPoolException;
import by.epam.travel_agency.dao.connection_pool.ConnectionPoolFactory;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements AbstractDao<User> {

    private static final Logger logger = Logger.getLogger(UserDaoImpl.class);

    private final static String LOGIN = "SELECT * FROM bustravelagency.users WHERE Login = ? AND Password = ?";
    private final static String INSERT = "INSERT INTO bustravelagency.users(Login, Password, Firstname, Lastname) VALUES(?,?,?,?)";
    private static final String SELECT_USERS_BY_LOGIN = "SELECT * FROM bustravelagency.users WHERE Login = ?";

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
    public boolean add(User user) {

        boolean flag = false;
        ConnectionPoolFactory connectionPoolFactory = ConnectionPoolFactory.getInstance();
        ConnectionPool connectionPool = connectionPoolFactory.getConnectionPool();
        Connection con = null;
        try {
            con = connectionPool.takeConnection();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }

        String firstname = user.getFirstname();
        String lastname = user.getLastname();
        String username = user.getLogin();
        String password = user.getPassword();

        ConnectionPool pool = null;
        PreparedStatement pstmt = null;

        try {
            pstmt = con.prepareStatement(INSERT);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, firstname);
            pstmt.setString(4, lastname);
            pstmt.executeUpdate();

            int count = pstmt.executeUpdate();
            if (count == 1) {
                flag = true;
            }
        } catch (SQLException e) {
            logger.debug("Can't insert user." + e);
        } finally {
            connectionPool.dispose();
            return flag;
        }
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
        return user;
    }

    @Override
    public User findEntityByLogin(String login) {
        User user = null;
        ConnectionPoolFactory connectionPoolFactory = ConnectionPoolFactory.getInstance();
        ConnectionPool connectionPool = connectionPoolFactory.getConnectionPool();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement prepareStatement = connection.prepareStatement(SELECT_USERS_BY_LOGIN);
            prepareStatement.setString(1, login);
            ResultSet resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setFirstname(resultSet.getString("email"));
                user.setLastname(resultSet.getString("email"));
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.debug("Can't select user by login." + e);
        } finally {
            // connectionPool.freeConnection(connection);
        }
        return user;
    }


}