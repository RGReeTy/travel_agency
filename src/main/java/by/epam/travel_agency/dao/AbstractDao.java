package by.epam.travel_agency.dao;

import by.epam.travel_agency.bean.User;


public interface AbstractDao<T> {

	int countAllRows();

	//T findEntityById(Integer id) throws DAOException;

	User findEntityByLoginAndPassword(String login, String password);

	boolean findEntityByLogin(String login);

	boolean addNewUserToDB(User user) throws DAOException;

}