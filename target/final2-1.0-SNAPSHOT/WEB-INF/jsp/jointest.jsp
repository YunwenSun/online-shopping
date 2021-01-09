<%-- 
    Document   : jointest
    Created on : 2020-12-16, 1:24:49
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
        <h1>View Cart:</h1>
        <c:forEach var="cart" items="${carts}">
                <tr>
                    <td><li></td>
                        <td>${cart.item}</td><td>${cart.store}</td><td>${cart.num}</td><td>${cart.price}</td>
                                         
                </tr>
            </c:forEach>
    </body>
</html>