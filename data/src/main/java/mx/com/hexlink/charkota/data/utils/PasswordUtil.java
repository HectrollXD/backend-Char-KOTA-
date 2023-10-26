package mx.com.hexlink.charkota.data.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;



public class PasswordUtil {
    public static String encrypt(String passworToEncrypt) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");

        return new String(
                digest.digest(passworToEncrypt.getBytes(StandardCharsets.UTF_8)),
                StandardCharsets.UTF_8
        );
    }

    public static boolean verify(String passwordToVerify, String passwordEncripted) throws NoSuchAlgorithmException {
        return encrypt(passwordToVerify).equals(passwordEncripted);
    }
}
