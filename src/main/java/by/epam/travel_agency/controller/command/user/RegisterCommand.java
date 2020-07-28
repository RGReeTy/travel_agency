package by.epam.travel_agency.controller.command.user;

import by.epam.travel_agency.bean.User;
import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.controller.paramName.MessageKey;
import by.epam.travel_agency.controller.paramName.RequestParameterName;
import by.epam.travel_agency.service.factory.ServiceFactory;
import by.epam.travel_agency.service.receiver.ReceiverException;
import by.epam.travel_agency.service.receiver.UserService;
import by.epam.travel_agency.service.util.ConfigurationManager;
import by.epam.travel_agency.service.validation.UserValidator;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.travel_agency.controller.paramName.RequestParameterName.MESSAGE;
import static by.epam.travel_agency.controller.util.EntityBuilderHelper.creatNewUserFromRequest;

/**
 * The type Register command.
 */
public class RegisterCommand implements Command {

    private static final Logger logger = Logger.getLogger(RegisterCommand.class);

    private static final String LOGIN = "login";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserService userService = ServiceFactory.getInstance().getUserService();

        if (UserValidator.isOkParametersOfNewUserBeforeCreating(request)) {

            try {
                if (!userService.isThisLoginBusy(request.getParameter(LOGIN))) {

                    User user = creatNewUserFromRequest(request);

                    if (userService.addNewUser(user)) {
                        forwardToPage(request, response, ConfigurationManager.getProperty(RequestParameterName.PAGE_MAIN));
                    }

                } else {
                    logger.info("This login already exist!");
                    request.setAttribute(MESSAGE, MessageKey.REGISTER_LOGIN_ERROR);
                    response.sendRedirect(ConfigurationManager.getProperty(RequestParameterName.PAGE_ERROR));
                }

            } catch (ReceiverException | ServletException | IOException e) {
                logger.error("Error catches at RegisterCommand: " + e);
                request.setAttribute(MESSAGE, MessageKey.REGISTER_ERROR);
                response.sendRedirect(ConfigurationManager.getProperty(RequestParameterName.PAGE_ERROR));
            }
        } else {
            request.setAttribute(MESSAGE, MessageKey.REGISTER_ERROR);
            response.sendRedirect(ConfigurationManager.getProperty(RequestParameterName.PAGE_ERROR));
        }
    }
}