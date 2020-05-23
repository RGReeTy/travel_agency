package by.epam.travel_agency.controller.command.user;


import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.service.manager.ConfigurationManager;
import by.epam.travel_agency.service.receiver.ReceiverException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class RegisterCommand implements Command {

    private static final Logger logger = Logger.getLogger(RegisterCommand.class);


    @Override
    public String execute(HttpServletRequest request) {
        String response = ConfigurationManager.getProperty("path.page.error");
        try {
            if (USER_RECEIVER.receiverUserAdd(request)) {
                response = ConfigurationManager.getProperty("path.page.success");
            }
        } catch (ReceiverException e) {
            logger.error("Error catches at RegisterCommand: " + e);
        }
        return response;
    }
}