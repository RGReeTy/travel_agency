package main.java.by.epam.travel_agency.controller.command;

import main.java.by.epam.travel_agency.controller.command.locale.ChangeLocaleCommand;
import main.java.by.epam.travel_agency.controller.command.order.CancelOrderCommand;
import main.java.by.epam.travel_agency.controller.command.order.DeleteOrderCommand;
import main.java.by.epam.travel_agency.controller.command.order.DeliverOrderCommand;
import main.java.by.epam.travel_agency.controller.command.order.MakeOrderCommand;
import main.java.by.epam.travel_agency.controller.command.order.ShowConcreteOrderCommand;
import main.java.by.epam.travel_agency.controller.command.order.ShowOrderCommand;
import main.java.by.epam.travel_agency.controller.command.page.AddProductPageCommand;
import main.java.by.epam.travel_agency.controller.command.page.AllOrderPageCommand;
import main.java.by.epam.travel_agency.controller.command.page.EditProductPageCommand;
import main.java.by.epam.travel_agency.controller.command.page.GoToPageCommand;
import main.java.by.epam.travel_agency.controller.command.page.UserPageCommand;
import main.java.by.epam.travel_agency.controller.command.product.AddProductCommand;
import main.java.by.epam.travel_agency.controller.command.product.DeleteProductCommand;
import main.java.by.epam.travel_agency.controller.command.product.EditProductCommand;
import main.java.by.epam.travel_agency.controller.command.product.SearchProductCommand;
import main.java.by.epam.travel_agency.controller.command.product.ShowConcreteProductCommand;
import main.java.by.epam.travel_agency.controller.command.product.ShowProductCommand;
import main.java.by.epam.travel_agency.controller.command.recipe.ConfirmRecipeCommand;
import main.java.by.epam.travel_agency.controller.command.recipe.MakeRecipeCommand;
import main.java.by.epam.travel_agency.controller.command.recipe.ShowRecipeCommand;
import main.java.by.epam.travel_agency.controller.command.user.AddAccountCommand;
import main.java.by.epam.travel_agency.controller.command.user.AddToCartCommand;
import main.java.by.epam.travel_agency.controller.command.user.LogInCommand;
import main.java.by.epam.travel_agency.controller.command.user.LogOutCommand;
import main.java.by.epam.travel_agency.controller.command.user.RegisterCommand;
import main.java.by.epam.travel_agency.controller.command.user.RemoveFromCartCommand;
import main.java.by.epam.travel_agency.controller.command.user.ShowAccountCommand;
import main.java.by.epam.travel_agency.controller.command.user.ShowCartCommand;

public enum CommandEnum {

	ADD_ACCOUNT {
		{
			this.command = new AddAccountCommand();
		}
	},
	ADD_PRODUCT {
		{
			this.command = new AddProductCommand();
		}
	},
	ADD_PRODUCT_PAGE {
		{
			this.command = new AddProductPageCommand();
		}
	},
	ADD_TO_CART {
		{
			this.command = new AddToCartCommand();
		}
	},
	ALL_ORDERS_PAGE {
		{
			this.command = new AllOrderPageCommand();
		}
	},
	CANCEL_ORDER {
		{
			this.command = new CancelOrderCommand();
		}
	},
	CHANGE_LOCALE {
		{
			this.command = new ChangeLocaleCommand();
		}
	},
	CONFIRM_RECIPE {
		{
			this.command = new ConfirmRecipeCommand();
		}
	},
	DELETE_ORDER {
		{
			this.command = new DeleteOrderCommand();
		}
	},
	DELETE_PRODUCT {
		{
			this.command = new DeleteProductCommand();
		}
	},
	DELIVER_ORDER {
		{
			this.command = new DeliverOrderCommand();
		}
	},
	EDIT_PRODUCT {
		{
			this.command = new EditProductCommand();
		}
	},
	EDIT_PRODUCT_PAGE {
		{
			this.command = new EditProductPageCommand();
		}
	},
	GO_TO_PAGE {
		{
			this.command = new GoToPageCommand();
		}
	},
	LOGIN {
		{
			this.command = new LogInCommand();
		}
	},
	LOGOUT {
		{
			this.command = new LogOutCommand();
		}
	},
	MAKE_ORDER {
		{
			this.command = new MakeOrderCommand();
		}
	},
	MAKE_RECIPE {
		{
			this.command = new MakeRecipeCommand();
		}
	},
	REGISTRATION {
		{
			this.command = new RegisterCommand();
		}
	},
	REMOVE_FROM_CART {
		{
			this.command = new RemoveFromCartCommand();
		}
	},
	SEARCH_PRODUCTS {
		{
			this.command = new SearchProductCommand();
		}
	},
	SHOW_CART_ACTION {
		{
			this.command = new ShowCartCommand();
		}
	},
	SHOW_ACCOUNT {
		{
			this.command = new ShowAccountCommand();
		}
	},
	SHOW_LIST_RECIPES {
		{
			this.command = new ShowRecipeCommand();
		}
	},
	SHOW_ORDER {
		{
			this.command = new ShowConcreteOrderCommand();
		}
	},
	SHOW_ORDERS {
		{
			this.command = new ShowOrderCommand();
		}
	},
	SHOW_PRODUCT {
		{
			this.command = new ShowConcreteProductCommand();
		}
	},
	SHOW_PRODUCTS {
		{
			this.command = new ShowProductCommand();
		}
	},
	USERS_PAGE {
		{
			this.command = new UserPageCommand();
		}
	};

	Command command;

	public Command getCurrentCommand() {
		return command;
	}
}