package main.java.by.epam.travel_agency.controller.command.user;

import javax.servlet.http.HttpServletRequest;

import main.java.by.epam.travel_agency.bean.User;
import main.java.by.epam.travel_agency.constant.MessageKey;
import main.java.by.epam.travel_agency.controller.command.Command;
import main.java.by.epam.travel_agency.service.manager.ConfigurationManager;

public class ShowAccountCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			request.setAttribute("message", MessageKey.LOG_IN_ERROR);
			return ConfigurationManager.getProperty("path.page.error");
		}
		return ConfigurationManager.getProperty("path.page.account");
	}
}