<%-- 
    Document   : shopping
    Created on : 2020-12-15, 16:05:27
    Author     : Susan Sun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="shopping.htm" method="POST" >
        Key Word: <input type="text" name="keyWord" ><br/>
        
                <input type="radio" name="searchKey" value="name"/>
                <label>by Name</label>			
		<input type="radio" name="searchKey" value="store"/>
		<label>by Store</label>
                <input type="radio" name="searchKey" value="type"/>
		<label>by Type</label>
                <br/>
                <input type="submit" value="search">
        </form>
        
        <table>
            <br/>
            <br/>
            <tr>
                 <td></td><td><b>name</b></td><td><b>type</b></td><td><b>store</b></td><td><b>price</b></td><td><b>action</b></td>
             </tr>
                <c:forEach var="result" items="${searchResults}">
                <tr>
                    <td><li></td>
                        <td>${result.name}</td><td>${result.type}</td><td>${result.store}</td><td>${result.price}</td>
                        <td>
                            <a href="shopItem.htm?name=${result.name}&store=${result.store}">View</a>             
                        </td>                   
                </tr>
            </c:forEach>
                
        </table> <br/>       
                        <a href="viewCart.htm?msg="> View your cart</a><br>
                        <a href="customerAdmin.htm"> Back to your page</a><br>
    </body>
</html>
