<%-- 
    Document   : LoginPage
    Created on : 2020-12-8, 15:16:06
    Author     : Susan Sun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Seller Log in</title>
    </head>
    <body>
        <h2> Seller Log in here:</h2>
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
            <a href="index.htm">Back to Login Page</a>
    </body>
</html>
