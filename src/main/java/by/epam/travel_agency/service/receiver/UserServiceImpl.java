package by.epam.travel_agency.service.receiver;

import by.epam.travel_agency.bean.User;
import by.epam.travel_agency.controller.MessageKey;
import by.epam.travel_agency.dao.UserDAO;
import by.epam.travel_agency.dao.exception.DAOUserException;
import by.epam.travel_agency.dao.factory.DAOFactory;
import by.epam.travel_agency.dao.factory.DAOFactoryProvider;
import by.epam.travel_agency.service.validation.UserValidator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.epam.travel_agency.service.util.EntityBuilderHelper.creatNewUserFromRequest;

public class UserServiceImpl implements UserService {

    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

    private final DAOFactory daoFactory = DAOFactoryProvider.getSqlDaoFactory();
    private UserDAO userDao = daoFactory.getUserDao();

    private static final String MESSAGE = "message";
    private static final String ADMIN = "admin";
    private static final String MANAGER = "manager";
    private static final String USER = "user";
    private static final String LOGIN = "login";

    @Override
    public List<User> receiverUserFindAll() throws ReceiverException {
        try {
            return userDao.getAllUsersForChangingLevelAccess();
        } catch (DAOUserException e) {
            throw new ReceiverException(e);
        }
    }

    @Override
    public User receiverUserFindById(int id) throws ReceiverException {
        User user = null;
        try {
            user = userDao.findEntityById(id);
        } catch (DAOUserException e) {
            throw new ReceiverException(e);
        }
        return user;
    }

    @Override
    public User receiverUserFindByLoginAndPassword(String login, String password) throws ReceiverException {
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
    public boolean receiverUserAdd(HttpServletRequest request) throws ReceiverException {
        logger.info("receiverUserAdd is start");
        boolean isSuccessfullyCreateNewUser = false;
        if (UserValidator.isOkParametersOfNewUserBeforeCreating(request)) {
            if (isThisLoginBusy(request)) {
                logger.info("This login already exist!");
                request.setAttribute(MESSAGE, MessageKey.REGISTER_LOGIN_ERROR);
            } else {
                User user = creatNewUserFromRequest(request);
                try {
                    user.setId_user(receiverCountUsersAtDB() + 1);
                    isSuccessfullyCreateNewUser = userDao.addNewUserToDB(user);
                } catch (DAOUserException e) {
                    logger.error(e);
                    request.setAttribute(MESSAGE, MessageKey.DATABASE_ERROR);
                    throw new ReceiverException(e);
                }
            }
        } else {
            request.setAttribute(MESSAGE, MessageKey.REGISTER_SUCCESS);
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


    private int receiverCountUsersAtDB() throws ReceiverException {
        try {
            return userDao.countAllRows();
        } catch (DAOUserException e) {
            throw new ReceiverException(e);
        }
    }

    private boolean isThisLoginBusy(HttpServletRequest request) throws ReceiverException {
        try {
            return userDao.findEntityByLogin(request.getParameter(LOGIN));
        } catch (DAOUserException e) {
            throw new ReceiverException(e);
        }
    }


}