package com.example.sweets.util;

import java.security.SecureRandom;


public class OTPGenerator {

    private static final SecureRandom random = new SecureRandom();

    public static String generateOtp(){
        int number = 100000 + random.nextInt(900000);
        return String.valueOf(number);
    }
}
