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
        <title>Win Hangman</title>
        <link rel="stylesheet" type="text/css" href="hm.css">
    </head>
    <body>
        <nav>
        <form action="hangmanServlet" method="post">
        <fieldset>
            <input type="hidden" name="logout" value="win">
            <input type="submit" value="Logout">
        </fieldset>
        </form>
        </nav>
        <h1>Hangman</h1>
<%
    final Object lock = session.getId().intern();
    Hangman.Game game;
    synchronized(lock) {
        game = (Hangman.Game) session.getAttribute("game");
    }
%>
        <img src="h<%= game.getState() %>.gif">
        <br>
        <h2>Congratulations! You got it!</h2>
        <form action="hangmanServlet" method="post">
        <fieldset>
            <input type="hidden" name="reset" value="win">
            <input type="submit" value="Play Again">
        </fieldset>
        </form>
    </body>
</html>