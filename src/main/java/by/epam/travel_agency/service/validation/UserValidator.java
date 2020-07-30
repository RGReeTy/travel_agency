package by.epam.travel_agency.service.validation;

import by.epam.travel_agency.bean.User;
import by.epam.travel_agency.controller.paramName.MessageKey;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

public class UserValidator {
    private static final Pattern LOGIN_PATTERN = Pattern.compile("\\A[A-Za-z][\\w]{2,14}\\z");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("\\A[\\w]{4,15}\\z");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("\\A[\\w]+@[a-z]+\\.[a-z]{2,5}\\z");
    private static final String IS_OK = "ok";
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String PARAM_NAME_PASSWORD_REPEAT = "password_repeat";
    private static final String PARAM_NAME_EMAIL = "email";

    private static final Logger logger = Logger.getLogger(UserValidator.class);


    public static boolean isOkParametersOfNewUserBeforeCreating(HttpServletRequest request) {
        boolean isOk = true;
        if (isParametersAreNotNull(request)) {
            request.setAttribute("message", MessageKey.REGISTER_BLANK_FIELDS);
            isOk = false;
        }
        if (!isPasswordRepeat(request)) {
            request.setAttribute("message", MessageKey.REGISTER_PASSWORD_MISMATCH);
            isOk = false;
        }
        return isOk;
    }

    private static boolean isParametersAreNotNull(HttpServletRequest request) {
        return (request.getParameter(PARAM_NAME_LOGIN).length() *
                request.getParameter(PARAM_NAME_PASSWORD).length() *
                request.getParameter(PARAM_NAME_PASSWORD_REPEAT).length() *
                request.getParameter(PARAM_NAME_EMAIL).length() == 0);
    }

    private static boolean isPasswordRepeat(HttpServletRequest request) {
        return (request.getParameter(PARAM_NAME_PASSWORD).equals(request.getParameter(PARAM_NAME_PASSWORD_REPEAT)));
    }

    public static String validateUserToMatchThePattern(User user) {
        logger.info("Start Validator");

        if (!LOGIN_PATTERN.matcher(user.getLogin()).find()) {
            return MessageKey.REGISTER_LOGIN_PATTERN_ERROR;
        }
        if (!PASSWORD_PATTERN.matcher(user.getPassword()).find()) {
            return MessageKey.REGISTER_PASSWORD_PATTERN_ERROR;
        }
        if (!EMAIL_PATTERN.matcher(user.getEmail()).matches()) {
            return MessageKey.REGISTER_EMAIL_PATTERN_ERROR;
        }
        logger.info("END Validator - success");
        return IS_OK;
    }

    public static boolean checkUserIsAdmin(User user) {
        return user.getLevelAccess() == 0;
    }

    public static boolean checkUserIsManager(User user) {
        return user.getLevelAccess() == 1;
    }
}
