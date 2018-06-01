
<%@page isELIgnored="false" contentType="text/html" pageEncoding="windows-1251"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<html>
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="properties.text" var="locale" scope="session"/>


    <fmt:message bundle="${locale}" key="text.user.local.accountmanager" var="accountmanager"/>
    <fmt:message bundle="${locale}" key="text.user.local.changelogin" var="changelogin"/>
    <fmt:message bundle="${locale}" key="text.user.local.oldlogin" var="oldlogin"/>
    <fmt:message bundle="${locale}" key="text.home.local.continuesub" var="continuesub"/>
    <fmt:message bundle="${locale}" key="text.user.local.newlogin" var="newlogin"/>
    <fmt:message bundle="${locale}" key="text.user.local.password" var="password"/>
    <fmt:message bundle="${locale}" key="text.user.local.loginuser" var="loginuser"/>
    <fmt:message bundle="${locale}" key="text.user.local.changelogin" var="changelogin"/>
    <fmt:message bundle="${locale}" key="text.user.local.changepassword" var="changepasswordd"/>
    <fmt:message bundle="${locale}" key="text.user.local.login" var="login"/>
    <fmt:message bundle="${locale}" key="text.user.local.newpassword" var="newpassword"/>
    <fmt:message bundle="${locale}" key="text.user.local.showmypayments" var="showmypayments"/>
    <fmt:message bundle="${locale}" key="text.admin.local.profile" var="profile"/>
    <fmt:message bundle="${locale}" key="text.admin.local.logout" var="logout"/>
    <fmt:message bundle="${locale}" key="text.admin.local.actions" var="actions"/>
    <fmt:message bundle="${locale}" key="text.home.local.subperiods" var="subperiods"/>
    <fmt:message bundle="${locale}" key="text.admin.local.hello" var="hello"/>
    <fmt:message bundle="${locale}" key="text.home.local.findpublications" var="findpublications"/>
    <fmt:message bundle="${locale}" key="text.home.local.showpapers" var="showpapers"/>
    <fmt:message bundle="${locale}" key="text.home.local.showmagazines" var="showmagazines"/>
    <fmt:message bundle="${locale}" key="text.home.local.showbooks" var="showbooks"/>
    <fmt:message bundle="${locale}" key="text.publication.local.onlyletters" var="onlyletters"/>
    <fmt:message bundle="${locale}" key="text.publication.local.loginandpasswordvalidator" var="forloginandpass"/>
    <fmt:message bundle="${locale}" key="text.home.local.showall" var="showall"/>
    <fmt:message bundle="${locale}" key="text.main.local.ru" var="ru_button"/>
    <fmt:message bundle="${locale}" key="text.main.local.en" var="en_button"/>
    <fmt:message bundle="${locale}" key="text.main.local.by" var="by"/>
    <fmt:message bundle="${locale}" key="text.home.local.year" var="year"/>
    <fmt:message bundle="${locale}" key="text.home.local.month" var="month"/>
    <fmt:message bundle="${locale}" key="text.home.local.week" var="week"/>
    <fmt:message bundle="${locale}" key="text.admin.local.magazine" var="magazine"/>
    <fmt:message bundle="${locale}" key="text.admin.local.paper" var="paper"/>
    <fmt:message bundle="${locale}" key="text.home.local.balance" var="balancee"/>
    <c:if test="${not empty userpasswordsuccess}">
        <fmt:message bundle="${locale}" key="text.user.local.userpasswordsuccess" var="usersuccess"/>
    </c:if>
    <c:if test="${not empty userpassworderror}">
        <fmt:message bundle="${locale}" key="text.user.local.userpassworderror" var="usererror"/>
    </c:if>
    <c:if test="${not empty userunsubscribesuccess}">
        <fmt:message bundle="${locale}" key="text.user.local.userunsubscribesuccess" var="usersuccess"/>
    </c:if>
    <c:if test="${not empty userunsubscribeerror}">
        <fmt:message bundle="${locale}" key="text.user.local.userunsubscribeerror" var="usererror"/>
    </c:if>
    <c:if test="${not empty usernamesuccess}">
        <fmt:message bundle="${locale}" key="text.user.local.usernamesuccess" var="usersuccess"/>
    </c:if>
    <c:if test="${not empty usernameerror}">
        <fmt:message bundle="${locale}" key="text.user.local.usernameerror" var="usererror"/>
    </c:if>
    <head>
        <link href="<c:url value='/css/default.css' />" rel="stylesheet" type="text/css"/>
        <script src="<c:url value='/js/admin.js' />" type="text/javascript"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
        <title>${profile}</title>
    </head>
    <body>
        <div class="top-container">
            <p class="line-1 anim-typewriter"><span class="user">${hello} ${user}.</span></p>
            ${loginuser}<br/>
            ${balancee} <b>${balance}</b>
        </div>
        <div class="header" id="myHeader">
            <div class="left_header"><a href="<c:url value="/controller/main"/>"><img class="img_header" src="<c:url value="/images/jspimages/logo.png"/>"></a></div>
            <div class="header_right">
                <a href="<c:url value="/controller/logout"/>" class="header_href"> ${logout}</a><br/>
            </div>
        </div>
        <div class="content">
            <c:set var="success" value="55" />
            <c:set var="error" value="66" />
            <c:if test="${not empty usersuccess}">
                <div class="success" id="<c:out value="${success}"/>">
                    <div class="success-msg">
                        <b>${usersuccess}</b><br/><br/>
                        <button class="submit" onclick="<c:out value="hide(${success})"/>">Ok</button>
                        <c:remove var="usersuccess" scope="session" />
                        <c:remove var="usererror" scope="session" />
                        <c:remove var="userpasswordsuccess" scope="session" />
                        <c:remove var="userpassworderror" scope="session" />
                        <c:remove var="userunsubscribesuccess" scope="session" />
                        <c:remove var="userunsubscribeerror" scope="session" />
                        <c:remove var="usernamesuccess" scope="session" />
                        <c:remove var="usernameerror" scope="session" />
                    </div></div>
                </c:if>
                <c:if test="${not empty usererror}">
                <div class="success" id="<c:out value="${error}"/>">
                    <div class="error-msg">
                        <b>${usererror}</b><br/><br/>
                        <button class="submit-error" onclick="<c:out value="hide(${error})"/>">Ok</button>
                        <c:remove var="usererror" scope="session" />
                        <c:remove var="usersuccess" scope="session" />
                        <c:remove var="userpasswordsuccess" scope="session" />
                        <c:remove var="userpassworderror" scope="session" />
                        <c:remove var="userunsubscribesuccess" scope="session" />
                        <c:remove var="userunsubscribeerror" scope="session" />
                        <c:remove var="usernamesuccess" scope="session" />
                        <c:remove var="usernameerror" scope="session" />
                    </div></div>
                </c:if>
            <div class="pagination-user">
                <c:forEach items="${pagesList}" var="page">
                    <td><a class="action-href" href="<c:url value="/controller/user?page=${page}"/>">${page} </a></td>
                </c:forEach>
            </div>
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
                                <b>${findpublications}</b>
                                <br/>
                            </div>
                            <div class="modal-body">

                                <br/>
                                <form name="deletePublicationForm" method="GET" action="<c:url value="/controller/user"/>">
                                    ${findpublications}:<br/>
                                    <input type="text" name="findpublicationname" value="" required="true" maxlength="45"  pattern="^(?=.{2,30}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$" title="${forloginandpass}"/>
                                    <input type="submit" class="ban_button" value="Find"/>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="publication-content">
                    <br/>
                    <a class="action-href" href="<c:url value="/controller/user?type=paper"/>">${showpapers}</a><br/><br/>
                    <a class="action-href" href="<c:url value="/controller/user?type=magazine"/>">${showmagazines}</a><br/><br/>
                    <a class="action-href" href="<c:url value="/controller/user?type=book"/>">${showbooks}</a><br/><br/>
                    <a class="action-href" href="<c:url value="/controller/user"/>">${showpapers}</a><br/><br/>
                    <u><a onclick="<c:out value="show(${find})"/>" class="action-href">${findpublications}</a></u><br/><br/>
                </div>
            </div>
            <div class="publication-four">
                <div class="publication-header">
                    <br/>
                    <b>${accountmanager}</b>
                    <br/>
                    <br/>
                </div>
                <c:set var="changename" value="322" />
                <c:set var="changepassword" value="1488" />
                <div id="<c:out value="${changename}"/>" class="modal">
                    <div class="modal_helper">
                        <div class="modal-content">
                            <div class="modal-header">
                                <span class="close" onclick="<c:out value="hide(${changename})"/>">&times;</span>
                                <br/>
                                <b>${changelogin}</b>
                                <br/>
                            </div>
                            <div class="modal-body">

                                <br/>
                                <form name="deletePublicationForm" method="POST" action="<c:url value="/controller/changename"/>">
                                    ${oldlogin}
                                    <input type="text" name="oldlogin" value="" required="true"  maxlength="45"  pattern="[a-zA-Z0-9_.-]*$" title="${forloginandpass}"/>
                                    ${newlogin}
                                    <input type="text" name="newlogin" value="" required="true" maxlength="45" pattern="[a-zA-Z0-9_.-]*$" title="${forloginandpass}"/>
                                    ${password}
                                    <input type="password" name="password" value="" required="true" maxlength="45" pattern="[a-zA-Z0-9_.-]*$" title="${forloginandpass}"/>
                                    <input type="submit" class="ban_button" value="Ok"/>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="<c:out value="${changepassword}"/>" class="modal">
                    <div class="modal_helper">
                        <div class="modal-content">
                            <div class="modal-header">
                                <span class="close" onclick="<c:out value="hide(${changepassword})"/>">&times;</span>
                                <br/>
                                <b>${changepasswordd}</b>
                                <br/>
                            </div>
                            <div class="modal-body">

                                <br/>
                                <form name="deletePublicationForm" method="POST" action="<c:url value="/controller/changepassword"/>">
                                    ${login}
                                    <input type="text" name="login" value="" required="true" maxlength="45" pattern="[a-zA-Z0-9_.-]*$" title="${forloginandpass}"/>
                                    ${password}
                                    <input type="password" name="password" value="" required="true" maxlength="45" pattern="[a-zA-Z0-9_.-]*$" title="${forloginandpass}"/>
                                    ${newpassword}
                                    <input type="password" name="newpassword" value="" required="true" maxlength="45" pattern="[a-zA-Z0-9_.-]*$" title="${forloginandpass}"/>
                                    <input type="submit" class="ban_button" value="Ok"/>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="publication-content">
                    <br/>
                    <u><a onclick="<c:out value="show(${changename})"/>" class="action-href">${changelogin}</a></u><br/><br/>
                    <u><a onclick="<c:out value="show(${changepassword})"/>" class="action-href">${changepasswordd}</a></u><br/><br/>
                    <a class="action-href" href="<c:url value="/controller/user/payments"/>">${showmypayments}</a><br/><br/>
                </div>
            </div>
            <div class="panel">
                <c:forEach items="${userpublications}" var="userpublications">
                    <c:if test="${userpublications.type == 'paper'}">
                        <c:set var="type" value="${paper}"/>
                    </c:if>
                    <c:if test="${userpublications.type == 'book'}">
                        <c:set var="type" value="${book}"/>
                    </c:if>
                    <c:if test="${userpublications.type == 'magazine'}">
                        <c:set var="type" value="${magazine}"/>
                    </c:if>

                    <c:if test="${userpublications.periodType == 'year'}">
                        <c:set var="periodtype" value="${year}"/>
                    </c:if>
                    <c:if test="${userpublications.periodType == 'month'}">
                        <c:set var="periodtype" value="${month}"/>
                    </c:if>
                    <c:if test="${userpublications.periodType == 'weak'}">
                        <c:set var="periodtype" value="${week}"/>
                    </c:if>
                    <div class="pricing-plan">
                        <img src="<c:url value="/images/${userpublications.image}"/>" 
                             width="10" height="150" alt="image" class="avatar">
                        <h2 class="pricing-header"><c:out value="${userpublications.name}" /></h2>
                        <ul class="pricing-features">
                            <span class="pricing-features-item"><c:out value="${type}" /></span>
                        </ul>
                        <span class="pricing-price"><c:out value="${userpublications.price}$ / ${periodtype}" /></span>
                        <form name="subsribeForm" method="POST" action="<c:url value="/controller/unsubscribe"/>">
                            <input type="hidden" name="publicationid" value="${userpublications.id}"/> 
                            <input type="submit" value="${continuesub}" class="pricing-button"/>
                        </form>
                    </div>
                </c:forEach>
            </div>
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
