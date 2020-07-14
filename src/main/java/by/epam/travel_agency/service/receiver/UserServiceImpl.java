package by.epam.travel_agency.service.receiver;

import by.epam.travel_agency.bean.User;
import by.epam.travel_agency.controller.param_name.MessageKey;
import by.epam.travel_agency.dao.UserDAO;
import by.epam.travel_agency.dao.exception.DAOUserException;
import by.epam.travel_agency.dao.factory.DAOFactory;
import by.epam.travel_agency.dao.factory.DAOFactoryProvider;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {

    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

    private final DAOFactory daoFactory = DAOFactoryProvider.getSqlDaoFactory();
    private UserDAO userDao = daoFactory.getUserDao();

    private static final String MESSAGE = "message";
    private static final String ADMIN = "admin";
    private static final String MANAGER = "manager";
    private static final String USER = "user";


    @Override
    public List<User> findAllUsers() throws ReceiverException {
        try {
            return userDao.getAllUsersForChangingLevelAccess();
        } catch (DAOUserException e) {
            throw new ReceiverException(e);
        }
    }

    @Override
    public User findUserById(int id) throws ReceiverException {
        User user = null;
        try {
            user = userDao.findEntityById(id);
        } catch (DAOUserException e) {
            throw new ReceiverException(e);
        }
        return user;
    }

    @Override
    public User findUserByLoginAndPassword(String login, String password) throws ReceiverException {
        User user = null;
        try {
            user = userDao.findEntityByLoginAndPassword(login, password);
        } catch (DAOUserException e) {
            throw new ReceiverException(e);
        }
        return user;
    }

    @Override
    public Map<String, Integer> countAllUsersByLevelAccessMap() throws ReceiverException {

        Map<String, Integer> usersByLevelAccess = new HashMap<>();
        HashMap<Integer, Integer> usersDAO;
        try {
            usersDAO = userDao.countAllUsersByLevelAccess();
        } catch (DAOUserException e) {
            throw new ReceiverException(e);
        }
        if (!usersDAO.isEmpty()) {
            usersByLevelAccess.put(ADMIN, usersDAO.get(0));
            usersByLevelAccess.put(MANAGER, usersDAO.get(1));
            usersByLevelAccess.put(USER, usersDAO.get(2));
        }
        return usersByLevelAccess;
    }

    @Override
    public boolean updateUserStatusByID(int user_id, int status) throws ReceiverException {
        boolean flag = false;
        try {
            flag = userDao.updateUserStatus(user_id, status);
        } catch (DAOUserException e) {
            throw new ReceiverException(e);
        }
        return flag;
    }

    @Override
    public boolean addNewUser(User user) throws ReceiverException {
        logger.info("addNewUser is start");
        boolean isSuccessfullyCreateNewUser = false;

        try {
            user.setId_user(receiverCountUsersAtDB() + 1);
            isSuccessfullyCreateNewUser = userDao.addNewUserToDB(user);
        } catch (DAOUserException e) {
            logger.error(e);
            throw new ReceiverException(e);
        }

        logger.info(isSuccessfullyCreateNewUser);

        return isSuccessfullyCreateNewUser;
    }

    @Override
    public BigDecimal countingTotalMoneySpentForUserID(int id_user) throws ReceiverException {
        BigDecimal bigDecimal = null;
        try {
            bigDecimal = userDao.countTotalMoneySpent(id_user);
        } catch (DAOUserException e) {
            throw new ReceiverException(e);
        }
        return bigDecimal;
    }

    @Override
    public boolean isThisLoginBusy(String login) throws ReceiverException {
        try {
            return userDao.findEntityByLogin(login);
        } catch (DAOUserException e) {
            throw new ReceiverException(e);
        }
    }

    @Override
    public boolean updateUserInfo(User user, String firstname, String lastname, String email, String phone) throws ReceiverException {
        boolean successUpdate;
        if (user.getFirstname().equals(firstname) & user.getLastname().equals(lastname)
                & user.getEmail().equals(email) & user.getPhone().equals(phone)) {
            //Nothing to change
            logger.info("Nothing to change!");
            return false;
        }

        if (firstname != null && !firstname.isEmpty()) {
            user.setFirstname(firstname);
        }
        if (lastname != null && !lastname.isEmpty()) {
            user.setLastname(lastname);
        }
        if (email != null && !email.isEmpty()) {
            user.setEmail(email);
        }
        if (phone != null && !phone.isEmpty()) {
            user.setPhone(phone);
        }

        try {
            successUpdate = userDao.updateUserInfo(user);
        } catch (DAOUserException e) {
            throw new ReceiverException(e);
        }
        return successUpdate;
    }


    private int receiverCountUsersAtDB() throws ReceiverException {
        try {
            return userDao.countAllRows();
        } catch (DAOUserException e) {
            throw new ReceiverException(e);
        }
    }


}