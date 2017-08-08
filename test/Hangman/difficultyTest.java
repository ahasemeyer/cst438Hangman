/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hangman;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author hasmy
 */
public class difficultyTest {
    
    private static final boolean DEBUG = false;
    public difficultyTest() {
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

    
    @Test
    public void testDifficulty() throws Exception {
        boolean result = true;
        String level;
        String testUser = "testUser";
        String testWord;
        Hangman.Game testGame;
        
        level = "easy";
        for(int i = 0; i < 10; i++)
        {
            testGame = new Hangman.Game(testUser, level);
            testWord = testGame.getWord();
            if(testWord.length()>3)
                result = false; 
        }
        
        level = "medium";
        for(int i = 0; i < 10; i++)
        {
            testGame = new Hangman.Game(testUser, level);
            testWord = testGame.getWord();
            if(testWord.length() > 6 && testWord.length() < 3)
                result = false; 
        }
        
        
        level = "hard";
        for(int i = 0; i < 10; i++)
        {
            testGame = new Hangman.Game(testUser, level);
            testWord = testGame.getWord();
            if(testWord.length() < 6)
                result = false; 
        }
        
        assertTrue(result);
    }
    
}
