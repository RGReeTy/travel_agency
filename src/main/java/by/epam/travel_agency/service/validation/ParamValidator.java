package by.epam.travel_agency.service.validation;

/**
 * The type Param validator.
 */
public class ParamValidator {

    private static final String LETTERS_WITH_DASHES = "[a-zA-Zа-яА-Я-]";
    private static final String LETTER_AND_NUMBER = "[a-zA-Z0-9]*";
    private static final String ONLY_LATIN_LETTER = "[a-zA-Z]";
    private static final String ONLY_LETTER = "[a-zA-Zа-яА-Я]";
    private static final String ANY_SYMBOLS = ".+";

    /**
     * Validate id boolean.
     *
     * @param id the id
     * @return the boolean
     */
    public static boolean validateId(int id) {
        return id >= 0;
    }

    /**
     * Validate positive number boolean.
     *
     * @param id the id
     * @return the boolean
     */
    public static boolean validatePositiveNumber(int id) {
        return id > 0;
    }

    /**
     * Validate only latin letters boolean.
     *
     * @param str the str
     * @return the boolean
     */
    public static boolean validateOnlyLatinLetters(String str) {
        return str.matches(ONLY_LATIN_LETTER);
    }

    /**
     * Validate latin letters and numbers boolean.
     *
     * @param str the str
     * @return the boolean
     */
    public static boolean validateLatinLettersAndNumbers(String str) {
        return str.matches(LETTER_AND_NUMBER);
    }

    /**
     * Validate letters boolean.
     *
     * @param str the str
     * @return the boolean
     */
    public static boolean validateLetters(String str) {
        return str.matches(ONLY_LETTER);
    }

    /**
     * Validate string with symbols and numbers boolean.
     *
     * @param str the str
     * @return the boolean
     */
    public static boolean validateStringWithSymbolsAndNumbers(String str) {
        return str.matches(LETTER_AND_NUMBER);
    }

    /**
     * Validate string with dashes boolean.
     *
     * @param str the str
     * @return the boolean
     */
    public static boolean validateStringWithDashes(String str) {
        return str.matches(LETTERS_WITH_DASHES);
    }

    /**
     * Not empty.
     *
     * @param string the string
     */
    public static void notEmpty(String string) {
        if (string == null || string.length() == 0)
            throw new IllegalArgumentException("String must not be empty");
    }
}
