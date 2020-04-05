package main.java.by.epam.travel_agency.controller.command;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.pharmacy.receiver.OrderReceiver;
import by.epam.pharmacy.receiver.ProductReceiver;
import by.epam.pharmacy.receiver.RecipeReceiver;
import by.epam.pharmacy.receiver.UserReceiver;

public interface Command {

	/**
	 * The interface for processing of actions. Each action will be processed by
	 * the class realizing this interface. 
	 */

	final static Logger LOGGER = LogManager.getLogger();

	UserReceiver USER_RECEIVER = UserReceiver.getInstance();
	ProductReceiver PRODUCT_RECEIVER = ProductReceiver.getInstance();
	RecipeReceiver RECIPE_RECEIVER = RecipeReceiver.getInstance();
	OrderReceiver ORDER_RECEIVER = OrderReceiver.getInstance();

	String execute(HttpServletRequest request);
}
