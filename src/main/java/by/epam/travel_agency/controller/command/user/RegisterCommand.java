package by.epam.travel_agency.controller.command.user;


import by.epam.travel_agency.bean.User;
import by.epam.travel_agency.constant.MessageKey;
import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.receiver.ReceiverException;
import by.epam.travel_agency.service.manager.ConfigurationManager;
import by.epam.travel_agency.service.validation.UserValidator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class RegisterCommand implements Command {

    private static final Logger logger = Logger.getLogger(RegisterCommand.class);

    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String PARAM_NAME_PASSWORD_REPEAT = "password_repeat";
    private static final String PARAM_NAME_EMAIL = "email";
    private static final String PARAM_NAME_FIRSTNAME = "firstname";
    private static final String PARAM_NAME_LASTNAME = "lastname";
    private static final String PARAM_NAME_PHONE = "phone";

    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        String passwordRepeat = request.getParameter(PARAM_NAME_PASSWORD_REPEAT);
        String email = request.getParameter(PARAM_NAME_EMAIL);
        String firstname = request.getParameter(PARAM_NAME_FIRSTNAME);
        String lastname = request.getParameter(PARAM_NAME_LASTNAME);
        String phone = request.getParameter(PARAM_NAME_PHONE);

        if (login.length() * password.length() * passwordRepeat.length() * email.length() == 0) {
            request.setAttribute("message", MessageKey.REGISTER_BLANK_FIELDS);
            return ConfigurationManager.getProperty("path.page.register");
        }
        if (!password.equals(passwordRepeat)) {
            request.setAttribute("message", MessageKey.REGISTER_PASSWORD_MISMATCH);
            return ConfigurationManager.getProperty("path.page.register");
        }
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setEmail(email);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setPhone(phone);
        user.setId_discount(1);
        user.setLevel_access(2);

        logger.info(user.toString());

        String validationMessage = UserValidator.validateUser(user);

        logger.info("after validator");

        if (validationMessage != null) {
            request.setAttribute("message", validationMessage);
            logger.info(validationMessage);
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
                logger.info("This login already exist!");
                request.setAttribute("message", MessageKey.REGISTER_LOGIN_ERROR);
                return ConfigurationManager.getProperty("path.page.register");
            }
            if (USER_RECEIVER.receiverUserAdd(user)) {
                logger.info(" check new user by login "+ getClass());
                user = USER_RECEIVER.receiverUserFindByLogin(user.getLogin());
                request.getSession().setAttribute("user", user);
                request.setAttribute("message", MessageKey.REGISTER_SUCCESS);
                return ConfigurationManager.getProperty("path.page.success");
            } else {
                request.setAttribute("message", MessageKey.REGISTER_ERROR);
                return ConfigurationManager.getProperty("path.page.error");
            }
        } catch (ReceiverException e) {
            logger.debug(e.getMessage());
            request.setAttribute("message", MessageKey.DATABASE_ERROR);
            return ConfigurationManager.getProperty("path.page.error");
        }
    }
}