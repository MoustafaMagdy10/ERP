package com.erp.inventory.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtils {

    private static final int TEMP_PASSWORD_LENGTH = 12;
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+";

    public static String generateTempPassword() {
        return RandomStringUtils.random(TEMP_PASSWORD_LENGTH, CHARACTERS);
    }

    public static String encodeTempPassword(String tempPassword) {
        return new BCryptPasswordEncoder().encode(tempPassword);
    }
}