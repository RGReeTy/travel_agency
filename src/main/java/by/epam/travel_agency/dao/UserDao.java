package by.epam.travel_agency.dao;

import by.epam.travel_agency.bean.User;
import by.epam.travel_agency.dao.exception.DAOUserException;

import java.util.HashMap;
import java.util.List;


public interface UserDao {

    int countAllRows() throws DAOUserException;

    User findEntityByLoginAndPassword(String login, String password) throws DAOUserException;

    boolean findEntityByLogin(String login) throws DAOUserException;

    boolean addNewUserToDB(User user) throws DAOUserException, DAOUserException;

    User findEntityById(int id_user) throws DAOUserException;

    HashMap<Integer, Integer> countAllUsersByLevelAccess() throws DAOUserException;

    List<User> getAllUsersForChangingLevelAccess() throws DAOUserException;

    boolean updateUserStatus(int id_user, int status) throws DAOUserException;

}