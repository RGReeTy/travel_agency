package by.epam.travel_agency.service.util;

import org.mindrot.jbcrypt.BCrypt;

/**
 * The type Hash string helper.
 */
public class HashStringHelper {

    private static final String HASHED_STRING_START = "$2a$";
    private static int workload = 12;


    /**
     * Hash password string.
     *
     * @param password_plaintext the password plaintext
     * @return the string
     */
    public static String hashPassword(String password_plaintext) {
        String salt = BCrypt.gensalt(workload);
        return BCrypt.hashpw(password_plaintext, salt);
    }


    /**
     * Check password boolean.
     *
     * @param password_plaintext the password plaintext
     * @param stored_hash        the stored hash
     * @return the boolean
     */
    public static boolean checkPassword(String password_plaintext, String stored_hash) {
        System.out.println(password_plaintext + "\n" + stored_hash);

        if (stored_hash == null || !stored_hash.startsWith(HASHED_STRING_START)) {
            throw new IllegalArgumentException("Invalid hash provided for comparison");
        }

        return BCrypt.checkpw(password_plaintext, stored_hash);
    }
}
