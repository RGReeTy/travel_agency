package by.epam.travel_agency.dao;

import by.epam.travel_agency.bean.User;
import by.epam.travel_agency.dao.exception.DAOUserException;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;


/**
 * The interface User dao.
 */
public interface UserDAO {

    /**
     * Count all rows int.
     *
     * @return the int
     * @throws DAOUserException the dao user exception
     */
    int countAllRows() throws DAOUserException;

    /**
     * Find user by login user.
     *
     * @param login the login
     * @return the user
     * @throws DAOUserException the dao user exception
     */
    User findUserByLogin(String login) throws DAOUserException;

    /**
     * Find entity by login boolean.
     *
     * @param login the login
     * @return the boolean
     * @throws DAOUserException the dao user exception
     */
    boolean findEntityByLogin(String login) throws DAOUserException;

    /**
     * Add new user to db boolean.
     *
     * @param user the user
     * @return the boolean
     * @throws DAOUserException the dao user exception
     */
    boolean addNewUserToDB(User user) throws DAOUserException;

    /**
     * Find entity by id user.
     *
     * @param id_user the id user
     * @return the user
     * @throws DAOUserException the dao user exception
     */
    User findEntityById(int id_user) throws DAOUserException;

    /**
     * Count all users by level access hash map.
     *
     * @return the hash map
     * @throws DAOUserException the dao user exception
     */
    HashMap<Integer, Integer> countAllUsersByLevelAccess() throws DAOUserException;

    /**
     * Gets all users for changing level access.
     *
     * @return the all users for changing level access
     * @throws DAOUserException the dao user exception
     */
    List<User> getAllUsersForChangingLevelAccess() throws DAOUserException;

    /**
     * Update user status boolean.
     *
     * @param id_user the id user
     * @param status  the status
     * @return the boolean
     * @throws DAOUserException the dao user exception
     */
    boolean updateUserStatus(int id_user, int status) throws DAOUserException;

    /**
     * Count total money spent big decimal.
     *
     * @param id_user the id user
     * @return the big decimal
     * @throws DAOUserException the dao user exception
     */
    BigDecimal countTotalMoneySpent(int id_user) throws DAOUserException;

    /**
     * Update user info boolean.
     *
     * @param user the user
     * @return the boolean
     * @throws DAOUserException the dao user exception
     */
    boolean updateUserInfo(User user) throws DAOUserException;

}