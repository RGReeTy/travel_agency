package by.epam.travel_agency.controller.command.locale;

import javax.servlet.http.HttpServletRequest;

import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.service.manager.ConfigurationManager;

public class ChangeLocaleCommand implements Command {
	
	@Override
	public String execute(HttpServletRequest request) {
		String language = request.getParameter("locale");
		request.getSession().setAttribute("locale", language);

		//TODO подумать как менять локаль на странице, на которой непосредственно находится пользователь
		// (не пересылая на главную)

		return ConfigurationManager.getProperty("path.page.main");
	}
}
