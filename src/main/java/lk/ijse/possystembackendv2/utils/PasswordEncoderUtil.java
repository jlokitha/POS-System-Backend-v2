package lk.ijse.possystembackendv2.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class PasswordEncoderUtil {
    public static String encodePassword(String password) {
        return DigestUtils.sha256Hex(password);
    }
    public static boolean matches(String rawPassword, String encodedPassword) {
        return encodePassword(rawPassword).equals(encodedPassword);
    }
}