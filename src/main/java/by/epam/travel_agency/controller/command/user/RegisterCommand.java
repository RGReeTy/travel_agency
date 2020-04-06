package main.java.by.epam.travel_agency.controller.command.user;


import main.java.by.epam.travel_agency.bean.User;
import main.java.by.epam.travel_agency.constant.MessageKey;
import main.java.by.epam.travel_agency.controller.command.Command;
import main.java.by.epam.travel_agency.receiver.ReceiverException;
import main.java.by.epam.travel_agency.service.manager.ConfigurationManager;
import main.java.by.epam.travel_agency.service.validation.UserValidator;
import org.apache.logging.log4j.Level;

import javax.servlet.http.HttpServletRequest;

public class RegisterCommand implements Command {

    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String PARAM_NAME_PASSWORD_REPEAT = "password_repeat";
    private static final String PARAM_NAME_EMAIL = "email";

    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        String passwordRepeat = request.getParameter(PARAM_NAME_PASSWORD_REPEAT);
        String email = request.getParameter(PARAM_NAME_EMAIL);

        if (login.length() * password.length() * passwordRepeat.length() * email.length() == 0) {
            request.setAttribute("message", MessageKey.REGISTER_BLANK_FIELDS);
            return ConfigurationManager.getProperty("path.page.register");
        }
        if (!password.equals(passwordRepeat)) {
            request.setAttribute("message", MessageKey.REGISTER_PASSWORD_MISMATCH);
            return ConfigurationManager.getProperty("path.page.register");
        }
        User user = new User();
        user.setUsername(login);
        user.setPassword(password);
        user.setEmail(email);
        //user.setAccessLevel(3);
        String validationMessage = UserValidator.validateUser(user);
        if (validationMessage != null) {
            request.setAttribute("message", validationMessage);
            return ConfigurationManager.getProperty("path.page.register");
        }
//		try {
//			List<User> users = USER_RECEIVER.receiverUserFindAll();
//			users.size();
//			int x = users.size() + 1;
//			user.setId(x);
//		} catch (ReceiverException e) {
//			LOGGER.log(Level.ERROR, e.getMessage());
//			request.setAttribute("message", MessageKey.DATABASE_ERROR);
//			return ConfigurationManager.getProperty("path.page.error");
//		}
        try {
            if (USER_RECEIVER.receiverUserFindByLogin(login) != null) {
                request.setAttribute("message", MessageKey.REGISTER_LOGIN_ERROR);
                return ConfigurationManager.getProperty("path.page.register");
            }
            if (USER_RECEIVER.receiverUserAdd(user)) {
                user = USER_RECEIVER.receiverUserFindByLogin(user.getUsername());
                request.getSession().setAttribute("user", user);
                request.setAttribute("message", MessageKey.REGISTER_SUCCESS);
                return ConfigurationManager.getProperty("path.page.success");
            } else {
                request.setAttribute("message", MessageKey.REGISTER_ERROR);
                return ConfigurationManager.getProperty("path.page.error");
            }
        } catch (ReceiverException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            request.setAttribute("message", MessageKey.DATABASE_ERROR);
            return ConfigurationManager.getProperty("path.page.error");
        }
    }
}