package by.epam.travel_agency.controller.command.page;

import javax.servlet.http.HttpServletRequest;

import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.service.manager.ConfigurationManager;

public class GoToPageCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		String page = request.getParameter("page");
		return ConfigurationManager.getProperty(page);
	}
}
