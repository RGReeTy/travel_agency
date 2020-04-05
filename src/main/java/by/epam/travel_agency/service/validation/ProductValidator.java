package main.java.by.epam.travel_agency.service.validation;

import java.util.regex.Pattern;

import by.epam.pharmacy.constant.MessageKey;

public class ProductValidator {
	public static final Pattern PRODUCT_NAME_PATTERN = Pattern.compile("\\A[А-ЯA-Z\\d][А-Яа-я\\w\\s:.!?,-]+\\z");
	public static final Pattern PRODUCT_PRICE_PATTERN = Pattern.compile("\\A[\\d]{1,4}\\z");
	public static final Pattern PRODUCT_PRODUCER_PATTERN = Pattern.compile("\\A[А-ЯA-Z\\d][А-Яа-я\\w\\s:.!?,-]+\\z");
	public static final Pattern PRODUCT_DOSAGE_PATTERN = Pattern.compile("\\A[\\d]{1,3}\\z");
	
	public static String validateProduct(Product product) {
		if (!PRODUCT_NAME_PATTERN.matcher(product.getName()).find()) {
			return MessageKey.PRODUCT_NAME_PATTERN_ERROR;
		}
		if (!PRODUCT_PRICE_PATTERN.matcher(Integer.toString(product.getPrice())).find()) {
			return MessageKey.PRODUCT_PRICE_PATTERN_ERROR;
		}
		if (!PRODUCT_PRODUCER_PATTERN.matcher(product.getProducer()).matches()) {
			return MessageKey.PRODUCT_PRODUCER_PATTERN_ERROR;
		}
		if (!PRODUCT_DOSAGE_PATTERN.matcher(Integer.toString(product.getDosage())).find()) {
			return MessageKey.PRODUCT_DOSAGE_PATTERN_ERROR;
		}
		return null;
	}
}