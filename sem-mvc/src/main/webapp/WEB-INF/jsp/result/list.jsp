<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page trimDirectiveWhitespaces="true" %>

<spring:url value="/results/create" var="createUrl"/>

<c:set var="admin">
    <sec:authorize access="hasRole('ROLE_ADMIN')">true</sec:authorize>
</c:set>

<c:if test="${param.delete != null}">
    <div class="alert alert-success" role="alert">
        <spring:message code="page.result.list.alert.delete"/>
    </div>
</c:if>


<c:forEach var="event" items="${events}">
    <spring:url value="/events/${result.event.id}" var="detailUrl"/>
    <h3><a style="color: black" href="${detailUrl}" class="fill-div">${event.key.name}</a></h3>
    <table class="table table-striped">
        <thead>
        <tr>
            <th><spring:message code="entity.result.position"/></th>
            <th><spring:message code="entity.result.performance"/></th>
            <th><spring:message code="entity.result.sportsman"/></th>
            <th><spring:message code="entity.result.note"/></th>
            <%--<sec:authorize access="isAuthenticated()">--%>
                <%--<th></th>--%>
            <%--</sec:authorize>--%>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${event.value}" var="result">
            <spring:url value="/results/${result.id}" var="detailUrl"/>
            <tr  class='clickable-row' data-href='${detailUrl}'>
                <td><strong><c:out value="${result.position}"/></strong></td>
                <td><c:out value="${result.performance}"/><spring:message code="performance-units.${result.performanceUnit}"/></td>
                <td><c:out value="${result.sportsman.name} ${result.sportsman.surname}"/></td>
                <td><c:out value="${result.note}"/></td>
                <%--<sec:authorize access="isAuthenticated()">--%>
                    <%--<td>--%>
                        <%--<spring:url value="/results/${result.id}" var="detailUrl"/>--%>
                        <%--<a href="${detailUrl}" class="btn btn-success btn-xs"><spring:message code="link.detail"/></a>--%>
                        <%--<c:if test="${admin || result.event.admin.id == loggedUser.id}">--%>
                            <%--<spring:url value="/results/${result.id}/update" var="updateUrl"/>--%>
                            <%--<a href="${updateUrl}" class="btn btn-primary btn-xs"><spring:message code="link.update"/></a>--%>
                        <%--</c:if>--%>
                    <%--</td>--%>
                <%--</sec:authorize>--%>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:forEach>
