package by.epam.travel_agency.controller.command.user;

import javax.servlet.http.HttpServletRequest;

import by.epam.travel_agency.bean.User;
import by.epam.travel_agency.constant.MessageKey;
import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.receiver.ReceiverException;
import org.apache.logging.log4j.Level;

import by.epam.travel_agency.service.manager.ConfigurationManager;

public class LogInCommand implements Command {


	private static final String PARAM_NAME_LOGIN = "login";
	private static final String PARAM_NAME_PASSWORD = "password";

	@Override
	public String execute(HttpServletRequest request) {

		//DELETE
		System.out.println("LOGIN execute message");

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
				// Ольга сказала, что отслеживаем юзера по ИД, а не по логину
				//LOGGER.info("User with id {} was authorized", user.getId());
				return ConfigurationManager.getProperty("path.page.main");
			}
		} catch (ReceiverException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			request.setAttribute("message", MessageKey.DATABASE_ERROR);
			return ConfigurationManager.getProperty("path.page.error");
		}
	}
}