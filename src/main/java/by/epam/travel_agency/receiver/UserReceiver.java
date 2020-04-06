package main.java.by.epam.travel_agency.receiver;

import main.java.by.epam.travel_agency.bean.User;
import main.java.by.epam.travel_agency.dao.UserDaoImpl;

public class UserReceiver {

    private UserDaoImpl userDao = UserDaoImpl.getInstance();

    private static UserReceiver instance = new UserReceiver();

    private UserReceiver() {
    }

    public static UserReceiver getInstance() {
        return instance;
    }

//	public List<User> receiverUserFindAll() throws ReceiverException {
//		ArrayList<User> users = null;
//		try {
//			users = (ArrayList<User>) instance.userDao.findAll();
//		} catch (DAOException e) {
//			new ReceiverException("Exception in receiverUserFindAll method", e);
//		}
//		return users;
//	}

//	public User receiverUserFindById(Integer id) throws ReceiverException {
//		User user = null;
//		try {
//			user = instance.userDao.findEntityById(id);
//		} catch (DAOException e) {
//			new ReceiverException("Exception in receiverUserfindById method", e);
//		}
//		return user;
//	}

    public User receiverUserFindByLoginAndPassword(String login, String password) throws ReceiverException {
        User user = null;
        user = instance.userDao.findEntityByLoginAndPassword(login, password);
        return user;
    }

//	public boolean receiverUserDelete(Integer id) throws ReceiverException {
//		try {
//			instance.userDao.delete(id);
//		} catch (DAOException e){
//			new ReceiverException("Exception in receiverUserDelete method", e);
//		}
//		return true;
//	}

//	public boolean receiverUserDelete(User user) throws ReceiverException {
//		try {
//			instance.userDao.delete(user);
//		} catch (DAOException e){
//			new ReceiverException("Exception in receiverUserDelete method", e);
//		}
//		return true;
//	}

    public boolean receiverUserAdd(User user) throws ReceiverException {
        instance.userDao.add(user);
        return true;
    }


    public User receiverUserFindByLogin(String login) {
        User user = instance.userDao.findEntityByLogin(login);
        return user;
    }
}