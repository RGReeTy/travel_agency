package by.epam.travel_agency.service.util;

import java.util.ResourceBundle;

/**
 * The type Configuration manager.
 */
public class ConfigurationManager {
	private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("config");

	private ConfigurationManager() {
	}

	/**
	 * Gets property.
	 *
	 * @param key the key
	 * @return the property
	 */
	public static String getProperty(String key) {
		return resourceBundle.getString(key);
	}
}
