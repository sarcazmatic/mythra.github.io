package ru.maleth.mythra.encrypter;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PassEncTech {

    private static String encryptedPassword;

    public static String encryptPass(String password) {
        try
        {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(password.getBytes());

            //Convert the hash value into bytes
            byte[] bytes = m.digest();

            //The bytes array has bytes in decimal form. Converting it into hexadecimal format.
            StringBuilder s = new StringBuilder();
            for(int i=0; i< bytes.length ;i++) {
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            // Complete hashed password in hexadecimal format.
            encryptedPassword = s.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return encryptedPassword;
    }

}
