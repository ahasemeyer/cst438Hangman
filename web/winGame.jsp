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
    </head>
    <body>
        <h1>Hangman</h1>
        <% String pictureFile = "h" + request.getAttribute("state") + ".gif";%>
        <img src="<%= pictureFile%>">
        <br>
        <h2>Congratulations! You got it!</h2>
    </body>
</html>