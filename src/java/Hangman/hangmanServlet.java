package Hangman;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Austin
 */
@WebServlet(urlPatterns = {"/hangmanServlet"})
public class hangmanServlet extends HttpServlet {
    static final java.util.regex.Pattern UNAMEPAT =
        java.util.regex.Pattern.compile("^([A-Za-z]+)$");
    static final java.util.regex.Pattern PWORDPAT =
        java.util.regex.Pattern.compile("^([!-~]+)$");
    static final java.util.regex.Pattern QUERYPAT =
        java.util.regex.Pattern.compile("^([A-Za-z])");
    //private Hangman.Game game = new Hangman.Game();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        
       //response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession();
        final Object lock = session.getId().intern();
        Hangman.Game game;
        String diff;
        synchronized(lock) {
            game = (Hangman.Game) session.getAttribute("game");
            diff = (String) session.getAttribute("diff");
        }
        Data.History history = null;
        int result = -1;
        boolean valid;
        
        if(diff==null)
        {
            System.out.println("Get difficulty ");
            String check = "true";
            synchronized(lock) {
                session.setAttribute("diff", check);
            }
            getServletContext().getRequestDispatcher("/difficulty.jsp").forward(request,response);
            
        }
        else if (game==null){
            /* validate input */
            String difficulty = request.getParameter("difficulty");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            System.out.print("Check difficulty: ");    
            System.out.println(difficulty);
            if (username == null || password == null && diff != null)
                getServletContext().getRequestDispatcher("/login.jsp").forward(request,response);
            System.out.println("test1 "); 
            java.util.regex.Matcher unameMatch = UNAMEPAT.matcher(username);
            java.util.regex.Matcher pwordMatch = PWORDPAT.matcher(password);
            if (!unameMatch.find() || !pwordMatch.find())
                getServletContext().getRequestDispatcher("/login.jsp").forward(request,response);
            System.out.println("test2 "); 
            username = unameMatch.group(1).toLowerCase();
            password = pwordMatch.group(1);
            valid = UserCred.chkPass(username, password);
            if (!valid)
                getServletContext().getRequestDispatcher("/login.jsp").forward(request,response);

            // this must be a new session, so we will start a new Game 
            //
            System.out.println("Hangman.  New game.");
            game = new Hangman.Game(username);  // start new game.
            synchronized(lock) {
                session.setAttribute("game", game);
                session.setAttribute("diff", diff);
            }
            result=0;
        } else {
            String reset = request.getParameter("reset");
            String logout = request.getParameter("logout");
            String guess = request.getParameter("guess");       
                
            if (reset != null) {
                result = -1;
                game.startNewGame();
            } else if (logout != null) {
                synchronized(lock) {
                    session.invalidate();
                }
                getServletContext().getRequestDispatcher("/login.jsp").forward(request,response);
            } else if (guess == null) {
                    result = 0;
            } else {
                // get user guess           
                java.util.regex.Matcher queryMatch = QUERYPAT.matcher(guess);
                if (!queryMatch.find()) {
                    // Invalid input
                    result = 0;
                } else {
                    // continue with current game
                    // force input to lower case
                    char ch = queryMatch.group(1).toLowerCase().charAt(0);  // letter that user has guessed
                    result = game.playGame(ch);
                }
            }
        }
        System.out.println("Hangman result="+result+" Game="+game.toString());
        switch (result){
            case 1:
                // win
                game.win();
                getServletContext().getRequestDispatcher("/winGame.jsp").forward(request,response);
                break;
            case 3:
                // lose
                game.lose();
                getServletContext().getRequestDispatcher("/loseGame.jsp").forward(request,response);
                break;
            case 0:
            case 2:
            default:
                // continue game
                getServletContext().getRequestDispatcher("/playGame.jsp").forward(request,response);
                break;
        }
        
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        processRequest(request, response);
    } 


    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
