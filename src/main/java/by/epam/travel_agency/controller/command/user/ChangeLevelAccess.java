package by.epam.travel_agency.controller.command.user;

import by.epam.travel_agency.bean.User;
import by.epam.travel_agency.constant.MessageKey;
import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.service.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ChangeLevelAccess implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        List<User> userList = USER_RECEIVER.receiverUserFindAll();
        if (userList.isEmpty()) {
            request.setAttribute("message", MessageKey.USERS_LIST_IS_EMPTY);
            return ConfigurationManager.getProperty("path.page.error");
        } else {
            request.setAttribute("userList", userList);
        }
        return ConfigurationManager.getProperty("path.page.admin_control");

    }
}
