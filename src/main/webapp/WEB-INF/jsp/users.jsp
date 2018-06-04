
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
    <fmt:message bundle="${locale}" key="text.admin.local.username" var="username"/>
    <fmt:message bundle="${locale}" key="text.admin.local.profile" var="profile"/>
    <fmt:message bundle="${locale}" key="text.users.local.ban" var="ban"/>
    <fmt:message bundle="${locale}" key="text.users.local.unban" var="unban"/>
    <fmt:message bundle="${locale}" key="text.users.local.change" var="change"/>
    <fmt:message bundle="${locale}" key="text.users.local.name" var="name"/>
    <fmt:message bundle="${locale}" key="text.users.local.status" var="status"/>
    <fmt:message bundle="${locale}" key="text.users.local.find" var="findd"/>
    <fmt:message bundle="${locale}" key="text.users.local.notbanned" var="notbanned"/>
    <fmt:message bundle="${locale}" key="text.users.local.banned" var="banned"/>
    <fmt:message bundle="${locale}" key="text.users.local.finduser" var="finduser"/>
    <fmt:message bundle="${locale}" key="text.users.local.showusers" var="showusers"/>
    <fmt:message bundle="${locale}" key="text.users.local.changebalance" var="changebalance"/>
    <fmt:message bundle="${locale}" key="text.users.local.action" var="action"/>
    <fmt:message bundle="${locale}" key="text.admin.local.actions" var="actions"/>
    <fmt:message bundle="${locale}" key="text.publication.local.onlynumber" var="onlynumber"/>
    <fmt:message bundle="${locale}" key="text.publication.local.onlyletters" var="onlyletters"/>
    <fmt:message bundle="${locale}" key="text.publication.local.loginandpasswordvalidator" var="forloginandpass"/>
    <fmt:message bundle="${locale}" key="text.admin.local.logout" var="logout"/>
    <fmt:message bundle="${locale}" key="text.home.local.balance" var="balancee"/>
    <fmt:message bundle="${locale}" key="text.admin.local.usermanager" var="usermanager"/>
    <fmt:message bundle="${locale}" key="text.user.local.changelogin" var="changelogin"/>
    <c:if test="${not empty usersuccessbanned}">
        <fmt:message bundle="${locale}" key="text.users.local.usersuccessbanned" var="usersuccess"/>
    </c:if>
    <c:if test="${not empty usererrorbanned}">
        <fmt:message bundle="${locale}" key="text.users.local.usererrorbanned" var="usererror"/>
    </c:if>
     <c:if test="${not empty userunbansuccess}">
        <fmt:message bundle="${locale}" key="text.users.local.userunbansuccess" var="usersuccess"/>
    </c:if>
    <c:if test="${not empty userunbanerror}">
        <fmt:message bundle="${locale}" key="text.users.local.userunbanerror" var="usererror"/>
    </c:if>
    <c:if test="${not empty userbalancesuccess}">
        <fmt:message bundle="${locale}" key="text.users.local.userbalancesuccess" var="usersuccess"/>
    </c:if>
    <c:if test="${not empty userbalanceerror}">
        <fmt:message bundle="${locale}" key="text.users.local.userbalanceerror" var="usererror"/>
    </c:if>
     <c:if test="${not empty usernamesuccess}">
        <fmt:message bundle="${locale}" key="text.users.local.usernamesuccess" var="usersuccess"/>
    </c:if>
    <c:if test="${not empty usernameerror}">
        <fmt:message bundle="${locale}" key="text.users.local.usernameerror" var="usererror"/>
    </c:if>
    <head>
        <link rel="stylesheet" type="text/css" href="<c:url value='../../css/default.css' />"/>
        <script src="<c:url value='/js/admin.js' />" type="text/javascript"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
        <title>${usermanager}</title>
    </head>
    <body>
        <c:set var="success" value="55" />
        <c:set var="error" value="66" />
        <c:if test="${not empty usersuccess}">
            <div class="success" id="<c:out value="${success}"/>">
                <div class="success-msg">
                    <b>${usersuccess}</b><br/><br/>
                    <button class="submit" onclick="<c:out value="hide(${success})"/>">Ok</button>
                    <c:remove var="usersuccess" scope="session" />
                    <c:remove var="usererror" scope="session" />
                    <c:remove var="usersuccessbanned" scope="session" />
                    <c:remove var="usererrorbanned" scope="session" />
                    <c:remove var="userunbansuccess" scope="session" />
                    <c:remove var="userbalancesuccess" scope="session" />
                    <c:remove var="userbalanceerror" scope="session" />
                    <c:remove var="usernamesuccess" scope="session" />
                    <c:remove var="usernameerror" scope="session" />
                </div></div>
            </c:if>
            <c:if test="${not empty usererror}">
            <div class="success" id="<c:out value="${error}"/>">
                <div class="error-msg">
                    <b>${usererror}</b><br/><br/>
                    <button class="submit-error" onclick="<c:out value="hide(${error})"/>">Ok</button>
                     <c:remove var="usersuccess" scope="session" />
                    <c:remove var="usererror" scope="session" />
                    <c:remove var="usersuccessbanned" scope="session" />
                    <c:remove var="usererrorbanned" scope="session" />
                    <c:remove var="userunbansuccess" scope="session" />
                    <c:remove var="userunbanerror" scope="session" />
                    <c:remove var="userbalancesuccess" scope="session" />
                    <c:remove var="userbalanceerror" scope="session" />
                    <c:remove var="usernamesuccess" scope="session" />
                    <c:remove var="usernameerror" scope="session" />
                </div></div>
            </c:if>
        <div class="header" id="myHeader">
            <div class="left_header"><a href="<c:url value="/controller/main?type=all"/>"><img class="img_header" src="<c:url value="/images/jspimages/logo.png"/>"></a></div>
            <div class="center_header_publication">
                <div class="link"><a href="<c:url value="/controller/admin"/>">${profile}</a></div>
            </div>
            <div class="header_right">
                <a href="<c:url value="/controller/logout"/>" class="header_href"> ${logout}</a><br/>
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
                <c:set var="add" value="322" />
                <c:set var="delete" value="228" />
                <c:set var="find" value="1337" />
                <div id="<c:out value="${find}"/>" class="modal">
                    <div class="modal_helper">
                        <div class="modal-content">
                            <div class="modal-header">
                                <span class="close" onclick="<c:out value="hide(${find})"/>">&times;</span>
                                <br/>
                                <b>${finduser}</b>
                                <br/>
                            </div>
                            <div class="modal-body"
                                 <br/>
                                <form name="deletePublicationForm" method="GET" action="<c:url value="/controller/admin/users"/>">
                                    ${username}:<br/>
                                    <input type="text" name="findusername" value="" required="true" maxlength="45"  pattern="[a-zA-Z0-9_.-]*$" title="${forloginandpass}"/>
                                    <input type="submit" class="submit" value="${findd}"/>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="publication-content">
                    <br/>
                    <u><a onclick="<c:out value="show(${find})"/>" class="action-href">${finduser}</a></u><br/><br/>
                    <a class="action-href" href="<c:url value="/controller/admin/users"/>">${showusers}</a><br/><br/>

                </div>
            </div>
            <table id="users">
                <tr>
                    <th>${name}</th>
                    <th>${balancee}</th>
                    <th title="1 - banned, 0 - not banned">${status}</th>
                    <th align="center" valign="middle" colspan="2">${action}</th>
                </tr>
                <c:set var="p" value="1" />
                <c:forEach items="${allusers}" var="allusers">
                    <div id="<c:out value="${p}"/>" class="modal">
                        <div class="modal_helper">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <span class="close" onclick="<c:out value="hide(${p})"/>">&times;</span>
                                    <br/>
                                    <b>${changelogin} ${allusers.name}</b>
                                    <br/>
                                </div>
                                <div class="modal-body">
                                    <form id="delete_form" method="POST" action="<c:url value="/controller/adminchangename"/>">
                                        <input type="hidden" name="login" value="${allusers.name}"/>
                                        <input type="text" maxlength="45" name="newlogin" value="" required="true" maxlength="45"  pattern="[a-zA-Z0-9_.-]*$" title="${forloginandpass}"/>
                                        <input type="submit" value="Ok" class="submit"></form>
                                </div>

                            </div>
                        </div>
                    </div>
                    <div id="<c:out value="${p}${p}"/>" class="modal">
                        <div class="modal_helper">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <span class="close" onclick="<c:out value="hide(${p}${p})"/>">&times;</span>
                                    <br/>
                                    <b>${changebalance} ${allusers.name}</b>
                                    <br/>
                                </div>
                                <div class="modal-body">
                                    <form id="delete_form" method="POST" action="<c:url value="/controller/changebalance"/>">
                                        <input type="hidden" name="login" value="${allusers.name}"/>
                                        <input type="text" maxlength="45" name="newbalance" value="" required="true" maxlength="45"  pattern="^[ 0-9]+$" title="${onlynumber}"/>
                                        <input type="submit" value="Ok" class="submit"></form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <tr>
                        <c:if test="${allusers.status == 'banned'}">
                            <c:set var="status" value="${banned}"/>
                        </c:if>
                        <c:if test="${allusers.status == 'not_banned'}">
                            <c:set var="status" value="${notbanned}"/>
                        </c:if>
                        <td><c:out value="${allusers.name}" /><br/><button onclick="<c:out value="show(${p})"/>" class="table_button">${change}</button>  
                        <td><c:out value="${allusers.balance}" /><br/><button onclick="<c:out value="show(${p}${p})"/>" class="table_button">${change}</button> 
                        <td><c:out value="${status}" />
                        <td><form id="ban_form" action="<c:url value="/controller/ban"/>" method="POST">
                                <input type="hidden" name="command" value="banuser">
                                <input type="hidden" name="login" value="${allusers.name}">
                                <input type="submit" value="${ban}" class="submit">
                            </form></td>
                        <td><form id="unban_form" method="POST" action="<c:url value="/controller/unban"/>">
                                <input type="hidden" name="login" value="${allusers.name}"/>
                                <input type="hidden" name="command" value="unbanuser"/>
                                <input type="submit" value="${unban}" class="submit">
                            </form></td>
                    </tr>
                    <c:set var="p" value="${p+1}"/>
                </c:forEach>
            </table>
            <div class="pagination-admin-users">
                <c:forEach items="${pagesList}" var="page">
                    <td><a class="action-href" href="<c:url value="/controller/admin/users?page=${page}"/>">${page} </a></td>
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
