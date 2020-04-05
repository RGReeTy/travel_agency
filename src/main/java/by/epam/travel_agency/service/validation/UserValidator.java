package main.java.by.epam.travel_agency.service.validation;

import java.util.regex.Pattern;

import by.epam.pharmacy.constant.MessageKey;
import by.epam.pharmacy.entity.User;

public class UserValidator {
	public static final Pattern LOGIN_PATTERN = Pattern.compile("\\A[A-Za-z][\\w]{2,14}\\z");
	public static final Pattern PASSWORD_PATTERN = Pattern.compile("\\A[\\w]{5,15}\\z");
	public static final Pattern EMAIL_PATTERN = Pattern.compile("\\A[\\w]+@[a-z]+\\.[a-z]{2,5}\\z");
	
	public static String validateUser(User user){
		if (!LOGIN_PATTERN.matcher(user.getLogin()).find()){
			return MessageKey.REGISTER_LOGIN_PATTERN_ERROR;
		}
		if (!LOGIN_PATTERN.matcher(user.getPassword()).find()){
			return MessageKey.REGISTER_PASSWORD_PATTERN_ERROR;
		}
		if (!EMAIL_PATTERN.matcher(user.getEmail()).matches()) {
			return MessageKey.REGISTER_EMAIL_PATTERN_ERROR;
		}
		return null;
	}
}
