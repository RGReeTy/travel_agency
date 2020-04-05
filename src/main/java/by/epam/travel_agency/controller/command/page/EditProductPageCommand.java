package main.java.by.epam.travel_agency.controller.command.page;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import main.java.by.epam.travel_agency.controller.command.Command;
import org.apache.logging.log4j.Level;

import by.epam.pharmacy.constant.MessageKey;
import by.epam.pharmacy.entity.Product;
import by.epam.pharmacy.entity.User;
import main.java.by.epam.travel_agency.service.manager.ConfigurationManager;
import by.epam.pharmacy.receiver.ReceiverException;

public class EditProductPageCommand implements Command {

	/**
	 * class for the admin so that during the editing of the product a list of
	 * pictures, products and illnesses appeared
	 */

	@Override
	public String execute(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null || user.getAccessLevel() != 1) {
			request.setAttribute("message", MessageKey.CANCEL_ORDER_ERROR);
			return ConfigurationManager.getProperty("path.page.error");
		}
		int productId = Integer.parseInt(request.getParameter("product_id"));
		try {
			Product product = PRODUCT_RECEIVER.receiverProductFindById(productId);
			List<String> diseases = PRODUCT_RECEIVER.receiverFindAllProductTypes();
			List<String> picturePath = PRODUCT_RECEIVER.receiverFindAllProductPicturePath();
			request.setAttribute("diseases", diseases);
			request.setAttribute("picturePath", picturePath);
			request.setAttribute("product", product);
			return ConfigurationManager.getProperty("path.page.editproduct");
		} catch (ReceiverException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			request.setAttribute("message", MessageKey.DATABASE_ERROR);
			return ConfigurationManager.getProperty("path.page.error");
		}
	}
}