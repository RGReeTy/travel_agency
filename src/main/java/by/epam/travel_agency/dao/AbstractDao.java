package by.epam.travel_agency.dao;

import by.epam.travel_agency.bean.User;


public interface AbstractDao<T> {

	//List<T> findAll() throws DAOException;

	//T findEntityById(Integer id) throws DAOException;

	User findEntityByLoginAndPassword(String login, String password);

	User findEntityByLogin(String login);

	boolean add(User user) throws DAOException;

}