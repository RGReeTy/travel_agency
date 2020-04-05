package main.java.by.epam.travel_agency.controller.command.order;

import javax.servlet.http.HttpServletRequest;

import main.java.by.epam.travel_agency.controller.command.Command;
import org.apache.logging.log4j.Level;

import by.epam.pharmacy.constant.MessageKey;
import by.epam.pharmacy.entity.Order;
import by.epam.pharmacy.entity.Product;
import main.java.by.epam.travel_agency.service.manager.ConfigurationManager;
import by.epam.pharmacy.receiver.ReceiverException;

public class ShowConcreteOrderCommand implements Command {

	/** class for the user, where he can view his orders by number */

	@Override
	public String execute(HttpServletRequest request) {
		int orderId = Integer.parseInt(request.getParameter("order_id"));
		try {
			Order order = ORDER_RECEIVER.receiverOrderFindById(orderId);
			if (order == null) {
				request.setAttribute("message", MessageKey.FIND_ORDER_ERROR);
				return ConfigurationManager.getProperty("path.page.error");
			}
			int price = 0;
			for (Product product : order.getProducts()) {
				price += product.getPrice();
			}
			request.setAttribute("products", order.getProducts());
			request.setAttribute("full_price", price);
			request.setAttribute("order_id", order.getId());
			return ConfigurationManager.getProperty("path.page.order");
		} catch (ReceiverException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			request.setAttribute("message", MessageKey.DATABASE_ERROR);
			return ConfigurationManager.getProperty("path.page.error");
		}
	}
}