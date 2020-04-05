package main.java.by.epam.travel_agency.controller.command.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import main.java.by.epam.travel_agency.controller.command.Command;
import org.apache.logging.log4j.Level;

import by.epam.pharmacy.constant.MessageKey;
import by.epam.pharmacy.entity.User;
import main.java.by.epam.travel_agency.service.manager.ConfigurationManager;
import by.epam.pharmacy.receiver.ReceiverException;
import main.java.by.epam.travel_agency.service.validation.ProductValidator;

public class AddProductCommand implements Command {

	/** class for the admin to add product */

	@Override
	public String execute(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null || user.getAccessLevel() != 1) {
			request.setAttribute("message", MessageKey.USER_ERROR);
			return ConfigurationManager.getProperty("path.page.error");
		}
		String name = request.getParameter("name");
		String price = request.getParameter("price");
		String description = request.getParameter("description");
		String picturePath = request.getParameter("picture");
		String producer = request.getParameter("producer");
		String dosage = request.getParameter("dosage");
		String disease = request.getParameter("disease");
		try {
			if (name.length() * price.length() * description.length() * picturePath.length() * producer.length()
					* dosage.length() * disease.length() == 0) {
				List<String> diseases = PRODUCT_RECEIVER.receiverFindAllProductTypes();
				List<String> pictures = PRODUCT_RECEIVER.receiverFindAllProductPicturePath();
				request.setAttribute("diseases", diseases);
				request.setAttribute("picturePath", pictures);
				request.setAttribute("message", MessageKey.ADD_PRODUCT_BLANK_FIELDS);
				return ConfigurationManager.getProperty("path.page.addproduct");
			}
			Product product = new Product();
			try {
				List<Product> products = PRODUCT_RECEIVER.receiverProductFindAll();
				products.size();
				int x = products.size() + 1;
				product.setId(x);
			} catch (ReceiverException e) {
				LOGGER.log(Level.ERROR, e.getMessage());
				request.setAttribute("message", MessageKey.DATABASE_ERROR);
				return ConfigurationManager.getProperty("path.page.error");
			}
			product.setName(name);
			product.setPrice(Integer.parseInt(price));
			product.setDescription(description);
			product.setPicturePath(picturePath);
			product.setProducer(producer);
			product.setDosage(Integer.parseInt(dosage));
			product.setDisease(disease);
			String validationMessage = ProductValidator.validateProduct(product);
			if (validationMessage != null) {
				request.setAttribute("message", validationMessage);
				return ConfigurationManager.getProperty("path.page.addproduct");
			}
			if (PRODUCT_RECEIVER.receiverProductAdd(product)) {
				request.setAttribute("message", MessageKey.ADD_PRODUCT_SUCCESS);
				return ConfigurationManager.getProperty("path.page.success");
			} else {
				request.setAttribute("message", MessageKey.ADD_PRODUCT_ERROR);
				return ConfigurationManager.getProperty("path.page.error");
			}
		} catch (ReceiverException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			request.setAttribute("message", MessageKey.DATABASE_ERROR);
			return ConfigurationManager.getProperty("path.page.error");
		}
	}
}