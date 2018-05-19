<table id="publication">
            <c:if test="${not empty param.type || not empty param.findpublicationname}">
                <tr>
                    <th colspan="5"><c:out value="${param.findpublicationname}"/><c:out value="${param.type}s"/></th>
                </tr>
            </c:if>
            <c:if test="${empty param.type && empty param.findpublicationname}">
                <tr>
                    <th colspan="5">all publications</th>
                </tr>
            </c:if>
            <tr>
                <th>Image</th>
                <th>Name</th>
                <th>Type</th>
                <th>Price</th>
                <th>Subscribe</th>
            </tr>
            <c:forEach items="${publications}" var="publication">
                <tr>
                    <td><img src="<c:url value="/images/${publication.image}"/>" 
                             width="10" height="150" alt="image" class="avatar">
                    <td><c:out value="${publication.name}" />
                    <td><c:out value="${publication.type}" />
                    <td><c:out value="${publication.price}/${publication.periodType}" />
                    <td><form name="subsribeForm" method="POST" action="<c:url value="/controller/subscribe"/>">
                            <input type="hidden" name="publicationid" value="${publication.id}"/> 
                            <input type="hidden" name="command" value="subscribe"/>
                            <input type="number" name="subscribtionperiods" value="1" min="0" max="99" required="true">
                            <input type="submit" value="Subscribe"/>
                        </form></td>
                </tr>
            </c:forEach>
        </table>
                            
                                    <c:if test="${not empty param.type || not empty param.findpublicationname}">
            <span class="publication-type"><c:out value="${param.findpublicationname}"/><c:out value="${param.type}s"/></span>
        </c:if>
        <c:if test="${empty param.type && empty param.findpublicationname}">
            <span class="publication-type">All publications</span>
        </c:if>