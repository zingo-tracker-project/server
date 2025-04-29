package com.tracker.server.utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

public class AESUtil {

    private static final String SECRET_KEY = "scdsfadfa134325d";
    private static final String SALT = "saltekgo";

    // AES 암호화 (iv + 암호문을 하나의 byte 배열로 합침)
    public static byte[] encryptToBytes(String strToEncrypt) {
        try {
            byte[] iv = new byte[16];
            new SecureRandom().nextBytes(iv);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            SecretKeySpec secretKey = new SecretKeySpec(
                    factory.generateSecret(new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256)).getEncoded(),
                    "AES"
            );

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
            byte[] encrypted = cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8));

            // 📌 IV + 암호문 합치기
            byte[] result = new byte[iv.length + encrypted.length];
            System.arraycopy(iv, 0, result, 0, iv.length);
            System.arraycopy(encrypted, 0, result, iv.length, encrypted.length);

            return result; // ⚡ byte[] 로 리턴
        } catch (Exception e) {
            throw new RuntimeException("Error while encrypting: " + e.getMessage(), e);
        }
    }

    // AES 복호화 (iv + 암호문 byte 배열 받아서 복호화)
    public static String decryptFromBytes(byte[] encryptedBytes) {
        try {
            byte[] iv = new byte[16];
            byte[] cipherText = new byte[encryptedBytes.length - 16];

            System.arraycopy(encryptedBytes, 0, iv, 0, 16);
            System.arraycopy(encryptedBytes, 16, cipherText, 0, cipherText.length);

            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            SecretKeySpec secretKey = new SecretKeySpec(
                    factory.generateSecret(new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256)).getEncoded(),
                    "AES"
            );

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
            return new String(cipher.doFinal(cipherText), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Error while decrypting: " + e.getMessage(), e);
        }
    }

    // JWT-safe 암호화 (byte[]를 Base64 URL-safe 인코딩)
    public static String encryptForJwt(String userId) {
        byte[] encryptedBytes = encryptToBytes(userId);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(encryptedBytes);
    }

    // JWT-safe 복호화 (Base64 URL-safe 디코딩 후 복호화)
    public static String decryptFromJwt(String encoded) {
        byte[] encryptedBytes = Base64.getUrlDecoder().decode(encoded);
        return decryptFromBytes(encryptedBytes);
    }
}
