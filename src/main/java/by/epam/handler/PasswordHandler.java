package by.epam.handler;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHandler {

    /**
     * Need to take hashed password by SHA-1 from default password
     * @param password need to take not hashed password
     * @return hashed password
     */
    public static String getHashedPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(password.getBytes());
            byte byteData[] = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte oneByte : byteData) {
                sb.append(String.format("%02x", oneByte));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {

            throw new RuntimeException("Cannot hash password");

        }
    }
}
