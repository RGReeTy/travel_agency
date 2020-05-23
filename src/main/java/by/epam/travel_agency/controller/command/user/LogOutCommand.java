package by.epam.travel_agency.controller.command.user;

import javax.servlet.http.HttpServletRequest;

import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.service.manager.ConfigurationManager;

public class LogOutCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		String page = ConfigurationManager.getProperty("path.page.index");
		//TODO deleting user's login/ID, not a session!
		// destruction of a session
		request.getSession().invalidate();
		return page;
	}
}
