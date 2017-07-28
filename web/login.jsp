<%-- 
    Document   : login
    Created on : Jul 25, 2017, 9:50:51 AM
    Author     : swills
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hangman Login</title>
        <link rel="stylesheet" type="text/css" href="hm.css">
    </head>
    <body>
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
        <nav><!-- <a href="#">Register new user</a> --></nav>
    </body>
</html>
