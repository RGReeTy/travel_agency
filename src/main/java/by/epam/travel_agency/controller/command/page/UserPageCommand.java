package main.java.by.epam.travel_agency.controller.command.page;

import main.java.by.epam.travel_agency.controller.command.Command;
import main.java.by.epam.travel_agency.service.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class UserPageCommand implements Command {
    //show all users for admin page

    @Override
    public String execute(HttpServletRequest request) {
//		User user = (User) request.getSession().getAttribute("user");
//		if (user == null || user.getAccessLevel() != 1) {
//			request.setAttribute("message", MessageKey.CANCEL_ORDER_ERROR);
//			return ConfigurationManager.getProperty("path.page.error");
//		}
//		try {
//			List<User> users = USER_RECEIVER.receiverUserFindAll();
//			request.setAttribute("users", users);
//			return ConfigurationManager.getProperty("path.page.users");
//		} catch (ReceiverException e) {
//			LOGGER.log(Level.ERROR, e.getMessage());
//			request.setAttribute("message", MessageKey.DATABASE_ERROR);
        return ConfigurationManager.getProperty("path.page.error");
        //}
    }
}