package main.java.by.epam.travel_agency.controller.command;

import javax.servlet.http.HttpServletRequest;

import main.java.by.epam.travel_agency.receiver.UserReceiver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface Command {

	final static Logger LOGGER = LogManager.getLogger();

	UserReceiver USER_RECEIVER = UserReceiver.getInstance();
	//OrderReceiver ORDER_RECEIVER = OrderReceiver.getInstance();

	String execute(HttpServletRequest request);
}
