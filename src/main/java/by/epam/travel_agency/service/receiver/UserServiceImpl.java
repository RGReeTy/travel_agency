package by.epam.travel_agency.service.receiver;

import by.epam.travel_agency.bean.User;
import by.epam.travel_agency.dao.UserDAO;
import by.epam.travel_agency.dao.exception.DAOUserException;
import by.epam.travel_agency.dao.exception.GetIncorrectParameterException;
import by.epam.travel_agency.dao.factory.DAOFactory;
import by.epam.travel_agency.dao.factory.DAOFactoryProvider;
import by.epam.travel_agency.service.util.HashStringHelper;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.epam.travel_agency.service.validation.ParamValidator.*;

/**
 * The type User service.
 */
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
        if (validatePositiveNumber(id)) {
            try {
                user = userDao.findEntityById(id);
            } catch (DAOUserException | GetIncorrectParameterException e) {
                throw new ReceiverException(e);
            }
        } else {
            logger.info("ID is non positive number!");
            throw new ReceiverException("ID is non positive number!");
        }
        return user;
    }

    @Override
    public User findUserByLoginAndPassword(String login, String password) throws ReceiverException {
        try {
            notEmpty(login);
            notEmpty(password);
        } catch (IllegalArgumentException e) {
            throw new ReceiverException(e);
        }

        User user = null;

        if (validateLatinLettersAndNumbers(login) & validateLatinLettersAndNumbers(password)) {

            try {
                boolean checkResult = false;

                user = userDao.findUserByLogin(login);
                if (user != null) {
                    checkResult = HashStringHelper.checkPassword(password, user.getPassword());
                }

                if (user == null || !checkResult) {
                    return new User();
                }

            } catch (DAOUserException | GetIncorrectParameterException e) {
                throw new ReceiverException(e);
            }
        }
        return user;
    }

    @Override
    public boolean updateUserStatusByID(int user_id, int status) throws ReceiverException {
        boolean flag = false;
        if (validatePositiveNumber(user_id) & validateId(status)) {
            try {
                flag = userDao.updateUserStatus(user_id, status);
            } catch (DAOUserException | GetIncorrectParameterException e) {
                throw new ReceiverException(e);
            }
        } else {
            throw new ReceiverException("Input params incorrect!");
        }
        return flag;
    }

    @Override
    public synchronized boolean addNewUser(User user) throws ReceiverException {
        final String SUFFIX = "S";
        boolean isSuccessfullyCreateNewUser = false;

        try {
            user.setIdUser(receiverCountUsersAtDB() + 1);
            while (isThisLoginBusy(user.getLogin())) {
                user.setLogin(user.getLogin() + SUFFIX);
            }

            String hashedPassword = HashStringHelper.hashPassword(user.getPassword());
            user.setPassword(hashedPassword);

            isSuccessfullyCreateNewUser = userDao.addNewUserToDB(user);

        } catch (DAOUserException e) {
            logger.error(e);
            throw new ReceiverException(e);
        }

        return isSuccessfullyCreateNewUser;
    }


    @Override
    public BigDecimal countingTotalMoneySpentForUserID(int id_user) throws ReceiverException {
        BigDecimal bigDecimal = null;

        if (validatePositiveNumber(id_user)) {
            try {
                bigDecimal = userDao.countTotalMoneySpent(id_user);
            } catch (DAOUserException | GetIncorrectParameterException e) {
                throw new ReceiverException(e);
            }
        }
        return bigDecimal;
    }

    @Override
    public boolean isThisLoginBusy(String login) throws ReceiverException {
        notEmpty(login);

        try {
            if (validateStringWithSymbolsAndNumbers(login)) {
                return userDao.findEntityByLogin(login);
            } else {
                return false;
            }
        } catch (DAOUserException | GetIncorrectParameterException e) {
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
    public int getDiscountByID(int id_discount) throws ReceiverException {

        try {

            if (validateId(id_discount)) {

                return userDao.getDiscountByID(id_discount);

            } else {
                return 0;
            }
        } catch (DAOUserException | GetIncorrectParameterException e) {

            throw new ReceiverException(e);

        }
    }


    private int receiverCountUsersAtDB() throws ReceiverException {

        try {

            return userDao.countAllRows();

        } catch (DAOUserException e) {

            throw new ReceiverException(e);

        }
    }


}