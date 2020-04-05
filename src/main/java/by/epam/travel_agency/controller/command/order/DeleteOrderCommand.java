package main.java.by.epam.travel_agency.controller.command.order;

import javax.servlet.http.HttpServletRequest;

import main.java.by.epam.travel_agency.controller.command.Command;
import org.apache.logging.log4j.Level;

import by.epam.pharmacy.constant.MessageKey;
import by.epam.pharmacy.entity.Order;
import by.epam.pharmacy.entity.User;
import main.java.by.epam.travel_agency.service.manager.ConfigurationManager;
import by.epam.pharmacy.receiver.ReceiverException;

public class DeleteOrderCommand implements Command {

	/** class for the admin to delete an order */

	@Override
	public String execute(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null || user.getAccessLevel() != 1) {
			request.setAttribute("message", MessageKey.USER_ERROR);
			return ConfigurationManager.getProperty("path.page.error");
		}
		int orderId = Integer.parseInt(request.getParameter("order_id"));
		try {
			Order order = ORDER_RECEIVER.receiverOrderFindById(orderId);
			if (order == null) {
				request.setAttribute("message", MessageKey.FIND_ORDER_ERROR);
				return ConfigurationManager.getProperty("path.page.error");
			}
			if (ORDER_RECEIVER.receiverOrderDelete(order)) {
				request.setAttribute("message", MessageKey.DELETE_ORDER_SUCCESS);
				return ConfigurationManager.getProperty("path.page.success");
			} else {
				request.setAttribute("message", MessageKey.DELETE_ORDER_ERROR);
				return ConfigurationManager.getProperty("path.page.error");
			}
		} catch (ReceiverException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			request.setAttribute("message", MessageKey.DATABASE_ERROR);
			return ConfigurationManager.getProperty("path.page.error");
		}
	}
}