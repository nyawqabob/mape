<%-- 
    Document   : error
    Created on : 13.05.2018, 21:23:24
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="windows-1251"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="<c:url value='/css/default.css' />"/>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
        <title>404</title>
    </head>
    <body>
        <div class="errorpagediv">
            <span class="errorpage">404</span><br/>
            <a class="pricing-button" href="<c:url value="/controller/main?type=all"/>">Go to the main page</a></div>
    </body>
</html>
