package com.project.task.config;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class TokenGenerator {

    public static String generateToken() {
        // Generate a random string (e.g., using SecureRandom)
        String randomString = generateRandomString();

        // Compute the MD5 hash of the random string
        String token = computeMD5Hash(randomString);

        return token;
    }

    private static String generateRandomString() {
        // Generate a random byte array
        byte[] randomBytes = new byte[16];
        new SecureRandom().nextBytes(randomBytes);

        // Convert byte array to hexadecimal string
        StringBuilder sb = new StringBuilder();
        for (byte b : randomBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    private static String computeMD5Hash(String input) {
        try {
            // Get instance of MD5 MessageDigest
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Compute the MD5 hash of the input string
            byte[] hashBytes = md.digest(input.getBytes());

            // Convert byte array to hexadecimal string
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

}
