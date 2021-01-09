<%-- 
    Document   : customerAdmin
    Created on : 2020-12-11, 13:25:10
    Author     : Susan Sun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h3>Welcome Customer ${customer.username}:</h3>
        
        <a href="shopping.htm"> Go Shopping</a><br>
        <a href="viewCart.htm?msg="> View your cart</a><br>
        <a href="viewOrderCus.htm"> Orders</a><br>
        <br/>
        <a href="index.htm">Back to Welcome Page</a>
    </body>
</html>
