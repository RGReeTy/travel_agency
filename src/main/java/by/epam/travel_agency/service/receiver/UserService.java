package by.epam.travel_agency.service.receiver;

import by.epam.travel_agency.bean.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * The interface User service.
 */
public interface UserService {

    /**
     * Find all users list.
     *
     * @return the list
     * @throws ReceiverException the receiver exception
     */
    List<User> findAllUsers() throws ReceiverException;

    /**
     * Find user by id user.
     *
     * @param id the id
     * @return the user
     * @throws ReceiverException the receiver exception
     */
    User findUserById(int id) throws ReceiverException;

    /**
     * Find user by login and password user.
     *
     * @param login    the login
     * @param password the password
     * @return the user
     * @throws ReceiverException the receiver exception
     */
    User findUserByLoginAndPassword(String login, String password) throws ReceiverException;

    /**
     * Count all users by level access map map.
     *
     * @return the map
     * @throws ReceiverException the receiver exception
     */
    Map<String, Integer> countAllUsersByLevelAccessMap() throws ReceiverException;

    /**
     * Update user status by id boolean.
     *
     * @param user_id the user id
     * @param status  the status
     * @return the boolean
     * @throws ReceiverException the receiver exception
     */
    boolean updateUserStatusByID(int user_id, int status) throws ReceiverException;

    /**
     * Add new user boolean.
     *
     * @param user the user
     * @return the boolean
     * @throws ReceiverException the receiver exception
     */
    boolean addNewUser(User user) throws ReceiverException;

    /**
     * Counting total money spent for user id big decimal.
     *
     * @param id_user the id user
     * @return the big decimal
     * @throws ReceiverException the receiver exception
     */
    BigDecimal countingTotalMoneySpentForUserID(int id_user) throws ReceiverException;

    /**
     * Update user info boolean.
     *
     * @param user      the user
     * @param firstname the firstname
     * @param lastname  the lastname
     * @param email     the email
     * @param phone     the phone
     * @return the boolean
     * @throws ReceiverException the receiver exception
     */
    boolean updateUserInfo(User user, String firstname, String lastname, String email, String phone) throws ReceiverException;

    /**
     * Is this login busy boolean.
     *
     * @param login the login
     * @return the boolean
     * @throws ReceiverException the receiver exception
     */
    boolean isThisLoginBusy(String login) throws ReceiverException;

    /**
     * Gets discount by id.
     *
     * @param id_discount the id discount
     * @return the discount by id
     * @throws ReceiverException the receiver exception
     */
    int getDiscountByID(int id_discount) throws ReceiverException;
}