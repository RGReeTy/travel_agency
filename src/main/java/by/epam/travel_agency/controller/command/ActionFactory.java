package main.java.by.epam.travel_agency.controller.command;

import javax.servlet.http.HttpServletRequest;

import by.epam.pharmacy.constant.MessageKey;

public class ActionFactory {

	/**
	 * The command name is taken from the query and the object of the required
	 * command is returned. If the command isn't given it will return
	 * EmptyCommand.
	 *
	 * @param request
	 *            HttpServletRequest request
	 * @return the object of needed command.
	 */

	private static ActionFactory instance = new ActionFactory();

	private ActionFactory() {
	}

	public static ActionFactory getInstance() {
		return instance;
	}

	public Command defineCommand(HttpServletRequest request) {
		Command current = null;
		String command = null;
		// taking the name of command from request
		command = request.getParameter("action");
		if (command == null || command.isEmpty()) {
			return new EmptyCommand();
		}
		// get the object which is corresponding to the command
		try {
			CommandEnum currentEnum = CommandEnum.valueOf(command.toUpperCase());
			current = currentEnum.getCurrentCommand();
		} catch (IllegalArgumentException e) {
			request.setAttribute("wrongAction", command + MessageKey.WRONG_ACTION);
		}
		return current;
	}
}