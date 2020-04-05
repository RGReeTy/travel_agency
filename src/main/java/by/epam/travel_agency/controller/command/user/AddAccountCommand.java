package main.java.by.epam.travel_agency.controller.command.user;

import javax.servlet.http.HttpServletRequest;

import main.java.by.epam.travel_agency.controller.command.Command;
import org.apache.logging.log4j.Level;

import by.epam.pharmacy.constant.MessageKey;
import by.epam.pharmacy.entity.User;
import main.java.by.epam.travel_agency.service.manager.ConfigurationManager;
import by.epam.pharmacy.receiver.ReceiverException;

public class AddAccountCommand implements Command {

	/** class for user so that he can replenish an account */

	@Override
	public String execute(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			request.setAttribute("message", MessageKey.USER_ERROR);
			return ConfigurationManager.getProperty("path.page.error");
		}
		int sum = Integer.parseInt(request.getParameter("account"));
		int account = user.getAccount();
		int total = sum + account;
		user.setAccount(total);
		try {
			if (USER_RECEIVER.receiverUserUpdate(user) != null) {
				request.setAttribute("message", MessageKey.ADD_TO_ACCOUNT_SUCCESS);
				return ConfigurationManager.getProperty("path.page.success");
			} else {
				request.setAttribute("message", MessageKey.EDIT_ACCOUNT_ERROR);
				return ConfigurationManager.getProperty("path.page.error");
			}
		} catch (ReceiverException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			request.setAttribute("message", MessageKey.DATABASE_ERROR);
			return ConfigurationManager.getProperty("path.page.error");
		}
	}
}