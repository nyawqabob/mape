
<%@page isELIgnored="false" contentType="text/html" pageEncoding="windows-1251"%>
<!DOCTYPE html>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<html>
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="properties.text" var="locale" scope="session"/>

    <fmt:message bundle="${locale}" key="text.home.local.main" var="main"/>
    <fmt:message bundle="${locale}" key="text.home.local.balance" var="balancee"/>
    <fmt:message bundle="${locale}" key="text.home.local.findpublications" var="findpublications"/>
    <fmt:message bundle="${locale}" key="text.home.local.showpapers" var="showpapers"/>
    <fmt:message bundle="${locale}" key="text.home.local.showmagazines" var="showmagazines"/>
    <fmt:message bundle="${locale}" key="text.home.local.showbooks" var="showbooks"/>
    <fmt:message bundle="${locale}" key="text.home.local.showall" var="showall"/>
    <fmt:message bundle="${locale}" key="text.home.local.chippiest" var="chippiest"/>
    <fmt:message bundle="${locale}" key="text.admin.local.actions" var="actions"/>
    <fmt:message bundle="${locale}" key="text.users.local.find" var="findd"/>
    <fmt:message bundle="${locale}" key="text.admin.local.logout" var="logout"/>
    <fmt:message bundle="${locale}" key="text.admin.local.profile" var="profile"/>
    <fmt:message bundle="${locale}" key="text.home.local.subscribe" var="subscribe"/>
    <fmt:message bundle="${locale}" key="text.admin.local.book" var="book"/>
    <fmt:message bundle="${locale}" key="text.main.local.by" var="by"/>
    <fmt:message bundle="${locale}" key="text.main.local.ru" var="ru_button"/>
    <fmt:message bundle="${locale}" key="text.main.local.en" var="en_button"/>
    <fmt:message bundle="${locale}" key="text.home.local.year" var="year"/>
    <fmt:message bundle="${locale}" key="text.home.local.month" var="month"/>
    <fmt:message bundle="${locale}" key="text.home.local.week" var="week"/>
    <fmt:message bundle="${locale}" key="text.home.local.subperiods" var="subperiods"/>
    <fmt:message bundle="${locale}" key="text.admin.local.magazine" var="magazine"/>
    <fmt:message bundle="${locale}" key="text.admin.local.paper" var="paper"/>
    <c:if test="${not empty mainsubscribesuccess}">
    <fmt:message bundle="${locale}" key="text.home.local.mainsubscribesuccess" var="mainsuccess"/>
    </c:if>
    <c:if test="${not empty mainsubscribeerror}">
    <fmt:message bundle="${locale}" key="text.home.local.mainsubscribeerror" var="mainerror"/>
    </c:if>
     <c:if test="${not empty mainadminerror}">
    <fmt:message bundle="${locale}" key="text.home.local.mainadminerror" var="mainerror"/>
    </c:if>

    <head>
        <link href="<c:url value='/css/default.css' />" rel="stylesheet" type="text/css"/>
        <script src="<c:url value='/js/admin.js' />" type="text/javascript"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
        <title>${main}</title>
    </head>
    <body>
        <c:set var="success" value="55" />
        <c:set var="error" value="66" />
        <c:if test="${not empty mainsuccess}">
            <div class="success" id="<c:out value="${success}"/>">
                <div class="success-msg">
                    <b>${mainsuccess}</b><br/><br/>
                    <button class="submit" onclick="<c:out value="hide(${success})"/>">Ok</button>
                    <c:remove var="mainsuccess" scope="session" />
                    <c:remove var="mainerror" scope="session" />
                    <c:remove var="mainsubscribesuccess" scope="session" />
                    <c:remove var="mainsubscribeerror" scope="session" />
                    <c:remove var="mainadminerror" scope="session" />
                </div></div>
            </c:if>
            <c:if test="${not empty mainerror}">
            <div class="success" id="<c:out value="${error}"/>">
                <div class="error-msg">
                    <b>${mainerror}</b><br/><br/>
                    <button class="submit-error" onclick="<c:out value="hide(${error})"/>">Ok</button>
                    <c:remove var="mainerror" scope="session" />
                    <c:remove var="mainsuccess" scope="session" />
                    <c:remove var="mainsubscribesuccess" scope="session" />
                    <c:remove var="mainsubscribeerror" scope="session" />
                    <c:remove var="mainadminerror" scope="session" />
                </div></div>
            </c:if>
    </div>
    <div class="header" id="myHeader">
        <div class="left_header"><a href="<c:url value="/controller/main"/>"><img class="img_header" src="<c:url value="/images/jspimages/logo.png"/>"></a></div>
        <div class="center_header_publication">
            <div class="link"><a href="<c:url value="/controller/profile"/>">${profile}</a>
            </div>
        </div>
        <div class="balance"></div>
        <div class="header_right">

            <a href="<c:url value="/controller/logout"/>" class="header_href" class="header_href"> ${logout}</a><br/>
            ${balancee}: <b><c:out value="${balance}"/></b>
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
                            <b>${findpublications}</b>
                            <br/>
                        </div>
                            
                        <div class="modal-body">

                            <br/>
                            <form name="deletePublicationForm" method="GET" action="<c:url value="/controller/main"/>">
                                ${findpublications}:<br/>
                                <input type="text" name="findpublicationname" value="" required="true"/>
                                <input type="submit" class="submit" value="${findd}"/>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="publication-content">
                <br/>
                <a class="action-href" href="<c:url value="/controller/main?type=paper"/>">${showpapers}</a><br/><br/>
                <a class="action-href" href="<c:url value="/controller/main?type=magazine"/>">${showmagazines}</a><br/><br/>
                <a class="action-href" href="<c:url value="/controller/main?type=book"/>">${showbooks}</a><br/><br/>
                <a class="action-href" href="<c:url value="/controller/main"/>">${showall}</a><br/><br/>
                <u><a onclick="<c:out value="show(${find})"/>" class="action-href">${findpublications}</a></u><br/><br/>
            </div>
        </div>
        <div class="publication-three">
            <div class="publication-header">
                <br/>
                <b>${chippiest}</b>
                <br/>
                <br/>
            </div>
            <div class="publication-content">
                <div class="pricing-plan">
                    <c:if test="${chippiestpublication.type == 'paper'}">
                        <c:set var="chippiesttype" value="${paper}"/>
                    </c:if>
                    <c:if test="${chippiestpublication.type == 'book'}">
                        <c:set var="chippiesttype" value="${book}"/>
                    </c:if>

                    <c:if test="${chippiestpublication.type == 'magazine'}">
                        <c:set var="chippiesttype" value="${magazine}"/>
                    </c:if>
                    <c:if test="${chippiestpublication.periodType == 'year'}">
                        <c:set var="chippiestperiodtype" value="${year}"/>
                    </c:if>
                    <c:if test="${chippiestpublication.periodType == 'month'}">
                        <c:set var="chippiestperiodtype" value="${month}"/>
                    </c:if>
                    <c:if test="${chippiestpublication.periodType == 'weak'}">
                        <c:set var="chippiestperiodtype" value="${week}"/>
                    </c:if>
                    <img src="<c:url value="/images/${chippiestpublication.image}"/>" 
                         width="10" height="150" alt="image" class="avatar">
                    <h2 class="pricing-header"><c:out value="${chippiestpublication.name}" /></h2>
                    <ul class="pricing-features">
                        <span class="pricing-features-item"><c:out value="${chippiesttype}" /></span>
                    </ul>
                    <span class="pricing-price"><c:out value="${chippiestpublication.price}$ / ${chippiestperiodtype}" /></span>
                    <form name="subsribeForm" method="POST" action="<c:url value="/controller/subscribe"/>">
                        <input type="hidden" name="publicationid" value="${chippiestpublication.id}"/> 
                        <input type="hidden" name="command" value="subscribe"/>
                        <input type="text" pattern="[0-9]" name="subscribtionperiods" required="true" placeholder="${subperiods}">
                        <input type="submit" value="${subscribe}" class="pricing-button"/>
                    </form>
                </div>
            </div>
        </div>

        <div class="panel">

            <c:forEach items="${publications}" var="publication">
                <div class="pricing-plan">
                    <img src="<c:url value="/images/${publication.image}"/>" 
                         width="10" height="150" alt="image" class="avatar">
                    <h2 class="pricing-header"><c:out value="${publication.name}" /></h2>
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

                    <ul class="pricing-features">
                        <span class="pricing-features-item"><c:out value="${type}" /></span>
                    </ul>

                    <span class="pricing-price"><c:out value="${publication.price}$ / ${periodtype}" /></span>
                    <form name="subsribeForm" method="POST" action="<c:url value="/controller/subscribe"/>">
                        <input type="hidden" name="publicationid" value="${publication.id}"/> 
           
                        <input type="text" name="subscribtionperiods" required="true" placeholder="${subperiods}">
                        <input type="submit" value="${subscribe}" class="pricing-button"/>
                    </form>
                </div>
            </c:forEach>
        </div>
        <div class="pagination">
            <c:forEach items="${pagesList}" var="page"><a class="action-href" href="<c:url value="/controller/main?page=${page}"/>">${page}</a>
            </c:forEach>
        </div>
    </div>
  <t:footer></t:footer>
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
</body>
</html>
