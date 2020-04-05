package main.java.by.epam.travel_agency.controller.command.locale;

import javax.servlet.http.HttpServletRequest;

import main.java.by.epam.travel_agency.controller.command.Command;
import main.java.by.epam.travel_agency.service.manager.ConfigurationManager;

public class ChangeLocaleCommand implements Command {
	
	/** class for changing locale */
	
	@Override
	public String execute(HttpServletRequest request) {
		String language = request.getParameter("locale");
		request.getSession().setAttribute("locale", language);
		return ConfigurationManager.getProperty("path.page.main");
	}
}
