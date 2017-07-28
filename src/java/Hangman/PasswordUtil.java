/*
 * Murach book example code
 * servlet_and_jsp/netbeans/book_apps/ch17password/src/java/murach/util/PasswordUtil.java
 *
 * Modified to use SHA-512
 */
package Hangman;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.Base64;

public class PasswordUtil {
    
    /*  This code uses SHA-512. If this algorithm isn't available to you,
        you can try a weaker level of encryption such as SHA-128.
    */    
    public static String hashPassword(String password)
            throws NoSuchAlgorithmException {        
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.reset();
        md.update(password.getBytes());
        byte[] mdArray = md.digest();
        StringBuilder sb = new StringBuilder(mdArray.length * 2);
        for (byte b : mdArray) {
            int v = b & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }        
        return sb.toString();        
    }
    public static String hashPassword(String password, String salt)
            throws NoSuchAlgorithmException {
        return hashPassword(password + salt);
    }
    public static String getSalt() {
        Random r = new SecureRandom();
        byte[] saltBytes = new byte[32];
        r.nextBytes(saltBytes);
        return Base64.getEncoder().encodeToString(saltBytes);
    }
    
    public static String hashAndSaltPassword(String password)
            throws NoSuchAlgorithmException {
        String salt = getSalt();
        return hashPassword(password, salt);
    }
    
    public static void main(String[] args) {
        try {
            String salt = getSalt();
            String pass = hashPassword(args[0], salt);
            System.out.println("salt: " + salt);
            System.out.println("hash: " + pass);          
        } catch (NoSuchAlgorithmException ex) {
            System.err.println(ex);
        }    
    }
}