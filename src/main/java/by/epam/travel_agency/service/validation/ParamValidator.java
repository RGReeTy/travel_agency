package by.epam.travel_agency.service.validation;

public class ParamValidator {

    private static final String LETTERS_WITH_DASHES = "[a-zA-Zа-яА-Я-]";
    private static final String LETTER_AND_NUMBER = "[a-zA-Z0-9]*";
    private static final String ONLY_LATIN_LETTER = "[a-zA-Z]";
    private static final String ONLY_LETTER = "[a-zA-Zа-яА-Я]";
    private static final String ANY_SYMBOLS = ".+";

    public static boolean validateId(int id) {
        return id >= 0;
    }

    public static boolean validatePositiveNumber(int id) {
        return id > 0;
    }

    public static boolean validateOnlyLatinLetters(String str) {
        return str.matches(ONLY_LATIN_LETTER);
    }

    public static boolean validateLatinLettersAndNumbers(String str) {
        return str.matches(LETTER_AND_NUMBER);
    }

    public static boolean validateLetters(String str) {
        return str.matches(ONLY_LETTER);
    }

    public static boolean validateStringWithSymbolsAndNumbers(String str) {
        return str.matches(LETTER_AND_NUMBER);
    }

    public static boolean validateStringWithDashes(String str) {
        return str.matches(LETTERS_WITH_DASHES);
    }

    public static void notEmpty(String string) {
        if (string == null || string.length() == 0)
            throw new IllegalArgumentException("String must not be empty");
    }
}
