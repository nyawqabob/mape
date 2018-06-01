<%@tag isELIgnored="false" description="Footer" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="personal" uri="/WEB-INF/tld/yeartag.tld" %>

<html lang="en">
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="properties.text" var="locale" scope="session"/>

    <fmt:message bundle="${locale}" key="text.main.local.ru" var="ru_button"/>
    <fmt:message bundle="${locale}" key="text.main.local.en" var="en_button"/>
    <fmt:message bundle="${locale}" key="text.main.local.by" var="by"/>
    <head>
        <link rel="stylesheet" type="text/css" href="<c:url value='/css/footer.css' />"/>
        <script src="<c:url value='/js/admin.js' />" type="text/javascript"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
        <title></title>
    </head>
    <body>
        <div class="footer">
            <div class="left_footer">
                ${by}<br/><a href="https://epam.by" class="footer_href">Epam</a> © <personal:yearTag/>
            </div>
            <div class="right_footer">
                <form id="ru_form" action="<c:url value="/controller/changelang"/>" method="POST">
                    <input type="hidden" name="lang" value="ru">
                    <a class="footer_href" href="javascript:{}" onclick="document.getElementById('ru_form').submit();">${ru_button}</a>
                </form>
                <form id="en_form" action="<c:url value="/controller/changelang"/>" method="POST">
                    <input type="hidden" name="lang" value="en">
                    <a class="footer_href" href="javascript:{}" onclick="document.getElementById('en_form').submit();">${en_button}</a>
                </form>
            </div>
        </div>
    </body>