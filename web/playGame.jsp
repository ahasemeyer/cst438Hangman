<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Hangman Index</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="hm.css">
    </head>
    <body>
<%
    final Object lock = session.getId().intern();
    Hangman.Game game;
    synchronized(lock) {
        game = (Hangman.Game) session.getAttribute("game");
    }
%>
        <nav>
        <form action="hangmanServlet" method="post">
        <fieldset>
            <input type="hidden" name="logout" value="play">
            <input type="submit" value="Logout">
        </fieldset>
        </form>
        </nav>
        <div><h1>Hangman</h1></div>
        <img src="h<%= game.getState() %>.gif" /><br><br>
        <h2> <%= game.getDisplayWord() %></h2>
        <label>Guess a character</label>
        <form action="hangmanServlet" method="post">
        <fieldset>
            <input type="text" name="guess" maxlength="1" pattern="[\w]"><br><br>
            <input type="submit" value="Submit Letter">
        </fieldset>
        </form>
    </body>
</html>

