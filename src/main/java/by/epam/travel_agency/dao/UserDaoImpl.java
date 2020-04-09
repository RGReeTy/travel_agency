package by.epam.travel_agency.dao;

import by.epam.travel_agency.bean.Order;
import by.epam.travel_agency.bean.User;
import by.epam.travel_agency.dao.connection_pool.ConnectionPool;
import by.epam.travel_agency.dao.connection_pool.ConnectionPoolException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements AbstractDao<User> {
    private static Logger LOGGER = LogManager.getLogger(UserDaoImpl.class);

    private final static String LOGIN = "SELECT * FROM user_test.user WHERE username = ? AND password = ?";
    private final static String INSERT = "INSERT INTO user_test.user(username,password, firstname,lastname) VALUES(?,?,?,?)";
    private final static String GET_INFO = "SELECT user_test.orders.Id, CreatedAt, Count FROM user_test.orders WHERE Customer_nickname = ? ";
    private static final String SELECT_USERS_BY_LOGIN = "SELECT * FROM user_test.user WHERE username = ?";

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
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection con = null;
        try {
            con = connectionPool.takeConnection();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }

        String firstname = user.getFirstname();
        String lastname = user.getLastname();
        String username = user.getUsername();
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
            LOGGER.log(Level.WARN, "Can't insert user." + e);
        } finally {
            connectionPool.freeConnection(con);
            return flag;
        }
    }


    private Order parseOrder(ResultSet resultSet) throws SQLException {
        int idOrder = resultSet.getInt("id");
        String dateOrder = resultSet.getString("CreatedAt");
        int countOrder = resultSet.getInt("Count");
        return new Order(idOrder, dateOrder, countOrder);

    }


    public User findEntityByLoginAndPassword(String login, String password) {

        //DELETE
        System.out.println("findEntityByLoginAndPassword message");

        User user = null;
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        try {
            connectionPool.poolInitialization();
            Connection connection = connectionPool.takeConnection();
            PreparedStatement prepareStatement = connection.prepareStatement(LOGIN);
            prepareStatement.setString(1, login);
            prepareStatement.setString(2, password);

            //DELETE
            System.out.println("result set message"+login + " " +password);

            ResultSet resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setFirstname(resultSet.getString("firstname"));
                user.setLastname(resultSet.getString("lastname"));

                //DELETE
                System.out.println("findEntityByLoginAndPassword message - User was creating");
            }
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.log(Level.WARN, "Can't select user by login and password." + e);
        } finally {
            //connectionPool.freeConnection(connection);
        }
        return user;
    }

    @Override
    public User findEntityByLogin(String login) {
        User user = null;
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement prepareStatement = connection.prepareStatement(SELECT_USERS_BY_LOGIN);
            prepareStatement.setString(1, login);
            ResultSet resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setUsername(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setFirstname(resultSet.getString("email"));
                user.setLastname(resultSet.getString("email"));
            }
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.log(Level.WARN, "Can't select user by login." + e);
        } finally {
           // connectionPool.freeConnection(connection);
        }
        return user;
    }


}