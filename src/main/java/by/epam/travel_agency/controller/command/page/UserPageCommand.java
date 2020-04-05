package main.java.by.epam.travel_agency.controller.command.page;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import main.java.by.epam.travel_agency.controller.command.Command;
import org.apache.logging.log4j.Level;

import by.epam.pharmacy.constant.MessageKey;
import by.epam.pharmacy.entity.User;
import main.java.by.epam.travel_agency.service.manager.ConfigurationManager;
import by.epam.pharmacy.receiver.ReceiverException;

public class UserPageCommand implements Command {

	/** class for showing all users */

	@Override
	public String execute(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null || user.getAccessLevel() != 1) {
			request.setAttribute("message", MessageKey.CANCEL_ORDER_ERROR);
			return ConfigurationManager.getProperty("path.page.error");
		}
		try {
			List<User> users = USER_RECEIVER.receiverUserFindAll();
			request.setAttribute("users", users);
			return ConfigurationManager.getProperty("path.page.users");
		} catch (ReceiverException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			request.setAttribute("message", MessageKey.DATABASE_ERROR);
			return ConfigurationManager.getProperty("path.page.error");
		}
	}
}