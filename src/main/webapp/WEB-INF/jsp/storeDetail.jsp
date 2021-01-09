<%-- 
    Document   : storeAdmin
    Created on : 2020-12-14, 16:19:23
    Author     : Susan Sun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="updateStore.htm" method="get">
        <h2>This is your store ${store} !</h2>
        
        <h3>Manage your store</h3>
        
        <c:if test="${empty items}">
            <strong>Empty</strong>
        </c:if>
        <br>
       <table>
            <tr>
            <b><td></td><td>Name</td><td>Type</td><td>Price</td><td>Amount</td><td>Action</td></b>
            </tr>
            <c:forEach var="item" items="${items}">
                <tr>
                    <td><li></td>
                        <td>${item.name}</td><td>${item.type}</td><td>${item.price}</td><td>${item.currentNum}</td>
                        <td>
                        <a href="viewItem.htm?name=${item.name}">View</a>
                        <a href="updateItem.htm?name=${item.name}">Update</a>
                        <a href="deleteItem.htm?name=${item.name}">Delete</a>
                        </td>
                    
                </tr>
            </c:forEach>
            
        </table>
        
        </form>
        
        <div>
            <h3>Add an item</h3>
            <a href="addItem.htm" method="GET">Add a new Item</a><br/>
            <a href="storeAdmin.htm" method="GET">Back to All Stores</a>
        </div>
    </body>
</html>
