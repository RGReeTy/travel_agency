package by.epam.travel_agency.controller.command.user;

import by.epam.travel_agency.bean.User;
import by.epam.travel_agency.controller.MessageKey;
import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.service.factory.ServiceFactory;
import by.epam.travel_agency.service.receiver.ReceiverException;
import by.epam.travel_agency.service.receiver.UserService;
import by.epam.travel_agency.service.util.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogInCommand implements Command {

    private static final Logger logger = Logger.getLogger(LogInCommand.class);

    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request) {

        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);

        logger.info("LOGIN " + login + " pass " + password);

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();
        HttpSession session = request.getSession();

        try {
            User user = userService.receiverUserFindByLoginAndPassword(login, password);
            if (user == null) {
                logger.debug("Unknown user tried entering " + getClass());
                request.setAttribute("message", MessageKey.LOG_IN_ERROR);
                return ConfigurationManager.getProperty("path.page.error");
            } else {
                //TODO положить в сессию а не в реквест
                request.getSession().setAttribute("user", user);
                //TODO
                // Ольга сказала, что отслеживаем юзера по ИД, а не по логину
                //LOGGER.info("User with id {} was authorized", user.getId());
                return ConfigurationManager.getProperty("path.page.main");
            }
        } catch (ReceiverException e) {
            logger.debug(e);
            request.setAttribute("message", MessageKey.DATABASE_ERROR);
            return ConfigurationManager.getProperty("path.page.error");
        }
    }
}