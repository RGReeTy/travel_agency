package main.java.by.epam.travel_agency.controller.command;

import main.java.by.epam.travel_agency.constant.MessageKey;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {

	private static ActionFactory instance = new ActionFactory();

	private ActionFactory() {
	}

	public static ActionFactory getInstance() {
		return instance;
	}

	public Command defineCommand(HttpServletRequest request) {
		Command current = null;
		String command = null;
		command = request.getParameter("action");
		if (command == null || command.isEmpty()) {
			return new EmptyCommand();
		}

		try {
			CommandEnum currentEnum = CommandEnum.valueOf(command.toUpperCase());
			current = currentEnum.getCurrentCommand();
		} catch (IllegalArgumentException e) {
			request.setAttribute("wrongAction", command + MessageKey.WRONG_ACTION);
		}
		return current;
	}
}