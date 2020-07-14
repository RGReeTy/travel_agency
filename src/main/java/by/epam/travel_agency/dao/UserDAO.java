package by.epam.travel_agency.dao;

import by.epam.travel_agency.bean.User;
import by.epam.travel_agency.dao.exception.DAOUserException;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;


public interface UserDAO {

    int countAllRows() throws DAOUserException;

    User findUserByLogin(String login) throws DAOUserException;

    boolean findEntityByLogin(String login) throws DAOUserException;

    boolean addNewUserToDB(User user) throws DAOUserException;

    User findEntityById(int id_user) throws DAOUserException;

    HashMap<Integer, Integer> countAllUsersByLevelAccess() throws DAOUserException;

    List<User> getAllUsersForChangingLevelAccess() throws DAOUserException;

    boolean updateUserStatus(int id_user, int status) throws DAOUserException;

    BigDecimal countTotalMoneySpent(int id_user) throws DAOUserException;

    boolean updateUserInfo(User user) throws DAOUserException;

}