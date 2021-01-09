<%-- 
    Document   : error
    Created on : Nov 7, 2020, 6:25:22 AM
    Author     : Surya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error</title>
    </head>
    <body>
        <h1>Something went wrong :(</h1>
        ${errormsg}
        <c:forEach var="id" items="${ids}">
                <tr>
                      ${id}              
                </tr>
            </c:forEach>
    </body>
</html>
