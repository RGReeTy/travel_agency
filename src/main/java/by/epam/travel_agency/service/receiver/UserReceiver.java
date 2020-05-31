package by.epam.travel_agency.service.receiver;

import by.epam.travel_agency.bean.User;
import by.epam.travel_agency.constant.MessageKey;
import by.epam.travel_agency.dao.exception.DAOUserException;
import by.epam.travel_agency.dao.UserDaoImpl;
import by.epam.travel_agency.service.validation.UserValidator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

public class UserReceiver {

    private static final Logger logger = Logger.getLogger(UserReceiver.class);

    private static final String IS_OK = "ok";
    private static final String MESSAGE = "message";
    private static final String ADMIN = "admin";
    private static final String MANAGER = "manager";
    private static final String USER = "user";
    private static final String LOGIN = "login";

    private UserDaoImpl userDao = UserDaoImpl.getInstance();

    private static UserReceiver instance = new UserReceiver();

    private UserReceiver() {
    }

    public static UserReceiver getInstance() {
        return instance;
    }


    public List<User> receiverUserFindAll() throws ReceiverException {
        try {
            return instance.userDao.getAllUsersForChangingLevelAccess();
        } catch (DAOUserException e) {
            throw new ReceiverException(e);
        }
    }

    public User receiverUserFindById(int id) throws ReceiverException {
        User user = null;
        try {
            user = instance.userDao.findEntityById(id);
        } catch (DAOUserException e) {
            throw new ReceiverException(e);
        }
        return user;
    }

    public User receiverUserFindByLoginAndPassword(String login, String password) throws ReceiverException {
        User user = null;
        try {
            user = instance.userDao.findEntityByLoginAndPassword(login, password);
        } catch (DAOUserException e) {
            throw new ReceiverException(e);
        }
        return user;
    }

    public HashMap<String, Integer> countAllUsersByLevelAccessMap() throws ReceiverException {

        HashMap<String, Integer> usersByLevelAccess = new HashMap<>();
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

    public boolean updateUserStatusByID(int user_id, int status) throws ReceiverException {
        boolean flag = false;
        try {
            flag = instance.userDao.updateUserStatus(user_id, status);
        } catch (DAOUserException e) {
            throw new ReceiverException(e);
        }
        return flag;
    }

    public boolean receiverUserAdd(HttpServletRequest request) throws ReceiverException {
        logger.info("receiverUserAdd is start");
        boolean isSuccessfullyCreateNewUser = false;
        if (UserValidator.isOkParametersOfNewUserBeforeCreating(request)) {
            if (isThisLoginBusy(request)) {
                logger.info("This login already exist!");
                request.setAttribute(MESSAGE, MessageKey.REGISTER_LOGIN_ERROR);
            } else {
                User user = creatNewUserFromRequest(request);

                logger.debug(user.toString());

                try {
                    isSuccessfullyCreateNewUser = instance.userDao.addNewUserToDB(user);
                } catch (DAOUserException e) {
                    throw new ReceiverException(e);
                }
            }
        } else {
            request.setAttribute(MESSAGE, MessageKey.REGISTER_SUCCESS);
        }
        logger.debug(isSuccessfullyCreateNewUser);

        return isSuccessfullyCreateNewUser;
    }


    private User creatNewUserFromRequest(HttpServletRequest request) throws ReceiverException {

        final String PARAM_NAME_LOGIN = "login";
        final String PARAM_NAME_PASSWORD = "password";
        final String PARAM_NAME_EMAIL = "email";
        final String PARAM_NAME_FIRSTNAME = "firstname";
        final String PARAM_NAME_LASTNAME = "lastname";
        final String PARAM_NAME_PHONE = "phone";

        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        String email = request.getParameter(PARAM_NAME_EMAIL);
        String firstname = request.getParameter(PARAM_NAME_FIRSTNAME);
        String lastname = request.getParameter(PARAM_NAME_LASTNAME);
        String phone = request.getParameter(PARAM_NAME_PHONE);

        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setEmail(email);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setPhone(phone);
        user.setId_discount(1);
        user.setLevel_access(2);

        logger.info(user.toString());

        String validationMessage = UserValidator.validateUserToMatchThePattern(user);

        logger.info("after validator: " + validationMessage);

        if (!validationMessage.equals(IS_OK)) {
            request.setAttribute(MESSAGE, validationMessage);
            logger.info(validationMessage);
            return null;
        }

        try {
            user.setId_user(receiverCountUsersAtDB() + 1);
        } catch (ReceiverException e) {
            logger.error(e);
            request.setAttribute(MESSAGE, MessageKey.DATABASE_ERROR);
            throw new ReceiverException(e);
        }
        return user;
    }

    private int receiverCountUsersAtDB() throws ReceiverException {
        try {
            return instance.userDao.countAllRows();
        } catch (DAOUserException e) {
            throw new ReceiverException(e);
        }
    }

    private boolean isThisLoginBusy(HttpServletRequest request) throws ReceiverException {
        try {
            return instance.userDao.findEntityByLogin(request.getParameter(LOGIN));
        } catch (DAOUserException e) {
            throw new ReceiverException(e);
        }
    }


}