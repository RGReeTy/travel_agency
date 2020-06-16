package by.epam.travel_agency.service.receiver;

import by.epam.travel_agency.bean.User;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface UserService {

    public List<User> receiverUserFindAll() throws ReceiverException;

    public User receiverUserFindById(int id) throws ReceiverException;

    public User receiverUserFindByLoginAndPassword(String login, String password) throws ReceiverException;

    public Map<String, Integer> countAllUsersByLevelAccessMap() throws ReceiverException;

    public boolean updateUserStatusByID(int user_id, int status) throws ReceiverException;

    public boolean receiverUserAdd(HttpServletRequest request) throws ReceiverException;

    public BigDecimal countingTotalMoneySpentForUserID(int id_user) throws ReceiverException;

}