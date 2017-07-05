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

    //private Hangman.Game game = new Hangman.Game();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        
       //response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession();
        Hangman.Game game = (Hangman.Game)session.getAttribute("game");
        Data.History history = null;
        int result;
        if (game==null){
            // this must be a new session, so we will start a new Game
            System.out.println("Hangman.  New game.");
            game = new Hangman.Game();  // start new game.
            result=0;
        } else {
        // get user guess 
           String input = request.getParameter("guess");
           System.out.println("Hangman. guess="+input);
           if (input==null || input.length() != 1) {
               result=0;
           } else {
             char guess = input.charAt(0);
             result = game.playGame(guess);
           }
        }
        System.out.println("Hangman result="+result+" Game="+game.toString());
        if (result==1){
              // win
              session.invalidate();  // destory the session
              request.setAttribute("displayWord", game.getDisplayWord());
              request.setAttribute("state", Integer.toString(game.getState()));
              //request.setAttribute("")
              game.win();
              getServletContext().getRequestDispatcher("/winGame.jsp").forward(request,response);
        } else if (result==3){
              // lose
              session.invalidate();  // destory the session
              request.setAttribute("word", game.getWord());
              game.lose();
              getServletContext().getRequestDispatcher("/loseGame.jsp").forward(request,response);
        } else {
              // continue game
              session.setAttribute("game", game);
              request.setAttribute("word", game.getWord());
              request.setAttribute("displayWord", game.getDisplayWord());
              request.setAttribute("state", Integer.toString(game.getState()));
              getServletContext().getRequestDispatcher("/playGame.jsp").forward(request,response);
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
