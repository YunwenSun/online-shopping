<%-- 
    Document   : shopItem
    Created on : 2020-12-15, 19:25:37
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
        ${msg}
        <form action="shopItem.htm" method="POST" >
        <h2>View Item:</h2>
               
                <b>Name : </b> ${item.name}<br/><br/>
                
                <b>Store : </b> ${item.store}<br/><br/>
                
                <b>Type :  </b> ${item.type}<br/><br/>
                
                <b>Price : </b> ${item.price}<br/><br/>
                
                <b>Amount : </b> ${item.currentNum}<br/><br/>
                
                <b>Description : </b> ${item.description}<br/><br/>
                
                <b>Sold Amount :  </b> ${item.soldNum}<br/><br/>
                
                <b>Rate :  </b> ${item.rate}<br/><br/>
                
                <b>Feedbacks : </b> <br/>
                <c:forEach var="review" items="${viewreviews}">
                    <li>Customer ${review.customer} : ${review.feedback}</li>
                </c:forEach>
                        <br/>
                select num:
                
                <select name="numToAdd">
                    
                    <c:forEach var="i" begin="1" end="${item.currentNum}">
                        <option value ="${i}">${i}</option>
                    </c:forEach>                
                    
                </select>
                
                <br/>
                <input type="submit" value="Add to Cart"/>
                </form>
                <a href="shopping.htm">Back to shopping page</a>
        
        <!--<form action="shopping.htm?">-->
</html>
