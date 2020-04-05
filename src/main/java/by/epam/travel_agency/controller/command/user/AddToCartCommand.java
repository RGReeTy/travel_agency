package main.java.by.epam.travel_agency.controller.command.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import main.java.by.epam.travel_agency.controller.command.Command;
import org.apache.logging.log4j.Level;

import by.epam.pharmacy.constant.MessageKey;
import by.epam.pharmacy.entity.User;
import main.java.by.epam.travel_agency.service.manager.ConfigurationManager;
import by.epam.pharmacy.receiver.ReceiverException;

public class AddToCartCommand implements Command {
	
	/**class for user to add product to cart*/
	
	private static final String OTHER = "Other";
	private static final String DEPRESSION = "Depression";

	@Override
	public String execute(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			request.setAttribute("message", MessageKey.USER_ERROR);
			return ConfigurationManager.getProperty("path.page.error");
		}
		int id = Integer.parseInt(request.getParameter("product_id"));
		try {
			Product product = PRODUCT_RECEIVER.receiverProductFindById(id);
			if (product == null) {
				request.setAttribute("message", MessageKey.FIND_PRODUCT_ERROR);
				return ConfigurationManager.getProperty("path.page.error");
			} 
			List<Recipe> recipes = RECIPE_RECEIVER.receiverFindRecipeByUserProduct(user.getId(),product.getId());		
			if (product.getDisease().equals(OTHER) || product.getDisease().equals(DEPRESSION)) {	
				for (Recipe o: recipes){
					if (o.getStatusRecipe().equals(RecipeMedicine.RECIPE)){	
						user.addProduct(product);
						request.getSession().setAttribute("user", user);
						request.setAttribute("message", MessageKey.ADD_TO_CART_SUCCESS);
						return ConfigurationManager.getProperty("path.page.success");
					} else {
						request.setAttribute("message", MessageKey.RECIPE_NECESSARY);
						return ConfigurationManager.getProperty("path.page.error");	
					}
				}
				request.setAttribute("message", MessageKey.RECIPE_NECESSARY);
				return ConfigurationManager.getProperty("path.page.error");	
			}
			user.addProduct(product);
			request.getSession().setAttribute("user", user);
			request.setAttribute("message", MessageKey.ADD_TO_CART_SUCCESS);
			return ConfigurationManager.getProperty("path.page.success");
		} catch (ReceiverException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			request.setAttribute("message", MessageKey.DATABASE_ERROR);
			return ConfigurationManager.getProperty("path.page.error");
		}
	}
}