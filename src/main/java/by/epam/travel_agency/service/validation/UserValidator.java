package by.epam.travel_agency.service.validation;

import by.epam.travel_agency.bean.User;
import by.epam.travel_agency.constant.MessageKey;
import org.apache.log4j.Logger;

import java.util.regex.Pattern;

public class UserValidator {
    private static final Pattern LOGIN_PATTERN = Pattern.compile("\\A[A-Za-z][\\w]{2,14}\\z");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("\\A[\\w]{4,15}\\z");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("\\A[\\w]+@[a-z]+\\.[a-z]{2,5}\\z");
    private static final Logger logger = Logger.getLogger(UserValidator.class);

    public static String validateUser(User user) {

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
        return null;
    }
}
