package com.jc4balos.user_service.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class StringHasher {

    public static String hashString(String string) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(string);
    }

    /*
     * @param rawString - the raw string to be checked
     * 
     * @param hashedString - the hashed string to be compared
     * 
     * @return boolean - true if the raw string matches the hashed string, false
     * otherwise
     */
    public static boolean checkString(String rawString, String hashedString) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(rawString, hashedString);
    }
}
