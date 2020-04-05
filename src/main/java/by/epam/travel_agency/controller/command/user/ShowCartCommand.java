package main.java.by.epam.travel_agency.controller.command.user;

import javax.servlet.http.HttpServletRequest;

import main.java.by.epam.travel_agency.controller.command.Command;
import by.epam.pharmacy.constant.MessageKey;
import by.epam.pharmacy.entity.User;
import main.java.by.epam.travel_agency.service.manager.ConfigurationManager;

public class ShowCartCommand implements Command {

	/** class for user to show his cart */

	@Override
	public String execute(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			request.setAttribute("message", MessageKey.USER_ERROR);
			return ConfigurationManager.getProperty("path.page.error");
		}
		if (user.getPharmacyList().isEmpty()) {
			request.setAttribute("message", MessageKey.SHOW_CART_ERROR);
			return ConfigurationManager.getProperty("path.page.error");
		}
		int fullPrice = 0;
		for (Product product : user.getPharmacyList()) {
			fullPrice += product.getPrice();
		}
		request.setAttribute("cart", user.getPharmacyList());
		request.setAttribute("full_price", fullPrice);
		return ConfigurationManager.getProperty("path.page.cart");
	}
}