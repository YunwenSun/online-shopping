<%-- 
    Document   : viewOrder
    Created on : 2020-12-16, 16:35:41
    Author     : Susan Sun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <!--<a href="generatePDF.htm"> print<a>-->
        All money in total: ${priceInAll}
        <h1>Your Orders</h1>
        
        <c:forEach var="order" items="${orders}" >
            <!--<form action="createPDF.htm" method="get" >-->
                <!--<input type="hidden" name="item" value="">-->
            <table>
                <tr><b>Order Id: </b> ${order.get(0).id}  </tr>    
                <tr><b>Order Date: </b> ${order.get(0).date} </tr>    
                <tr><b>Total Price: </b> ${order.get(0).totalPrice}  </tr>
                <!--<tr><a href="print.htm"> review</a></td>-->
                <!--<input type="submit" value="print">-->
            <!--customer-->
                <c:if test="${role==1}">
                    
               
            <tr>
                 <b><td></td><td>name</td><td>store</td><td>seller</td><td>price</td><td>num</td><td>action</td></b>
                                 
             </tr>
             
            <c:forEach var="ord" items="${order}">
                <tr>
                    <td><li></td>
                        <td>${ord.item}</td><td>${ord.store}</td><td>${ord.seller}</td><td>${ord.price}</td><td>${ord.num}</td>
                        
                        <td><a href="review.htm?name=${ord.item}&store=${ord.store}"> review</a></td>                                                  
                </tr>
            </c:forEach>
                 </c:if>
            <!--seller-->   
                <c:if test="${role==0}">
                    
               
            <tr>
                 <b><td></td><td>name</td><td>store</td><td>customer</td><td>price</td><td>num</td></b>
                                 
             </tr>
             
            <c:forEach var="ord" items="${order}">
                <tr>
                    <td><li></td>
                        <td>${ord.item}</td><td>${ord.store}</td><td>${ord.customer}</td><td>${ord.price}</td><td>${ord.num}</td>
                        
                                                                          
                </tr>
            </c:forEach>
                 </c:if>
            </table>
            <p></p>
            </br></br>
        </c:forEach>
            <div>
                <c:if test="${role==0}">
            <a href="sellerAdmin.htm">Back to Your Page</a>
                </c:if>
                <c:if test="${role==1}">
            <a href="customerAdmin.htm">Back to Your Page</a>
                </c:if>
            </div>
</html>
