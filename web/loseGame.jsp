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
    </head>
    <body>
        <h1>Hangman</h1>
        <img src="h7.gif">
        <br>
        <h2>Sorry. You lose. The word is <%= request.getAttribute("word") %></h2>
    </body>
</html>
