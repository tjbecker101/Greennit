package com.greennit.CS3141.webpage;

import com.greennit.CS3141.entities.User;
import com.greennit.CS3141.managers.UserManager;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserDAO {

    public User checkLogin (String username, String password) {
        UserManager manager = new UserManager();
        User user;
        try {
            user = manager.getUser(username.toLowerCase());
            if (user.getPass().equals(password)) {
                return user;
            }
            else {
                return null;
            }
        } catch (IllegalArgumentException e) {
            return null;
        }
        finally {
            manager.exit();
        }
    }

    public String SHA_256(String text) throws NoSuchAlgorithmException {
        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
        final byte[] hashbytes = digest.digest(
                text.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(hashbytes);
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
