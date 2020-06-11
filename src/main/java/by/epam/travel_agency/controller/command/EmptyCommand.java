package by.epam.travel_agency.controller.command;

import javax.servlet.http.HttpServletRequest;

import by.epam.travel_agency.service.util.ConfigurationManager;

public class EmptyCommand implements Command {
	
	/**
     * EmptyCommand will be executed if a servlet has been accessed without a command.
     * @param request HttpServletRequest request
     * @return the path of main page.
     */

    @Override
    public String execute(HttpServletRequest request) {
    /*In case of error or empty command, redirect to main page */
        return ConfigurationManager.getProperty("path.page.main");
    }
}