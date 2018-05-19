
<%@page isELIgnored="false" contentType="text/html" pageEncoding="windows-1251"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<html>
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="properties.text" var="locale" scope="session"/>

    <fmt:message bundle="${locale}" key="text.main.local.ru" var="ru_button"/>
    <fmt:message bundle="${locale}" key="text.main.local.en" var="en_button"/>
    <fmt:message bundle="${locale}" key="text.main.local.by" var="by"/>
    <fmt:message bundle="${locale}" key="text.admin.local.profile" var="profile"/>
    <fmt:message bundle="${locale}" key="text.admin.local.date" var="date"/>
    <fmt:message bundle="${locale}" key="text.admin.local.finishdate" var="finishdate"/>
    <fmt:message bundle="${locale}" key="text.admin.local.payments" var="payments"/>
    <fmt:message bundle="${locale}" key="text.admin.local.user" var="userr"/>
    <fmt:message bundle="${locale}" key="text.admin.local.subscribeto" var="subscribeto"/>
    <fmt:message bundle="${locale}" key="text.admin.local.at" var="at"/>
    <fmt:message bundle="${locale}" key="text.admin.local.periods" var="periods"/>
    <fmt:message bundle="${locale}" key="text.admin.local.paid" var="paid"/>
    <fmt:message bundle="${locale}" key="text.admin.local.logout" var="logout"/>
    <head>
        <link rel="stylesheet" type="text/css" href="<c:url value='/css/default.css' />"/>
        <script src="<c:url value='/js/admin.js' />" type="text/javascript"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
        <title>${payments}</title>
    </head>
    <body>
        <div class="header" id="myHeader">
            <div class="left_header"><a href="<c:url value="/controller/main"/>"><img class="img_header" src="<c:url value="/images/jspimages/logo.png"/>"></a></div>
            <div class="center_header_publication">
                <div class="link"><a href="<c:url value="/controller/user"/>">${profile}</a></div>
            </div>
            <div class="header_right">
                <a href="<c:url value="/controller/logout"/>" class="header_href">${logout}</a><br/>
            </div>
        </div>
        <div class="content">
            <table id="payments">
                <th>${date}</th>
                <th>${finishdate}</th>
                <th>${payments}</th>
                    <c:forEach items="${userpayments}" var="userpayments">            
                    <tr>
                        <td><c:out value="${userpayments.date}"/></td>
                        <td><c:out value="${userpayments.finishDate}"/></td>
                        <td> ${subscribeto} <c:out value="${userpayments.publicationName}"/> ${at} <c:out value="${userpayments.subscribtionPeriod}"/> ${periods}. ${paid} <c:out value="${userpayments.amount}"/>. </td>
                    </tr>
                </c:forEach>
            </table>
            <script>
                window.onscroll = function () {
                    myFunction()
                };

                var header = document.getElementById("myHeader");
                var sticky = header.offsetTop;

                function myFunction() {
                    if (window.pageYOffset >= sticky) {
                        header.classList.add("sticky");
                    } else {
                        header.classList.remove("sticky");
                    }
                }
            </script>

        </div>
 <t:footer></t:footer>
    </body>
</html>