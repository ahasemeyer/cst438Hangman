<%-- 
    Document   : loseGame
    Created on : Jun 13, 2017, 10:16:13 AM
    Author     : Austin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lose Hangman</title>
        <link rel="stylesheet" type="text/css" href="hm.css">
    </head>
    <body>
        <nav>
        <form action="hangmanServlet" method="post">
        <fieldset>
            <input type="hidden" name="logout" value="lose">
            <input type="submit" value="Logout">
        </fieldset>
        </form>
        </nav>
        <h1>Hangman</h1>
        <img src="h7.gif">
        <br>
<%
    final Object lock = session.getId().intern();
    Hangman.Game game;
    synchronized(lock) {
        game = (Hangman.Game) session.getAttribute("game");
    }
%>
        <h2>Sorry. You lose. The word is <%= game.getWord() %></h2>
        <form action="hangmanServlet" method="post">
        <fieldset>
            <input type="hidden" name="reset" value="lose">
            <input type="submit" value="Play Again">
        </fieldset>
        </form>
    </body>
</html>
