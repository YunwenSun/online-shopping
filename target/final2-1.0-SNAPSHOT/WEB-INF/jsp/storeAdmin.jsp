<%-- 
    Document   : storeAdmin
    Created on : 2020-12-16, 20:49:11
    Author     : Susan Sun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Store Admin</title>
    </head>
    <body>
        <form action="storeAdmin.htm" method="get">
        <h2>Hi ${seller.username} !</h2>
        
        <h3>Manage your stores</h3>
        
        <c:if test="${empty stores}">
            <strong>Empty</strong>
        </c:if>        
        <ul>
            <c:forEach var="store" items="${stores}">
                <li>
                    ${store.name}
                    <a href="updateStore.htm?name=${store.name}">View</a>
                    <a href="removeStore.htm?name=${store.name}">Remove</a>
                </li>
            </c:forEach>
        </ul>
        </form>
        
        <div>
            <h3>Add your store</h3>
            <form:form action="storeAdmin.htm" method="POST" modelAttribute="newStore" >
                
                <form:hidden path="owner" value="${seller.username}"/>
                <br> Store :</br> <form:input path="name"/><br /><br />
                <input type="submit" value="Add Store" />
            </form:form>
        </div>
        <a href="sellerAdmin.htm">Back to Your Page</a>
    </body>
</html>
