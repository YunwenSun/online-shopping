<%-- 
    Document   : viewCart
    Created on : 2020-12-16, 1:07:14
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
        <form method="POST">
        <h2>View Cart:</h2>
         <table>
             <c:if test="${empty carts}">
            <strong>Empty</strong>
            </c:if>
            
             <c:if test="${!empty carts}">
        
             <tr>
             <b><td></td><td>name</td><td>store</td><td>price</td><td>num</td><td>action</td></b>
             </tr>
        <c:forEach var="cart" items="${carts}">
            <form>
               
                <tr>
                    <td><li></td>
                        <td>${cart.item}</td><td>${cart.store}</td><td>${cart.price}</td><td>${cart.num}</td>
                        <td>
                            <a href="addNum.htm?name=${cart.item}&store=${cart.store}">+1</a>
                            <a href="minusNum.htm?name=${cart.item}&store=${cart.store}">-1</a>                           
                            <a href="removeNum.htm?name=${cart.item}&store=${cart.store}">Remove</a>
                        </td>                   
                </tr>
                 </form>

            </c:forEach>
             </table><br/>
                <c:if test="${!empty carts}">
                    <strong><b>Total Price: </b> ${carts.get(0).totalPrice}</strong>
                </c:if>
             
        </c:if>
                <input type="submit" value="check out">
        </form>
        <br/><br/>
        <a href="shopping.htm">Back to shopping</a><br/>
        <a href="customerAdmin.htm">Back to Your page</a>
    </body>       
</html>
