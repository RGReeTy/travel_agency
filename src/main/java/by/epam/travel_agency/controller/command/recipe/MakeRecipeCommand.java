package main.java.by.epam.travel_agency.controller.command.recipe;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import main.java.by.epam.travel_agency.controller.command.Command;
import org.apache.logging.log4j.Level;

import by.epam.pharmacy.constant.MessageKey;
import by.epam.pharmacy.constant.RecipeMedicine;
import by.epam.pharmacy.entity.Product;
import by.epam.pharmacy.entity.Recipe;
import by.epam.pharmacy.entity.User;
import main.java.by.epam.travel_agency.service.manager.ConfigurationManager;
import by.epam.pharmacy.receiver.ReceiverException;

public class MakeRecipeCommand implements Command {
	
	/**class for user to make a recipe and send it to doctor*/

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
			} else {
				Recipe recipe = new Recipe();
				recipe.setProduct(product);
				recipe.setUser(user);
				recipe.setStatusRecipe(RecipeMedicine.NO_RECIPE);
				try {
					List<Recipe> recipes = RECIPE_RECEIVER.receiverRecipeFindAll();
					recipes.size();
					int x = recipes.size() + 1;
					recipe.setId(x);
				} catch (ReceiverException e) {
					LOGGER.log(Level.ERROR, e.getMessage());
					request.setAttribute("message", MessageKey.DATABASE_ERROR);
					return ConfigurationManager.getProperty("path.page.error");
				}
				try {	
					if (RECIPE_RECEIVER.receiverRecipeAdd(recipe)) {
						request.setAttribute("message", MessageKey.ADD_TO_LIST_RECIPE_SUCCESS);
						return ConfigurationManager.getProperty("path.page.success");
					} else {
						request.setAttribute("message", MessageKey.MAKE_RECIPE_ERROR);
						return ConfigurationManager.getProperty("path.page.error");
					}
				} catch (ReceiverException e) {
					LOGGER.log(Level.ERROR, e.getMessage());
					request.setAttribute("message", MessageKey.DATABASE_ERROR);
					return ConfigurationManager.getProperty("path.page.error");
				}
			}
		} catch (ReceiverException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			request.setAttribute("message", MessageKey.DATABASE_ERROR);
			return ConfigurationManager.getProperty("path.page.error");
		}
	}
}