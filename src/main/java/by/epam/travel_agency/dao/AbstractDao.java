package main.java.by.epam.travel_agency.dao;

import main.java.by.epam.travel_agency.bean.User;


public interface AbstractDao<T> {

	//List<T> findAll() throws DAOException;

	//T findEntityById(Integer id) throws DAOException;

	User findEntityByLoginAndPassword(String login, String password);

	boolean add(User user) throws DAOException;

}