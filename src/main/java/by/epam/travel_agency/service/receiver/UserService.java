package by.epam.travel_agency.service.receiver;

import by.epam.travel_agency.bean.User;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface UserService {

    List<User> receiverUserFindAll() throws ReceiverException;

    User receiverUserFindById(int id) throws ReceiverException;

    User receiverUserFindByLoginAndPassword(String login, String password) throws ReceiverException;

    Map<String, Integer> countAllUsersByLevelAccessMap() throws ReceiverException;

    boolean updateUserStatusByID(int user_id, int status) throws ReceiverException;

    boolean receiverUserAdd(HttpServletRequest request) throws ReceiverException;

    BigDecimal countingTotalMoneySpentForUserID(int id_user) throws ReceiverException;

    boolean updateUserInfo(User user, String firstname, String lastname, String email, String phone) throws ReceiverException;

}