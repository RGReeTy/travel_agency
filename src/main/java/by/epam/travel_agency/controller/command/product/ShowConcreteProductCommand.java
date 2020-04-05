package main.java.by.epam.travel_agency.controller.command.product;

import javax.servlet.http.HttpServletRequest;

import main.java.by.epam.travel_agency.controller.command.Command;
import org.apache.logging.log4j.Level;

import by.epam.pharmacy.constant.MessageKey;
import by.epam.pharmacy.entity.Product;
import main.java.by.epam.travel_agency.service.manager.ConfigurationManager;
import by.epam.pharmacy.receiver.ReceiverException;

public class ShowConcreteProductCommand implements Command {
	
	/** class for user to show all information about a product */
	
	@Override
	public String execute(HttpServletRequest request) {
		int productId = Integer.parseInt(request.getParameter("product_id"));
		try {
			Product product = PRODUCT_RECEIVER.receiverProductFindById(productId);
			request.setAttribute("product", product);
			return ConfigurationManager.getProperty("path.page.product");
		} catch (ReceiverException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			request.setAttribute("message", MessageKey.DATABASE_ERROR);
			return ConfigurationManager.getProperty("path.page.error");
		}
	}
}