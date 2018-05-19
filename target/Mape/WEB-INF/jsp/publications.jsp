
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
    <fmt:message bundle="${locale}" key="text.admin.local.publicationmanager" var="publicationmanager"/>
    <fmt:message bundle="${locale}" key="text.users.local.action" var="action"/>
    <fmt:message bundle="${locale}" key="text.admin.local.profile" var="profile"/>
    <fmt:message bundle="${locale}" key="text.admin.local.actions" var="actions"/>
    <fmt:message bundle="${locale}" key="text.home.local.showpapers" var="showpapers"/>
    <fmt:message bundle="${locale}" key="text.home.local.showmagazines" var="showmagazines"/>
    <fmt:message bundle="${locale}" key="text.home.local.showbooks" var="showbooks"/>
    <fmt:message bundle="${locale}" key="text.home.local.showall" var="showall"/>
    <fmt:message bundle="${locale}" key="text.admin.local.logout" var="logout"/>
    <fmt:message bundle="${locale}" key="text.users.local.find" var="findd"/>
    <fmt:message bundle="${locale}" key="text.users.local.change" var="change"/>
    <fmt:message bundle="${locale}" key="text.users.local.name" var="name"/>
    <fmt:message bundle="${locale}" key="text.home.local.findpublications" var="findpublications"/>
    <fmt:message bundle="${locale}" key="text.admin.local.magazine" var="magazine"/>
    <fmt:message bundle="${locale}" key="text.admin.local.paper" var="paper"/>
    <fmt:message bundle="${locale}" key="text.admin.local.book" var="book"/>
    <fmt:message bundle="${locale}" key="text.home.local.year" var="year"/>
    <fmt:message bundle="${locale}" key="text.home.local.month" var="month"/>
    <fmt:message bundle="${locale}" key="text.home.local.week" var="week"/>
    <fmt:message bundle="${locale}" key="text.publication.local.addpubl" var="addnewpubl"/>
    <fmt:message bundle="${locale}" key="text.publication.local.onlynumber" var="onlynumber"/>
    <fmt:message bundle="${locale}" key="text.publication.local.newpublname" var="newpublname"/>
    <fmt:message bundle="${locale}" key="text.publication.local.newpublimage" var="newpublimage"/>
    <fmt:message bundle="${locale}" key="text.publication.local.newpublprice" var="newpublprice"/>
    <fmt:message bundle="${locale}" key="text.publication.local.newpubltype" var="newpubltype"/>
    <fmt:message bundle="${locale}" key="text.publication.local.newpublperiodtype" var="newpublperiodtype"/>
    <fmt:message bundle="${locale}" key="text.publication.local.changename" var="changename"/>
    <fmt:message bundle="${locale}" key="text.publication.local.changeprice" var="changeprice"/>
    <fmt:message bundle="${locale}" key="text.publication.local.changetype" var="changetype"/>
    <fmt:message bundle="${locale}" key="text.publication.local.delete" var="deletee"/>
    <fmt:message bundle="${locale}" key="text.publication.local.image" var="image"/>
    <fmt:message bundle="${locale}" key="text.publication.local.price" var="price"/>
    <fmt:message bundle="${locale}" key="text.publication.local.type" var="typee"/>
    <c:if test="${not empty publicationtypesuccess}">
        <fmt:message bundle="${locale}" key="text.publication.local.publicationtypesuccess" var="publicationsuccess"/>
    </c:if>
    <c:if test="${not empty publicationtypeerror}">
        <fmt:message bundle="${locale}" key="text.publication.local.publicationtypeerror" var="publicationerror"/>
    </c:if>
    <c:if test="${not empty publicationpricesuccess}">
        <fmt:message bundle="${locale}" key="text.publication.local.publicationpricesuccess" var="publicationsuccess"/>
    </c:if>
    <c:if test="${not empty publicationpriceerror}">
        <fmt:message bundle="${locale}" key="text.publication.local.publicationpriceerror" var="publicationerror"/>
    </c:if>
    <c:if test="${not empty publicationnamesuccess}">
        <fmt:message bundle="${locale}" key="text.publication.local.publicationnamesuccess" var="publicationsuccess"/>
    </c:if>
    <c:if test="${not empty publicationnameeerror}">
        <fmt:message bundle="${locale}" key="text.publication.local.publicationnameerror" var="publicationerror"/>
    </c:if>
    <c:if test="${not empty publicationdeletesuccess}">
        <fmt:message bundle="${locale}" key="text.publication.local.publicationdeletesuccess" var="publicationsuccess"/>
    </c:if>
    <c:if test="${not empty publicationdeleteerror}">
        <fmt:message bundle="${locale}" key="text.publication.local.publicationdeleteerror" var="publicationerror"/>
    </c:if>
    <c:if test="${not empty publicationaddsuccess}">
        <fmt:message bundle="${locale}" key="text.publication.local.publicationaddsuccess" var="publicationsuccess"/>
    </c:if>
    <c:if test="${not empty publicationadderror}">
        <fmt:message bundle="${locale}" key="text.publication.local.publicationadderror" var="publicationerror"/>
    </c:if>
    <head>
        <link rel="stylesheet" type="text/css" href="<c:url value='../../css/default.css' />"/>
        <script src="<c:url value='/js/admin.js' />" type="text/javascript"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
        <title>${publicationmanager}</title>
    </head>
    <body>
        <c:set var="success" value="55" />
        <c:set var="error" value="66" />
        <c:if test="${not empty publicationsuccess}">
            <div class="success" id="<c:out value="${success}"/>">
                <div class="success-msg">
                    <b>${publicationsuccess}</b><br/><br/>
                    <button class="submit" onclick="<c:out value="hide(${success})"/>">Ok</button>
                    <c:remove var="publicationsuccess" scope="session" />
                    <c:remove var="publicationerror" scope="session" />
                    <c:remove var="publicationtypesuccess" scope="session" />
                    <c:remove var="publicationtypeerror" scope="session" />
                    <c:remove var="publicationpricesuccess" scope="session" />
                    <c:remove var="publicationpriceerror" scope="session" />
                    <c:remove var="publicationnamesuccess" scope="session" />
                    <c:remove var="publicationnameerror" scope="session" />
                    <c:remove var="publicationdeletesuccess" scope="session" />
                    <c:remove var="publicationdeleteerror" scope="session" />
                    <c:remove var="publicationaddsuccess" scope="session" />
                    <c:remove var="publicationadderror" scope="session" />
                </div></div>
            </c:if>
            <c:if test="${not empty publicationerror}">
            <div class="success" id="<c:out value="${error}"/>">
                <div class="error-msg">
                    <b>${publicationerror}</b><br/><br/>
                    <button class="submit-error" onclick="<c:out value="hide(${error})"/>">Ok</button>
                    <c:remove var="publicationerror" scope="session" />
                    <c:remove var="publicationsuccess" scope="session" />
                    <c:remove var="publicationtypesuccess" scope="session" />
                    <c:remove var="publicationtypeerror" scope="session" />
                    <c:remove var="publicationpricesuccess" scope="session" />
                    <c:remove var="publicationpriceerror" scope="session" />
                    <c:remove var="publicationnamesuccess" scope="session" />
                    <c:remove var="publicationnameerror" scope="session" />
                    <c:remove var="publicationdeletesuccess" scope="session" />
                    <c:remove var="publicationdeleteerror" scope="session" />
                    <c:remove var="publicationaddsuccess" scope="session" />
                    <c:remove var="publicationadderror" scope="session" />
                </div></div>
            </c:if>
        <div class="header" id="myHeader">
            <div class="left_header"><a href="<c:url value="/controller/main"/>"><img class="img_header" src="<c:url value="/images/jspimages/logo.png"/>"></a></div>
            <div class="center_header_publication">
                <div class="link"><a href="<c:url value="/controller/admin"/>">${profile}</a></div>
            </div>
            <div class="header_right">
                <a href="<c:url value="/controller/logout"/>" class="header_href">${logout}</a><br/>
            </div>
        </div>
        <div class="content">
            <div class="pagination-admin-publications">
                <c:forEach items="${pagesList}" var="page">
                    <td><a class="action-href" href="<c:url value="/controller/admin/publications?page=${page}"/>">${page} </a></td>
                </c:forEach>
            </div>
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
                <div id="<c:out value="${add}"/>" class="modal">
                    <div class="modal_helper">
                        <div class="modal-content">
                            <div class="modal-header">
                                <span class="close" onclick="<c:out value="hide(${add})"/>">&times;</span>
                                <br/>
                                <b>${addpubl}</b>
                                <br/>
                            </div>
                            <div class="modal-body">
                                <form name="addPublicationForm" method="POST" action="<c:url value="/controller/add"/>" enctype="multipart/form-data">
                                    <input type="hidden" name="command" value="addpublication"/>
                                    ${newpublname}:<br/>
                                    <input type="text" name="publicationname" value="" required="true" maxlength="45"/>
                                    <br/>${newpubltype}:<br/>
                                    <select name="publicationtype" required="true">
                                        <option value="book">${book}</option>
                                        <option value="paper">${paper}</option>
                                        <option value="magazine">${magazine}</option>
                                    </select>
                                    <br/>${newpublperiodtype}: <br/>
                                    <select name="periodtype" required="true">
                                        <option value="year">${year}</option>
                                        <option value="month">${month}</option>
                                        <option value="weak">${week}</option>
                                    </select>
                                    <br/>${newpublprice}:<br/>
                                    <input type="text" name="publicationprice" maxlength="45" placeholder="${onlynumber}" value="" required="true" pattern="^[ 0-9]+$"/>
                                    <br/>${newpublimage}:<br/>
                                    <input type="file" name="publicationimage" maxlength="45" value="select image" required="true" accept="image/x-png,image/gif,image/jpeg" />
                                    <input type="submit" value="Ok" class="submit"/>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
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
                                <form name="deletePublicationForm" method="GET" action="<c:url value="/controller/admin/publications"/>">
                                    ${findpublications}<br/>
                                    <input type="text" name="findpublicationname" value="" required="true"/>
                                    <input type="submit" class="submit" value="${findd}"/>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="publication-content">
                    <br/>
                    <a class="action-href" href="<c:url value="/controller/admin/publications?type=paper"/>">${showpapers}</a><br/><br/>
                    <a class="action-href" href="<c:url value="/controller/admin/publications?type=magazine"/>">${showmagazines}</a><br/><br/>
                    <a class="action-href" href="<c:url value="/controller/admin/publications?type=book"/>">${showbooks}</a><br/><br/>
                    <a class="action-href" href="<c:url value="/controller/admin/publications"/>">${showall}</a><br/><br/>
                    <u><a onclick="<c:out value="show(${find})"/>" class="action-href">${findpublications}</a></u><br/><br/>
                    <u><a onclick="<c:out value="show(${add})"/>" class="action-href">${addnewpubl}</a></u><br/><br/>
                </div>
            </div>
            <table id="publication">
                <tr>
                    <th>${image}</th>
                    <th>${price}</th>
                    <th>${name}</th>
                    <th>${typee}</th>
                    <th align="center" valign="middle" colspan="2">${action}</th>
                </tr>
                <c:set var="z" value="8" />
                <c:forEach items="${publications}" var="publication">
                    <c:if test="${publication.type == 'paper'}">
                        <c:set var="type" value="${paper}"/>
                    </c:if>
                    <c:if test="${publication.type == 'book'}">
                        <c:set var="type" value="${book}"/>
                    </c:if>
                    <c:if test="${publication.type == 'magazine'}">
                        <c:set var="type" value="${magazine}"/>
                    </c:if>

                    <c:if test="${publication.periodType == 'year'}">
                        <c:set var="periodtype" value="${year}"/>
                    </c:if>
                    <c:if test="${publication.periodType == 'month'}">
                        <c:set var="periodtype" value="${month}"/>
                    </c:if>
                    <c:if test="${publication.periodType == 'weak'}">
                        <c:set var="periodtype" value="${week}"/>
                    </c:if>
                    <div id="<c:out value="${z}"/>" class="modal">
                        <div class="modal_helper">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <span class="close" onclick="<c:out value="hide(${z})"/>">&times;</span>
                                    <br/>
                                    <b>${changeprice} ${publication.name}</b>
                                    <br/>
                                </div>
                                <div class="modal-body">
                                    <form id="delete_form" method="POST" action="<c:url value="/controller/changepublicationprice"/>">
                                        <input type="hidden" name="publicationid" value="${publication.id}"/>
                                        <input pattern="[0-9]" type="text" maxlength="45" name="newprice" value="" required="true"/>
                                        <input type="submit" value="Ok" class="submit"></form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div id="<c:out value="${z}${z}"/>" class="modal">
                        <div class="modal_helper">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <span class="close" onclick="<c:out value="hide(${z}${z})"/>">&times;</span>
                                    <br/>
                                    <b>${changename} ${publication.name}</b>
                                    <br/>
                                </div>
                                <div class="modal-body">
                                    <form id="delete_form" method="POST" action="<c:url value="/controller/changepublicationname"/>">
                                        <input type="hidden" name="publicationid" value="${publication.id}"/>
                                        <input type="text" maxlength="45" name="newpublicationname" value="" required="true"/>
                                        <input type="submit" value="Ok" class="submit"></form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div id="<c:out value="${z}${z}${z}"/>" class="modal">
                        <div class="modal_helper">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <span class="close" onclick="<c:out value="hide(${z}${z}${z})"/>">&times;</span>
                                    <br/>
                                    <b>${changetype} ${publication.name}</b>
                                    <br/>
                                </div>
                                <div class="modal-body">
                                    <form id="delete_form" method="POST" action="<c:url value="/controller/changepublicationtype"/>">
                                        <input type="hidden" name="publicationid" value="${publication.id}"/>
                                        <select name="newtype" required="true">
                                            <option value="book">${book}</option>
                                            <option value="paper">${paper}</option>
                                            <option value="magazine">${magazine}</option>
                                        </select>

                                        <input type="submit" value="Ok" class="submit"></form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <tr>
                        <td><img src="<c:url value="/images/${publication.image}"/>"
                                 width="100" height="100" alt="image">
                        <td><c:out value="${publication.price}/${periodtype}" /><br/><button onclick="<c:out value="show(${z})"/>" class="table_button">${change}</button>
                        <td><c:out value="${publication.name}" /><br/><button onclick="<c:out value="show(${z}${z})"/>" class="table_button">${change}</button>
                        <td><c:out value="${type}" /><br/><button onclick="<c:out value="show(${z}${z}${z})"/>" class="table_button">${change}</button>
                        <td><form id="delete_form" method="POST" action="<c:url value="/controller/delete"/>">
                                <input type="hidden" name="publicationid" value="${publication.id}"/>
                                <input type="submit" value="${deletee}" class="submit">                            </form></td>

                    </tr>
                    <c:set var="z" value="${z+1}"/>
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
