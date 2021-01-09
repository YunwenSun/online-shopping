<%-- 
    Document   : sellerAdmin
    Created on : 2020-12-11, 13:25:20
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
        <div>
        <h2>Welcome Seller ${seller.username}:</h2>
               
        <a href="storeAdmin.htm"> Manage your store</a><br>
        <a href="viewOrderSell.htm"> View your Orders</a><br>
        <br/>
        <a href="index.htm">Back to Login Page</a><br/>
        </div>
    </body>
</html>
