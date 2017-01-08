<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page trimDirectiveWhitespaces="true" %>

<spring:url value="/results/${event.id}/participants" var="formUrl"/>

<c:set var="admin">
    <sec:authorize access="hasRole('ROLE_ADMIN')">true</sec:authorize>
</c:set>

<c:if test="${param.delete != null}">
    <div class="alert alert-success" role="alert">
        <spring:message code="page.result.list.alert.delete"/>
    </div>
</c:if>


<table class="table table-striped">
    <thead>
    <tr>
        <th><spring:message code="entity.result.position"/></th>
        <th><spring:message code="entity.result.performance"/></th>
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
            <c:if test="${result.position >= 0}">
                <td><c:out value="${result.position}"/></td>
            </c:if>
            <c:if test="${result.position < 0}">
                <td> -- </td>
            </c:if>

            <c:if test="${result.performance >= 0}">
                <td><c:out value="${result.performance}"/><spring:message code="performance-units.${result.performanceUnit}"/></td>
            </c:if>
            <c:if test="${result.performance < 0}">
                <td> -- </td>
            </c:if>

            <td><c:out value="${result.sportsman.name} ${result.sportsman.surname}"/></td>
            <td><c:out value="${result.event.name}"/></td>
            <td><c:out value="${result.note}"/></td>
            <sec:authorize access="isAuthenticated()">
                <td>
                    <spring:url value="/results/${result.id}" var="detailUrl"/>
                    <a href="${detailUrl}" class="btn btn-success btn-xs"><spring:message code="link.detail"/></a>
                    <c:if test="${admin || result.event.admin.id == loggedUser.id}">
                        <spring:url value="/results/${result.event.id}/reset/${result.id}" var="resetUrl"/>
                        <a href="${resetUrl}" class="btn btn-danger btn-xs"><spring:message code="link.reset"/></a>
                        <spring:url value="/results/${result.event.id}/insert/${result.id}" var="insertUrl"/>
                        <a href="${insertUrl}" class="btn btn-primary btn-xs"><spring:message code="link.update"/></a>
                    </c:if>
                </td>
            </sec:authorize>
        </tr>
    </c:forEach>
    </tbody>
</table>

<button type="button" class="btn btn-warning" onclick="window.history.back()">
    <spring:message code="link.back-to-event"/>
</button>
