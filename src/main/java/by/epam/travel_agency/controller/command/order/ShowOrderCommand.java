package main.java.by.epam.travel_agency.controller.command.order;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import main.java.by.epam.travel_agency.controller.command.Command;
import org.apache.logging.log4j.Level;

import by.epam.pharmacy.constant.MessageKey;
import by.epam.pharmacy.entity.Order;
import by.epam.pharmacy.entity.User;
import main.java.by.epam.travel_agency.service.manager.ConfigurationManager;
import by.epam.pharmacy.receiver.ReceiverException;

public class ShowOrderCommand implements Command {
	
	/** class for user to show his orders*/

	@Override
	public String execute(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		if (user==null){
			request.setAttribute("message", MessageKey.USER_ERROR);
			return ConfigurationManager.getProperty("path.page.error");
		}
		List<Order> orders = null;
		try {
			orders = ORDER_RECEIVER.receiverFindOrdersByUserId(user.getId());
			if (orders.isEmpty()) {
				request.setAttribute("message", MessageKey.SHOW_ORDERS_ERROR);
				return ConfigurationManager.getProperty("path.page.error");
			} else {
				request.setAttribute("orders", orders);
				return ConfigurationManager.getProperty("path.page.orders");
			}
		} catch (ReceiverException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			request.setAttribute("message", MessageKey.DATABASE_ERROR);
			return ConfigurationManager.getProperty("path.page.error");
		}
	}
}