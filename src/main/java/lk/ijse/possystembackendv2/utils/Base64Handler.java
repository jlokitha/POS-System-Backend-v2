package lk.ijse.possystembackendv2.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

public class Base64Handler {
    public static String toBase64ProfilePic(MultipartFile profilePic) throws IOException {
        return Base64.getEncoder().encodeToString(profilePic.getBytes());
    }
}
