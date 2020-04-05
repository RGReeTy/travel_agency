package main.java.by.epam.travel_agency.controller.command.page;

import javax.servlet.http.HttpServletRequest;

import main.java.by.epam.travel_agency.controller.command.Command;
import main.java.by.epam.travel_agency.service.manager.ConfigurationManager;

public class GoToPageCommand implements Command {

	/** class for referencing elements */

	@Override
	public String execute(HttpServletRequest request) {
		String page = request.getParameter("page");
		return ConfigurationManager.getProperty(page);
	}
}
