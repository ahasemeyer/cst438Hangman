<%-- 
    Document   : difficulty
    Created on : Aug 5, 2017, 7:40:31 PM
    Author     : hasmy
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hangman Difficulty</title>
        <link rel="stylesheet" type="text/css" href="hm.css">
    </head>
    <body>
        <div><h1>Choose Difficulty Level</h1></div>
        <header>
            <form method="post" action="hangmanServlet">
                <fieldset>
                    <input type="radio" name="difficulty" value="easy" checked>
                    Easy<br>
                    <input type="radio" name="difficulty" value="medium">
                    Medium<br>
                    <input type="radio" name="difficulty" value="hard">
                    Hard<br>
                    <!--test <input type="text" id="name" maxlength="255" pattern="[\w\@_\.-]+"  name="test" /><br/-->
                    <input type="submit" value="Enter">
                </fieldset>
            </form>
        </header>
    </body>
</html>

