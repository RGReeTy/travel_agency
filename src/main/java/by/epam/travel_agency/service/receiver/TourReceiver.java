package by.epam.travel_agency.service.receiver;

import by.epam.travel_agency.bean.Hotel;
import by.epam.travel_agency.bean.Request;
import by.epam.travel_agency.bean.Tour;
import by.epam.travel_agency.bean.User;
import by.epam.travel_agency.dao.TourDaoImpl;
import org.apache.log4j.Logger;

import java.util.Set;

public class TourReceiver {

    private static final Logger logger = Logger.getLogger(TourReceiver.class);

    private static final String IS_OK = "ok";

    private TourDaoImpl tourDao = TourDaoImpl.getInstance();

    private static TourReceiver instance = new TourReceiver();

    private TourReceiver() {
    }

    public static TourReceiver getInstance() {
        return instance;
    }

    public Set<Tour> getAllTours() {
        return instance.tourDao.showAllTours();
    }

    public Set<Tour> getConcreteTypeTours(String typeOfTour) {
        return instance.tourDao.showConcreteTypeTours(typeOfTour);
    }

    public Set<Hotel> getAllHotels() {
        return instance.tourDao.showAllHotels();
    }

    public Set<Request> getAllRequestsForUser(User user) {
        return instance.tourDao.getAllRequestsByUserId(id);
    }

//    public int receiverCountUsersAtDB() throws ReceiverException {
//        return instance.userDao.countAllRows();
//    }

//	public List<User> receiverUserFindAll() throws ReceiverException {
//		ArrayList<User> users = null;
//		try {
//			users = (ArrayList<User>) instance.userDao.findAll();
//		} catch (DAOException e) {
//			new ReceiverException("Exception in receiverUserFindAll method", e);
//		}
//		return users;
//	}

//    public User receiverUserFindById(int id) {
//        User user = null;
//        user = instance.userDao.findEntityById(id);
//        return user;
//    }
//
//    public User receiverUserFindByLoginAndPassword(String login, String password) throws ReceiverException {
//
//        logger.info("receiverUserFindByLoginAndPassword message");
//
//        User user = null;
//        user = instance.userDao.findEntityByLoginAndPassword(login, password);
//        return user;
//    }

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

    //TODO if next method ll work
//    public boolean receiverUserAdd(User user) throws ReceiverException {
//        return instance.userDao.add(user);
//    }

//    public boolean receiverUserAdd(HttpServletRequest request) {
//        logger.info("receiverUserAdd is start");
//        boolean isSuccessfullyCreateNewUser = false;
//        if (UserValidator.isOkParametersOfNewUserBeforeCreating(request)) {
//            if (isThisLoginBusy(request)) {
//                logger.info("This login already exist!");
//                request.setAttribute("message", MessageKey.REGISTER_LOGIN_ERROR);
//            } else {
//                User user = creatNewUserFromRequest(request);
//
//                logger.debug(user.toString());
//
//                isSuccessfullyCreateNewUser = instance.userDao.addNewUserToDB(user);
//            }
//
//        } else {
//            request.setAttribute("message", MessageKey.REGISTER_SUCCESS);
//        }
//        logger.debug(isSuccessfullyCreateNewUser);
//
//        return isSuccessfullyCreateNewUser;
//    }


//    private User creatNewUserFromRequest(HttpServletRequest request) {
//
//        final String PARAM_NAME_LOGIN = "login";
//        final String PARAM_NAME_PASSWORD = "password";
//        final String PARAM_NAME_EMAIL = "email";
//        final String PARAM_NAME_FIRSTNAME = "firstname";
//        final String PARAM_NAME_LASTNAME = "lastname";
//        final String PARAM_NAME_PHONE = "phone";
//
//        String login = request.getParameter(PARAM_NAME_LOGIN);
//        String password = request.getParameter(PARAM_NAME_PASSWORD);
//        String email = request.getParameter(PARAM_NAME_EMAIL);
//        String firstname = request.getParameter(PARAM_NAME_FIRSTNAME);
//        String lastname = request.getParameter(PARAM_NAME_LASTNAME);
//        String phone = request.getParameter(PARAM_NAME_PHONE);
//
//        User user = new User();
//        user.setLogin(login);
//        user.setPassword(password);
//        user.setEmail(email);
//        user.setFirstname(firstname);
//        user.setLastname(lastname);
//        user.setPhone(phone);
//        user.setId_discount(1);
//        user.setLevel_access(2);
//
//        logger.info(user.toString());
//
//        String validationMessage = UserValidator.validateUserToMatchThePattern(user);
//
//        logger.info("after validator: " + validationMessage);
//
//        if (!validationMessage.equals(IS_OK)) {
//            request.setAttribute("message", validationMessage);
//            logger.info(validationMessage);
//            return null;
//        }
//
//        try {
//            user.setId_user(receiverCountUsersAtDB() + 1);
//        } catch (ReceiverException e) {
//            logger.error(e);
//            request.setAttribute("message", MessageKey.DATABASE_ERROR);
//            return null;
//        }
//
//        return user;
//    }
//
//    private boolean isThisLoginBusy(HttpServletRequest request) {
//        return instance.userDao.findEntityByLogin(request.getParameter("login"));
//    }


}