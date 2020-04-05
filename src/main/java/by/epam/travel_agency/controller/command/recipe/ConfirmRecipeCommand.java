package main.java.by.epam.travel_agency.controller.command.recipe;

import javax.servlet.http.HttpServletRequest;

import main.java.by.epam.travel_agency.controller.command.Command;
import org.apache.logging.log4j.Level;

import by.epam.pharmacy.constant.MessageKey;
import by.epam.pharmacy.constant.RecipeMedicine;
import by.epam.pharmacy.entity.Recipe;
import by.epam.pharmacy.entity.User;
import main.java.by.epam.travel_agency.service.manager.ConfigurationManager;
import by.epam.pharmacy.receiver.ReceiverException;

public class ConfirmRecipeCommand implements Command {
	
	/**class for doctor to confirm a recipe*/

	@Override
	public String execute(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null || user.getAccessLevel() != 2) {
			request.setAttribute("message", MessageKey.CANCEL_ORDER_ERROR);
			return ConfigurationManager.getProperty("path.page.error");
		}
		int id = Integer.parseInt(request.getParameter("recipe_id"));
		try {
			Recipe recipe = RECIPE_RECEIVER.receiverRecipeFindById(id);
			if (recipe == null) {
				request.setAttribute("message", MessageKey.FIND_ORDER_ERROR);
				return ConfigurationManager.getProperty("path.page.error");
			}
			if (RecipeMedicine.NO_RECIPE.equals(recipe.getStatusRecipe())) {
				recipe.setStatusRecipe(RecipeMedicine.RECIPE);
				if (RECIPE_RECEIVER.receiverUpdateStatusRecipe(recipe)) {
					request.setAttribute("message", MessageKey.RECIPE_WROTE_SUCCESS);
					return ConfigurationManager.getProperty("path.page.success");
				}
			}
			request.setAttribute("message", MessageKey.DELIVER_ORDER_ERROR);
			return ConfigurationManager.getProperty("path.page.error");	
		} catch (ReceiverException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			request.setAttribute("message", MessageKey.DATABASE_ERROR);
			return ConfigurationManager.getProperty("path.page.error");
		}
	}
}