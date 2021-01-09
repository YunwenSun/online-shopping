<%-- 
    Document   : viewItem
    Created on : 2020-12-14, 22:55:18
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
        <h1>Item ${item.name}:</h1>
        
        
        <form:form action="viewItem.htm" method="POST"  >
                
                

                <b>Name : </b> ${item.name}<br/>
                
                <b>Type : </b> ${item.type}<br/>
                
                <b>Price : </b> ${item.price}<br/>
                
                <b>Amount : </b> ${item.currentNum}<br/>
                
                <b>Description : </b> ${item.description}<br/>
                
                <b>Sold Amount : </b> ${item.soldNum}<br/>
                
                <b>Rate : </b> ${item.rate}<br/>
                
                <b>Feedbacks: </b> <br/>
                <c:forEach var="review" items="${viewreviews}">
                    <li> ${review.customer} : ${review.feedback}</li>
                </c:forEach>
                
                <br/> 
            </form:form>
                    <a href="updateStore.htm?name=${item.store}" >Back to your store</a>
    </body>
</html>
