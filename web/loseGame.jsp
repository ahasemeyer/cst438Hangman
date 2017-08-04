<%-- 
    Document   : loseGame
    Created on : Jun 13, 2017, 10:16:13 AM
    Author     : Austin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lose Hangman</title>
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
<c:choose>
    <c:when test="${game == null}">
        <header>
            <form method="post" action="hangmanServlet">
            <fieldset>
                <legend>Login:</legend>
                username: <input type="text" id="name" maxlength="255" pattern="[\w\@_\.-]+"  name="username" /><br/>
                password: <input type="password" id="password" maxlength="127" pattern="[!-~]+" name="password" /><br/>
                <input class="submit" type="submit" name="submit" value="Submit">
            </fieldset>     
            </form>
        </header>
    </c:when>
    <c:otherwise>
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
        <h2>Sorry. You lose. The word is <%= game.getWord() %></h2>
        <form action="hangmanServlet" method="post">
        <fieldset>
            <input type="hidden" name="reset" value="lose">
            <input type="submit" value="Play Again">
        </fieldset>
        </form>
    </c:otherwise>
</c:choose>
    </body>
</html>
