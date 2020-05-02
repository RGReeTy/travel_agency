package by.epam.travel_agency.controller.command.user;


import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.service.manager.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class RegisterCommand implements Command {

    private static final Logger logger = Logger.getLogger(RegisterCommand.class);


    @Override
    public String execute(HttpServletRequest request) {

        if (USER_RECEIVER.receiverUserAdd(request)) {
            return ConfigurationManager.getProperty("path.page.success");
        } else {
            return ConfigurationManager.getProperty("path.page.error");
        }
    }
}