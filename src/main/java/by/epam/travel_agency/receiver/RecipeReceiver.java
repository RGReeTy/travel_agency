package by.epam.pharmacy.receiver;

import java.util.ArrayList;
import java.util.List;

import by.epam.pharmacy.dao.RecipeDao;
import by.epam.pharmacy.entity.Recipe;
import main.java.by.epam.travel_agency.dao.DAOException;

public class RecipeReceiver {

	private RecipeDao recipeDao = RecipeDao.getInstance();

	private static RecipeReceiver instance = new RecipeReceiver();

	private RecipeReceiver() {
	}

	public static RecipeReceiver getInstance() {
		return instance;
	}

	public List<Recipe> receiverRecipeFindAll() throws ReceiverException {
		ArrayList<Recipe> recipes = null;
		try {
			recipes = (ArrayList<Recipe>) instance.recipeDao.findAll();
		} catch (DAOException e) {
			new ReceiverException("Exception in receiverRecipeFindAll method", e);
		}
		return recipes;
	}

	public Recipe receiverRecipeFindById(Integer id) throws ReceiverException {
		Recipe recipe = null;
		try {
			recipe = instance.recipeDao.findEntityById(id);
		} catch (DAOException e) {
			new ReceiverException("Exception in receiverRecipeFindById method", e);
		}
		return recipe;
	}

	public boolean receiverRecipeDelete(Integer id) throws ReceiverException {
		try {
			instance.recipeDao.delete(id);
		} catch (DAOException e) {
			new ReceiverException("Exception in receiverRecipeDelete method", e);
		}
		return true;
	}

	public boolean receiverRecipeDelete(Recipe recipe) throws ReceiverException {
		try {
			instance.recipeDao.delete(recipe);
		} catch (DAOException e) {
			new ReceiverException("Exception in receiverRecipeDelete method", e);
		}
		return true;
	}

	public boolean receiverRecipeAdd(Recipe recipe) throws ReceiverException {
		try {
			instance.recipeDao.add(recipe);
		} catch (DAOException e) {
			new ReceiverException("Exception in receiverRecipeAdd method", e);
		}
		return true;
	}

	public boolean receiverUpdateStatusRecipe(Recipe recipe) throws ReceiverException {
		try {
			instance.recipeDao.updateStatusRecipe(recipe);
		} catch (DAOException e) {
			new ReceiverException("Exception in receiverUpdateStatusRecipe method", e);
		}
		return true;
	}
	
	public List<Recipe> receiverFindRecipeByUserProduct(int id, int idProduct) throws ReceiverException {
		ArrayList<Recipe> recipes = null;
		try {
			recipes = (ArrayList<Recipe>) instance.recipeDao.findRecipeByUserProduct(id, idProduct);
		} catch (DAOException e) {
			new ReceiverException("Exception in receiverFindRecipeByUserProduct method", e);
		}
		return recipes;
	}
}