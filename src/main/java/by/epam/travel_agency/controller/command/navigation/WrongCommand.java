package by.epam.travel_agency.controller.command.navigation;

import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.service.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class WrongCommand implements Command {


    @Override
    public String execute(HttpServletRequest request) {
        return ConfigurationManager.getProperty("path.page.main");
    }
}
