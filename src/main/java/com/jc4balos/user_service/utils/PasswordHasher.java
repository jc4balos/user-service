package com.jc4balos.user_service.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringHasher {

    public static String createPassword(String string) {
        try {
            String salt = SaltGenerator.generateSalt(16);
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            String concatedString = string + salt;
            byte[] hashedBytes = messageDigest.digest(concatedString.getBytes());
            return bytesToHex(hashedBytes);
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
