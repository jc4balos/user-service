package com.jc4balos.user_service.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import com.jc4balos.user_service.model.User;

public class PasswordHasher {

    public static String hashPassword(User user, String inputPassword) {
        try {
            String salt = user.getSalt();
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

            String concatedString = inputPassword + salt;
            byte[] hashedBytes = messageDigest.digest(concatedString.getBytes());
            String inputPasswordHashed = bytesToHex(hashedBytes);
            return inputPasswordHashed;

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Password Encryption Error");
        }
    }

    public static Map<String, String> createPassword(String string) {
        try {
            String salt = SaltGenerator.generateSalt();
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            String concatedString = string + salt;
            byte[] hashedBytes = messageDigest.digest(concatedString.getBytes());
            String hashedPassword = bytesToHex(hashedBytes);
            return Map.of("password", hashedPassword, "salt", salt);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Password Encryption Error");
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1)
                hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

}
