
<%@page isELIgnored="false" contentType="text/html" pageEncoding="windows-1251"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<html lang="en">
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="properties.text" var="locale" scope="session"/>

    <fmt:message bundle="${locale}" key="text.main.local.ru" var="ru_button"/>
    <fmt:message bundle="${locale}" key="text.main.local.en" var="en_button"/>
    <fmt:message bundle="${locale}" key="text.main.local.by" var="by"/>
    <fmt:message bundle="${locale}" key="text.admin.local.profile" var="profile"/>
    <fmt:message bundle="${locale}" key="text.admin.local.loginadmin" var="loginadmin"/>
    <fmt:message bundle="${locale}" key="text.admin.local.systembalance" var="systemamount"/>
    <fmt:message bundle="${locale}" key="text.admin.local.actions" var="actions"/>
    <fmt:message bundle="${locale}" key="text.admin.local.findpayments" var="findpayments"/>
    <fmt:message bundle="${locale}" key="text.admin.local.showpayments" var="showpayments"/>
    <fmt:message bundle="${locale}" key="text.admin.local.usermanager" var="usermanager"/>
    <fmt:message bundle="${locale}" key="text.admin.local.publicationmanager" var="publicationmanager"/>
    <fmt:message bundle="${locale}" key="text.admin.local.date" var="date"/>
    <fmt:message bundle="${locale}" key="text.admin.local.finishdate" var="finishdate"/>
    <fmt:message bundle="${locale}" key="text.admin.local.payments" var="payments"/>
    <fmt:message bundle="${locale}" key="text.admin.local.user" var="userr"/>
    <fmt:message bundle="${locale}" key="text.admin.local.subscribeto" var="subscribeto"/>
    <fmt:message bundle="${locale}" key="text.admin.local.at" var="at"/>
    <fmt:message bundle="${locale}" key="text.admin.local.periods" var="periods"/>
    <fmt:message bundle="${locale}" key="text.admin.local.paid" var="paid"/>
    <fmt:message bundle="${locale}" key="text.admin.local.logout" var="logout"/>
    <fmt:message bundle="${locale}" key="text.admin.local.manager" var="manager"/>
    <fmt:message bundle="${locale}" key="text.admin.local.hello" var="hello"/>
    <fmt:message bundle="${locale}" key="text.main.local.by" var="by"/>
    <fmt:message bundle="${locale}" key="text.users.local.find" var="findd"/>
    <head>
        <link rel="stylesheet" type="text/css" href="<c:url value='/css/default.css' />"/>

        <script src="<c:url value='/js/admin.js' />" type="text/javascript"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
        <title>${profile}</title>
    </head>
    <body>
        
            <div class="top-container">
                <p class="line-1 anim-typewriter"> <span class="user">${hello} ${user}.</span><br/></p>
                ${loginadmin}<br/>
                ${systemamount} <b>${systembalance}</b>
                <c:set var="success" value="55" />
                <c:set var="error" value="66" />
                <c:if test="${not empty adminsuccess}">
                    <div class="success" id="<c:out value="${success}"/>">
                        <div class="success-msg">
                            <b>${adminsuccess}</b><br/><br/>
                            <button class="submit" onclick="<c:out value="hide(${success})"/>">Ok</button>
                            <c:remove var="adminsuccess" scope="session" />
                        </div></div>
                    </c:if>
                    <c:if test="${not empty adminerror
                          }">
                    <div class="success" id="<c:out value="${error}"/>">
                        <div class="error-msg">
                            <b>${adminerror
                                }</b><br/><br/>
                            <button class="submit-error" onclick="<c:out value="hide(${error})"/>">Ok</button>
                            <c:remove var="adminerror
                                      " scope="session" />
                        </div></div>
                    </c:if>
            </div>
            <div class="header" id="myHeader">
                <div class="left_header"><a href="<c:url value="/controller/main"/>"><img class="img_header" src="<c:url value="/images/jspimages/logo.png"/>"></a></div>
                <div class="header_right">
                    <a href="<c:url value="/controller/logout"/>" class="header_href">${logout}</a><br/>
                </div>
            </div>
            <div class="content">
                <div class="publication">
                    <div class="publication-header">
                        <br/>
                        <b>${actions}</b>
                        <br/>
                        <br/>
                    </div>
                    <c:set var="find" value="1337" />
                    <div id="<c:out value="${find}"/>" class="modal">
                        <div class="modal_helper">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <span class="close" onclick="<c:out value="hide(${find})"/>">&times;</span>

                                    <br/>
                                    <b>${findpayments}</b>
                                    <br/>
                                </div>
                                <div class="modal-body">

                                    <br/>
                                    <form name="deletePublicationForm" method="GET" action="<c:url value="/controller/admin"/>">
                                        ${findpayments}<br/>
                                        <input type="text" name="findpaymentname" value="" required="true"/><br/>
                                        <input type="submit" class="submit" value="${findd}"/>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="publication-content">
                        <br/>
                        <u><a onclick="<c:out value="show(${find})"/>" class="action-href">${findpayments}</a></u><br/><br/>
                        <a class="action-href" href="<c:url value="/controller/admin"/>">${showpayments}</a><br/><br/>
                    </div>
                </div>
                <div class="publication-two">
                    <div class="publication-header">
                        <br/>
                        <b>${manager}</b>
                        <br/>
                        <br/>
                    </div>
                    <div class="publication-content">
                        <br/>
                        <a class="action-href" href="<c:url value="/controller/admin/users"/>">${usermanager}</a><br/><br/>
                        <a class="action-href" href="<c:url value="/controller/admin/publications"/>">${publicationmanager}</a><br/><br/>
                    </div>
                </div>
                <table id="users">
                    <th>${date}</th>
                    <th>${finishdate}</th>
                    <th>${payments}</th>
                        <c:forEach items="${allpayments}" var="allpayments">            
                        <tr>
                            <td><c:out value="${allpayments.date}"/></td>
                            <td><c:out value="${allpayments.finishDate}"/></td>
                            <td>${userr} <c:out value="${allpayments.userId}"/> ${subscribeto} <c:out value="${allpayments.publicationName}"/> ${at} <c:out value="${allpayments.subscribtionPeriod}"/> ${periods}. ${paid} <c:out value="${allpayments.amount}"/></td>
                        </tr>
                    </c:forEach>
                </table>
                <script>
                    function show(p) {

                        var modal = document.getElementById(p);
                        modal.style.display = "block";
                    }
                    function hide(p) {
                        var modal = document.getElementById(p);
                        modal.style.display = "none";

                    }
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
