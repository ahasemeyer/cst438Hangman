package Hangman;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author shawn
 */
public class PasswordUtilTest {
    private static final boolean DEBUG = false;
    public PasswordUtilTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of hashPassword method, of class PasswordUtil.
     */
    @Test
    public void testHashPassword_String() throws Exception {
        System.out.println("hashPassword");
        String password = "cst438Hangman";
        String result = null;
        try {
            result = PasswordUtil.hashPassword(password);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        assertNotNull(result);
    }

    /**
     * Test of hashPassword method, of class PasswordUtil.
     */
    @Test
    public void testHashPassword_String_String() throws Exception {
        System.out.println("hashPassword");
        String password = "cst438Hangman";
        String salt = PasswordUtil.getSalt();
        if (DEBUG)
            System.out.println("salt: " + salt);
        String result = null;
        try {
            result = PasswordUtil.hashPassword(password, salt);
            if (DEBUG)
                System.out.println("salted password: " + result);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        assertNotNull(result);
    }

    /**
     * Test of getSalt method, of class PasswordUtil.
     */
    @Test
    public void testGetSalt() {
        System.out.println("getSalt");
        String result = PasswordUtil.getSalt();
        assertNotNull(result);
    }

    /**
     * Test of hashAndSaltPassword method, of class PasswordUtil.
     */
    @Test
    public void testHashAndSaltPassword() throws Exception {
        System.out.println("hashAndSaltPassword");
        String password = "cst438Hangman";
        String result = null;
        try {
            result = PasswordUtil.hashAndSaltPassword(password);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        assertNotNull(result);
    }

}
