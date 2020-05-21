package by.epam.travel_agency.dao;

import by.epam.travel_agency.bean.User;

import java.util.HashMap;
import java.util.List;


public interface UserDao {

    int countAllRows();

    //T findEntityById(Integer id) throws DAOException;

    User findEntityByLoginAndPassword(String login, String password);

    boolean findEntityByLogin(String login);

    boolean addNewUserToDB(User user) throws DAOException;

    User findEntityById(int id_user);

    HashMap<Integer, Integer> countAllUsersByLevelAccess();

    List<User> getAllUsersForChangingLevelAccess();

}