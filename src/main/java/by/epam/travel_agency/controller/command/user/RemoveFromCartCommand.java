package main.java.by.epam.travel_agency.controller.command.user;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import main.java.by.epam.travel_agency.controller.command.Command;
import by.epam.pharmacy.constant.MessageKey;
import by.epam.pharmacy.entity.Product;
import by.epam.pharmacy.entity.User;
import main.java.by.epam.travel_agency.service.manager.ConfigurationManager;

public class RemoveFromCartCommand implements Command {

	/** class to remove a product from the shopping cart */

	@Override
	public String execute(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			request.setAttribute("message", MessageKey.USER_ERROR);
			return ConfigurationManager.getProperty("path.page.error");
		}
		int id = Integer.parseInt(request.getParameter("product_id"));
		Iterator<Product> iterator = user.getPharmacyList().iterator();
		while (iterator.hasNext()) {
			if (iterator.next().getId() == id) {
				iterator.remove();
				break;
			}
		}
		request.getSession().setAttribute("user", user);
		request.setAttribute("message", MessageKey.REMOVE_FROM_CART_SUCCESS);
		return ConfigurationManager.getProperty("path.page.success");
	}
}