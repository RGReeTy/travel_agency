package main.java.by.epam.travel_agency.controller.command.user;

import javax.servlet.http.HttpServletRequest;

import main.java.by.epam.travel_agency.controller.command.Command;
import org.apache.logging.log4j.Level;

import by.epam.pharmacy.constant.MessageKey;
import by.epam.pharmacy.entity.User;
import main.java.by.epam.travel_agency.service.manager.ConfigurationManager;
import by.epam.pharmacy.receiver.ReceiverException;

public class LogInCommand implements Command {

	/** class where user can login */

	private static final String PARAM_NAME_LOGIN = "login";
	private static final String PARAM_NAME_PASSWORD = "password";

	@Override
	public String execute(HttpServletRequest request) {
		String login = request.getParameter(PARAM_NAME_LOGIN);
		String password = request.getParameter(PARAM_NAME_PASSWORD);
		try {
			User user = USER_RECEIVER.receiverUserFindByLoginAndPassword(login, password);
			if (user == null) {
				LOGGER.log(Level.INFO, "Unknown user tried entering");
				request.setAttribute("message", MessageKey.LOG_IN_ERROR);
				return ConfigurationManager.getProperty("path.page.error");
			} else {
				request.getSession().setAttribute("user", user);
				LOGGER.info("User with id {} was authorized", user.getId());
				return ConfigurationManager.getProperty("path.page.main");
			}
		} catch (ReceiverException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			request.setAttribute("message", MessageKey.DATABASE_ERROR);
			return ConfigurationManager.getProperty("path.page.error");
		}
	}
}