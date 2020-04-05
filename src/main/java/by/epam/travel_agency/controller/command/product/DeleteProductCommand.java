package main.java.by.epam.travel_agency.controller.command.product;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Level;

import main.java.by.epam.travel_agency.controller.command.Command;
import by.epam.pharmacy.constant.MessageKey;
import by.epam.pharmacy.entity.User;
import main.java.by.epam.travel_agency.service.manager.ConfigurationManager;
import by.epam.pharmacy.receiver.ReceiverException;

public class DeleteProductCommand implements Command {

	/** class for the admin to delete a product */

	@Override
	public String execute(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null || user.getAccessLevel() != 1) {
			request.setAttribute("message", MessageKey.USER_ERROR);
			return ConfigurationManager.getProperty("path.page.error");
		}
		int productId = Integer.parseInt(request.getParameter("product_id"));
		try {
			if (PRODUCT_RECEIVER.receiverProductDelete(productId)) {
				request.setAttribute("message", MessageKey.DELETE_PRODUCT_SUCCESS);
				return ConfigurationManager.getProperty("path.page.success");
			} else {
				request.setAttribute("message", MessageKey.DELETE_PRODUCT_ERROR);
				return ConfigurationManager.getProperty("path.page.error");
			}
		} catch (ReceiverException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			request.setAttribute("message", MessageKey.DATABASE_ERROR);
			return ConfigurationManager.getProperty("path.page.error");
		}
	}
}