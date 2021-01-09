<%-- 
    Document   : review
    Created on : 2020-12-17, 18:18:32
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
        
        <form:form  method="POST" modelAttribute="reviewmodel">
            
                <h2>Give your review of ${reviewmodel.item}</h2>
                <form:hidden value="${reviewmodel.item}" path="item" />
                <%--<form:hidden value="${reviewmodel.store}" path="store" />--%>
                <form:hidden value="${reviewmodel.customer}" path="customer" />
                Rate:
                <form:select path="rate">
                    
                    <c:forEach var="i" begin="1" end="5">
                        <option value ="${i}">${i}</option>
                    </c:forEach>                
                    
                </form:select>
                <br/>
                Feedback:<form:textarea path="feedback"/><br/>
                
                
                <input type="submit" value="submit">
        </form:form>
    </body>
</html>
