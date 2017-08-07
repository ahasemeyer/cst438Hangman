package Hangman;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
/**
 * Game contains logic to play hangman game.
 * Includes code to generate new words,
 * process user guess, determine win or lose.
 * @author Austin Hasemeyer
 * @version 1.0
 */
public class Game {
 
    private Random generator;  
    private int state;  
	           //  1 means no incorrect guesses, or start of game
                   //  2 means 1 incorrect guess.
                   //  3 means 2 incorrect guesses.
                   //  4 means 3 incorrect guesses.
                   //  5 means 4 incorrect guesses.
                   //  6 means 5 incorrect guesses.
	           //  7 means 6 incorrect guesses, user has lost game
    
    private String word;   // the word to be guessed 
    private StringBuffer displayWord; // part of the word (if any) to show user
    private ArrayList<String> wordlist;  // list of words
    private boolean debug = false;
    private Data.History history = null;
    private String username;
    private String difficulty;
    
    
    @Override
    public String toString()
    {
        return "Game[state="+state+" word="+word+" displayWord="+displayWord.toString()+"]";
    }
    
    
    public Game(String username, String difficulty) 
    {
        this.username = username;
        this.difficulty = difficulty; 
        startNewGame();
    }
    
    public int getState()
    {
        return state;
    }
    
    public String getWord()
    {
        return word;
    }
    
    public String getDisplayWord()
    {
        return displayWord.toString();
    }
    
    
    public String getDifficulty()
    {
        return difficulty;
    }
    
    
    public void startNewGame() {
        state = 1;
        String temp = difficulty;
        String temp1 = WordPicker.getWord();
        System.out.print("Difficulty Level ");
        System.out.println(temp);
        

        if("easy".equals(temp))
        {
            while(temp1.length() > 3)
            {
                temp1 = WordPicker.getWord();
                System.out.print(temp1.length());
            }
            
        }
        if("medium".equals(temp))
        {
            while(temp1.length() > 6 && temp1.length() < 3)
            {
                temp1 = WordPicker.getWord();
            }
        }       
        if("hard".equals(temp))
        {
            while(temp1.length() < 6)
            {
                temp1 = WordPicker.getWord();
            }
        }
        
        
        if (debug) 
            word = "computer";
        else  
            word = temp1;
        createDisplayWord();
        history = Data.DBUtil.createHistory(word,username); 
        System.out.println("Game  history="+history);    
    }
    
    public void win()
    {
        System.out.println("Game.win history="+history);
        if (history != null) {
            history.winGame();
            Data.DBUtil.updateHistory(history);
        }
    }
    
    
    public void lose()
    {
        System.out.println("Game.lose history="+history);
        if (history != null) {
            history.loseGame();
            Data.DBUtil.updateHistory(history);
        }
    }
    
    
    
    /*
     * process a user guess return page saying game over user win or lost, or
     * return page with updated game display
     * return 0 = continue game, good guess
     *        1 = good guess.  Win game. 
     *        2 = bad guess.  continue game
     *        3 = bad guess.  Lost game.
     */
    public int playGame(char guess) 
    {
            boolean correctGuess = updateDisplayWord(guess);         
            boolean correctInput = isInputCorrect(guess);
            if (!correctInput)
            {
                return 4;
            }
            else if (correctGuess==false) { 
                state++;
                if (state==7) {
                    // user has lost game
                    return 3;
                } else {
                    return 2; // bad guess, continue
                }
            }
            else if ( displayWord.indexOf("_") >= 0) {
               return 0; // continue game, with good guess
            } else {
               return 1; // all characters has been guessed, user has won game.
            }
    }
    
    
    /*
     *Check the input to see if it is legal
     *If it is not a letter return false
     *If the input is more than 1 letter return false
    */ 
    private boolean isInputCorrect(char guess)
    {
        boolean correctInput = true;
        
        if(Character.isLetter(guess) != true)
            correctInput = false;
        
        return correctInput;
    }
    
    /**
     * update display word to show any occurrences of guess
     * There is a space character between each letter, and 
     * any letters remaining to be guessed are displayed as underscore
     * @param guess
     * @return true if at least one match, false if guess is incorrect
     */
    private boolean updateDisplayWord(char guess){
        boolean correctGuess = false;
        for (int i=0; i<word.length(); i++) {
            if (word.charAt(i)==Character.toLowerCase(guess)){
                    displayWord.replace(2*i, 2*i+1, Character.toString(Character.toLowerCase(guess))); 
                    correctGuess=true;
                }
        }
        return correctGuess;
        
    }
    /**
     * Create the display version of word with letters replaced by
     * underscore for letter remaining to be guessed
     * @param word
     * @return 
     */
    private void createDisplayWord(){
        displayWord = new StringBuffer("_");
        for (int i=1; i<word.length(); i++) {
            displayWord.append(" _");
        }
   
    }
    
    /*
    private String randomWord() {
	try {
            if (wordlist == null) {
		wordlist = new ArrayList<String>();
		// read in word list
		Scanner infile = new Scanner(new File(RESOURCE_DIR + "wordlist.txt"));
		while (infile.hasNextLine()) {
                    wordlist.add(infile.nextLine());
		}
		infile.close();
            }
            int t = generator.nextInt(wordlist.size());
            return wordlist.get(t);

        } catch (Exception e) {
            System.out.println("Error randomWord: reading wordlist. " + e.getMessage());
            System.exit(0);
            return null; // to keep compiler happy
	}
    }
*/
}
