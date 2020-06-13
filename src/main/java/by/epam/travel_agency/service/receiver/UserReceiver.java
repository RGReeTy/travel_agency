package by.epam.travel_agency.service.receiver;

import by.epam.travel_agency.bean.User;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

public interface UserReceiver {

    public List<User> receiverUserFindAll() throws ReceiverException;

    public User receiverUserFindById(int id) throws ReceiverException;

    public User receiverUserFindByLoginAndPassword(String login, String password) throws ReceiverException;

    public HashMap<String, Integer> countAllUsersByLevelAccessMap() throws ReceiverException;

    public boolean updateUserStatusByID(int user_id, int status) throws ReceiverException;

    public boolean receiverUserAdd(HttpServletRequest request) throws ReceiverException;

    public BigDecimal countingTotalMoneySpentForUserID(int id_user) throws ReceiverException;

}