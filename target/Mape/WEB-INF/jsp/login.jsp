
<%@page isELIgnored="false" contentType="text/html" pageEncoding="windows-1251"%>
<!DOCTYPE html>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<html lang="en">
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="properties.text" var="locale" scope="session"/>

    <fmt:message bundle="${locale}" key="text.main.local.ru" var="ru_button"/>
    <fmt:message bundle="${locale}" key="text.main.local.en" var="en_button"/>
    <fmt:message bundle="${locale}" key="text.main.local.login" var="login"/>
    <fmt:message bundle="${locale}" key="text.main.local.loginname" var="login_name"/>
    <fmt:message bundle="${locale}" key="text.main.local.loginpassword" var="login_password"/>
    <fmt:message bundle="${locale}" key="text.main.local.loginsubmit" var="login_submit"/>
    <fmt:message bundle="${locale}" key="text.main.local.by" var="by"/>
    <c:if test="${not empty loginbannederror}">
        <fmt:message bundle="${locale}" key="text.main.local.loginbannederror" var="loginerror"/>
    </c:if>
    <c:if test="${not empty loginwrongdataerror}">
        <fmt:message bundle="${locale}" key="text.main.local.loginwrongdataerror" var="loginerror"/>
    </c:if>
    <head>
        <link href="<c:url value='/css/login.css' />" rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
        <title>${login}</title>
    </head>
    <body>
        <c:set var="success" value="55" />
        <c:set var="error" value="66" />
        <c:if test="${not empty loginerror}">
            <div class="success" id="<c:out value="${error}"/>">
                <div class="error-msg">
                    <b>${loginerror}</b><br/><br/>
                    <button class="submit-error" onclick="<c:out value="hide(${error})"/>">Ok</button>
                    <c:remove var="loginerror" scope="session" />
                </div></div>
            </c:if>
        <div id="backgroundd"></div>
        <form name="loginForm" method="POST" action="<c:url value="/controller/login"/>">
            <div class="imgcontainer">
                <img src="<c:url value="/images/jspimages/login_avatar.png"/>" alt="Avatar" class="avatar">
            </div>
            <div class="container">
                ${login_name}<br/>
                <input type="text" name="login" value="" required="true"/>
                <br/>${login_password}<br/>
                <input type="password" name="password" value="" required="true"/>
                <br/>
                <input type="submit" value="${login_submit}" accesskey="enter"/>
            </div>

        </form>
    <t:footer></t:footer>
    <script>
        function show(p) {

            var modal = document.getElementById(p);
            modal.style.display = "block";
        }
        function hide(p) {
            var modal = document.getElementById(p);
            modal.style.display = "none";

        }</script>
</body>
</html>
