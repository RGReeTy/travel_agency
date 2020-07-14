package by.epam.travel_agency.service.util;

import org.mindrot.jbcrypt.BCrypt;

public class HashStringHelper {

    private static final String HASHED_STRING_START = "$2a$";
    private static int workload = 12;


    public static String hashPassword(String password_plaintext) {
        String salt = BCrypt.gensalt(workload);
        return BCrypt.hashpw(password_plaintext, salt);
    }


    public static boolean checkPassword(String password_plaintext, String stored_hash) {
        System.out.println(password_plaintext + "\n" + stored_hash);

        if (stored_hash == null || !stored_hash.startsWith(HASHED_STRING_START)) {
            throw new IllegalArgumentException("Invalid hash provided for comparison");
        }

        return BCrypt.checkpw(password_plaintext, stored_hash);
    }
}
