package main.java.by.epam.travel_agency.controller.command.order;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import main.java.by.epam.travel_agency.controller.command.Command;
import org.apache.logging.log4j.Level;

import by.epam.pharmacy.constant.MessageKey;
import by.epam.pharmacy.constant.OrderStatus;
import by.epam.pharmacy.entity.Order;
import by.epam.pharmacy.entity.Product;
import by.epam.pharmacy.entity.User;
import main.java.by.epam.travel_agency.service.manager.ConfigurationManager;
import by.epam.pharmacy.receiver.ReceiverException;

public class MakeOrderCommand implements Command {
	
	/** class for user to make an order */

	@Override
	public String execute(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			request.setAttribute("message", MessageKey.USER_ERROR);
			return ConfigurationManager.getProperty("path.page.error");
		}
		List<Product> products = user.getPharmacyList();
		if (products == null) {
			request.setAttribute("message", MessageKey.MAKE_ORDER_ERROR);
			return ConfigurationManager.getProperty("path.page.error");
		}
		Order order = new Order();
		order.setProducts(products);
		order.setStatus(OrderStatus.ACTIVE);
		order.setUser(user);
		try {
			List<Order> orders = ORDER_RECEIVER.receiverOrderFindAll();
			orders.size();
			int x = orders.size() + 1;
			order.setId(x);
		} catch (ReceiverException e) {
			request.setAttribute("message", MessageKey.DATABASE_ERROR);
			return ConfigurationManager.getProperty("path.page.error");
		}
		try {
			if (ORDER_RECEIVER.receiverOrderAdd(order)) {
				user.removeAllProducts();
				int full_price = Integer.parseInt(request.getParameter("full_price"));
				int account = user.getAccount() - full_price;
				user.setAccount(account);
				if (USER_RECEIVER.receiverUserUpdate(user) != null) {
					request.setAttribute("message", MessageKey.MAKE_ORDER_SUCCESS);
					return ConfigurationManager.getProperty("path.page.success");
				}
				request.setAttribute("message", MessageKey.MAKE_ORDER_SUCCESS);
				return ConfigurationManager.getProperty("path.page.success");
			} else {
				request.setAttribute("message", MessageKey.MAKE_ORDER_ERROR);
				return ConfigurationManager.getProperty("path.page.error");
			}
		} catch (ReceiverException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			request.setAttribute("message", MessageKey.DATABASE_ERROR);
			return ConfigurationManager.getProperty("path.page.error");
		}
	}
}