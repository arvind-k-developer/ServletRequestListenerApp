<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>A Web Application</title>
    </head>
    <body>
        <h1>A Welcome Web Application</h1>
        <form method="POST" action="WelcomeServlet">
            <label for="name" title="Enter the name">Name: </label>
            <input type="text" id="txtName" name="txtName"/><br><br>
            <input type="submit" value="Submit"/>
        </form>
    </body>
</html>