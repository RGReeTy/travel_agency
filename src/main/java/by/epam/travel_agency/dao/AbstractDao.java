package by.epam.pharmacy.dao;

import java.util.List;

import main.java.by.epam.travel_agency.dao.DAOException;

public interface AbstractDao<T> {

	List<T> findAll() throws DAOException;

	T findEntityById(Integer id) throws DAOException;

	boolean delete(Integer id) throws DAOException;

	boolean delete(T entity) throws DAOException;

	boolean add(T entity) throws DAOException;

	T update(T entity) throws DAOException;
}