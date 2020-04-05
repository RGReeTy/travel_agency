package main.java.by.epam.travel_agency.controller.command.order;

import javax.servlet.http.HttpServletRequest;

import main.java.by.epam.travel_agency.controller.command.Command;
import org.apache.logging.log4j.Level;

import by.epam.pharmacy.constant.MessageKey;
import by.epam.pharmacy.constant.OrderStatus;
import by.epam.pharmacy.entity.Order;
import main.java.by.epam.travel_agency.service.manager.ConfigurationManager;
import by.epam.pharmacy.receiver.ReceiverException;

public class CancelOrderCommand implements Command {

	/** class for user to cancel an order */

	@Override
	public String execute(HttpServletRequest request) {
		int orderId = Integer.parseInt(request.getParameter("order_id"));
		try {
			Order order = ORDER_RECEIVER.receiverOrderFindById(orderId);
			if (order == null) {
				request.setAttribute("message", MessageKey.FIND_ORDER_ERROR);
				return ConfigurationManager.getProperty("path.page.error");
			}
			if (OrderStatus.ACTIVE.equals(order.getStatus())) {
				order.setStatus(OrderStatus.CANCELED);
				if (ORDER_RECEIVER.receiverUpdateStatus(order)) {
					request.setAttribute("message", MessageKey.CANCEL_ORDER_SUCCESS);
					return ConfigurationManager.getProperty("path.page.success");
				}
			}
			request.setAttribute("message", MessageKey.CANCEL_ORDER_ERROR);
			return ConfigurationManager.getProperty("path.page.error");
		} catch (ReceiverException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			request.setAttribute("message", MessageKey.DATABASE_ERROR);
			return ConfigurationManager.getProperty("path.page.error");
		}
	}
}