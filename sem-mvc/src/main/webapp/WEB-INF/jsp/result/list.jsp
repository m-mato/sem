<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page trimDirectiveWhitespaces="true" %>

<spring:url value="/results/create" var="createUrl"/>

<c:if test="${param.delete != null}">
    <div class="alert alert-success" role="alert">
        <spring:message code="page.result.list.alert.delete"/>
    </div>
</c:if>

<sec:authorize access="hasRole('ROLE_ADMIN')">
    <p>
        <a href="${createUrl}" class="btn btn-primary"><spring:message code="page.result.list.create"/></a>
    </p>
</sec:authorize>

<table class="table table-striped">
    <thead>
        <tr>
            <th><spring:message code="entity.result.performance"/></th>
            <th><spring:message code="entity.result.performance-unit"/></th>
            <th><spring:message code="entity.result.position"/></th>
            <th><spring:message code="entity.result.sportsman"/></th>
            <th><spring:message code="entity.result.event"/></th>
            <th><spring:message code="entity.result.note"/></th>
            <sec:authorize access="isAuthenticated()">
                <th></th>
            </sec:authorize>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${results}" var="result">
            <tr>
                <td><c:out value="${result.performance}"/></td>
                <td><spring:message code="performance-units.${result.performanceUnit}"/></td>
                <td><c:out value="${result.position}"/></td>
                <td><c:out value="${result.sportsman.name} ${result.sportsman.surname}"/></td>
                <td><c:out value="${result.event.name}"/></td>
                <td><c:out value="${result.note}"/></td>
                <sec:authorize access="isAuthenticated()">
                    <td>
                        <spring:url value="/results/${result.id}" var="detailUrl"/>
                        <a href="${detailUrl}" class="btn btn-success btn-xs"><spring:message code="link.detail"/></a>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <spring:url value="/results/${result.id}/update" var="updateUrl"/>
                            <a href="${updateUrl}" class="btn btn-primary btn-xs"><spring:message code="link.update"/></a>
                        </sec:authorize>
                    </td>
                </sec:authorize>
            </tr>
        </c:forEach>
    </tbody>
</table>