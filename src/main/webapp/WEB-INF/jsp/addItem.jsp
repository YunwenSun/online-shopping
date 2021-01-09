<%-- 
    Document   : addItem
    Created on : 2020-12-14, 17:45:55
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
        <h1>Add a new Item:</h1>
        
        
        <form:form action="addItem.htm" method="POST" modelAttribute="newItem" >
                
                <form:hidden path="store" value="${store}"/>

                Name : <br/>
                <form:input path="name"/><br />
                Type : <br/>
                <form:radiobutton path="type" value="Food"/>  Food
                <form:radiobutton path="type" value="Medicine"/>  Medicine
                <form:radiobutton path="type" value="Electronic"/>  Electronic
                <form:radiobutton path="type" value="Housing"/>  Housing
                <form:radiobutton path="type" value="Book"/>  Book
                <form:radiobutton path="type" value="Cloth"/>  Cloth
                <form:radiobutton path="type" value="MakeUp"/>  MakeUp
                <form:radiobutton path="type" value="Others"/>Others<br/>
                Price : <br/>
                <form:input path="price"/><br />
                Amount : <br/>
                <form:input path="currentNum"/><br />
                Description : <br/>
                <form:textarea path="description"/><br />
                
                <input type="submit" value="Add Item" />
            </form:form>
    </body>
</html>
