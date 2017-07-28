package Hangman;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author shawn
 */
public class UserCredTest {
    
    public UserCredTest() {
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
     * Test of chkPass method, of class UserCred.
     */
    @Test
    public void testChkPass_3args() {
        System.out.println("chkPass");
        // !!!! make sure this user can't login to the site
        String username = "testpackage";
        String password = "cst438Hangman";
        // force chkPass to allow disabled user for test
        boolean enabled = false;
        boolean result = UserCred.chkPass(username, password, enabled);
        assertTrue(result);
    }   
}
