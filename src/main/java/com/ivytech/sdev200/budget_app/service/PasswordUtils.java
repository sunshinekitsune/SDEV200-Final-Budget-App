package com.ivytech.sdev200.budget_app.service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public final class PasswordUtils {
    private static final SecureRandom RANDOM = new SecureRandom();

    private PasswordUtils() {}

    public static String generateSalt() {
        byte[] salt = new byte[16];
        RANDOM.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    public static String hashPassword(String password, String salt) {
        try {
            var spec =
                    new PBEKeySpec(
                            password.toCharArray(), Base64.getDecoder().decode(salt), 10_000, 256);
            var factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            return Base64.getEncoder().encodeToString(factory.generateSecret(spec).getEncoded());
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new IllegalStateException("Password hashing failed", e);
        }
    }

    public static boolean verify(String password, String salt, String hash) {
        return hashPassword(password, salt).equals(hash);
    }
}
