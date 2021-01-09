<%-- 
    Document   : CustomerLogin
    Created on : 2020-12-8, 20:35:30
    Author     : Susan Sun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer Log in</title>
    </head>
    <body>
        <div>
        <h2> Customer Log in here:</h2>
        <form  method="POST" >
            <h6>${msg}
            <h3> 			
		<input type="radio" name="rol" value="register"/>
                <label>Register</label>			
		<input type="radio" name="rol" value="login"/>
		<label>Log In</label>
                <br/>
            <h3> Username: </h3><input type="text" name= "username"><br/>
            <h3> Password: </h3><input type="password" name= "password"><br/>                        
            <br/>
            
            <input type="submit" value="Submit" >
        </form>
            </div>
            <a href="index.htm">Back to Login Page</a>
    </body>
</html>
