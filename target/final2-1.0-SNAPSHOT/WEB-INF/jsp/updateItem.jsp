<%-- 
    Document   : updateItem
    Created on : 2020-12-14, 22:55:30
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
    
    ${msg}
    <body>
        <h2>Update Item ${oldItem.name}:</h2>
        

        
        <form:form action="updateItem.htm" method="POST" modelAttribute="oldItem">
                
                <form:hidden path="store" value="${oldItem.store}"/>
                <form:hidden path="name" value="${oldItem.name}"/>
                <form:hidden path="type" value="${oldItem.type}"/>
                <form:hidden path="rate" value="${oldItem.rate}"/>
                <form:hidden path="soldNum" value="${oldItem.soldNum}"/>
                <b>Name : </b> ${oldItem.name}<br/><br/>
                
                <b>Type : </b> ${oldItem.type}<br/><br/>
                               
                <b>Rate : </b> ${oldItem.rate}<br/><br/>
                
                <b>Sold Amount : </b> ${oldItem.soldNum}<br/><br/>
                
                <b>Price : </b> 
                <form:input path="price"/><br /><br/>
                <b>Amount : </b> 
                <form:input path="currentNum"/><br /><br/>
                <b>Description : </b> <br/>                
                <form:textarea path="description"/><br /><br/>
                
                
                <input type="submit" value="Update" />
            </form:form>
                <br/>
                <a href="updateStore.htm?name=${item.store}" >Back to your store</a>
    </body>
</html>
