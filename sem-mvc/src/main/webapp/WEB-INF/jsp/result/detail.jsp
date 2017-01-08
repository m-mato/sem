<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page trimDirectiveWhitespaces="true" %>

<spring:url value="/results/${result.id}/update" var="updateUrl"/>
<spring:url value="/results/${result.id}/delete" var="deleteUrl"/>

<c:set var="admin">
    <sec:authorize access="hasRole('ROLE_ADMIN')">true</sec:authorize>
</c:set>

<c:if test="${param.create != null}">
    <div class="alert alert-success" role="alert">
        <spring:message code="page.result.detail.alert.create"/>
    </div>
</c:if>
<c:if test="${param.update != null}">
    <div class="alert alert-success" role="alert">
        <spring:message code="page.result.detail.alert.update"/>
    </div>
</c:if>

<c:if test="${admin || result.event.admin.id == loggedUser.id}">
    <p>
        <a href="${updateUrl}" class="btn btn-primary"><spring:message code="link.update"/></a>
    </p>
</c:if>

<table class="table table-striped">
    <tbody>
        <tr>
            <td><spring:message code="entity.result.performance"/></td>
            <c:if test="${result.performance >= 0}">
                <td class="lead"><strong><c:out value="${result.performance}"/></strong></td>
            </c:if>
            <c:if test="${result.performance < 0}">
                <td class="lead"><strong> -- </strong></td>
            </c:if>
        </tr>
        <tr>
            <td><spring:message code="entity.result.performance-unit"/></td>
            <td><spring:message code="performance-units.${result.performanceUnit}"/></td>
        </tr>
        <tr>
            <td><spring:message code="entity.result.position"/></td>
            <c:if test="${result.position >= 0}">
                <td class="lead"><strong><c:out value="${result.position}"/></strong></td>
            </c:if>
            <c:if test="${result.position < 0}">
                <td class="lead"><strong> -- </strong></td>
            </c:if>
        </tr>
        <tr>
            <td><spring:message code="entity.result.sportsman"/></td>
            <td><c:out value="${result.sportsman.name} ${result.sportsman.surname}"/></td>
        </tr>
        <tr>
            <td><spring:message code="entity.result.event"/></td>
            <spring:url value="/events/${result.event.id}" var="eventDetailUrl"/>
            <td><a href="${eventDetailUrl}"><c:out value="${result.event.name}"/></a></td>
        </tr>
        <tr>
            <td><spring:message code="entity.result.note"/></td>
            <td><c:out value="${result.note}"/></td>
        </tr>
    </tbody>
</table>

<c:if test="${fn:length(results) > 0}">
    <h2><spring:message code="page.result.detail.other"/></h2>
    <table class="table table-striped">
        <thead>
            <tr>
                <th><spring:message code="entity.result.performance"/></th>
                <th><spring:message code="entity.result.performance-unit"/></th>
                <th><spring:message code="entity.result.position"/></th>
                <th><spring:message code="entity.result.sportsman"/></th>
                <th></th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${results}" var="result">
            <tr>
                <c:if test="${result.performance >= 0}">
                    <td><c:out value="${result.performance}"/></td>
                </c:if>
                <c:if test="${result.performance < 0}">
                    <td> -- </td>
                </c:if>
                <td><spring:message code="performance-units.${result.performanceUnit}"/></td>
                <c:if test="${result.position >= 0}">
                    <td><c:out value="${result.position}"/></td>
                </c:if>
                <c:if test="${result.position < 0}">
                    <td> -- </td>
                </c:if>
                <td><c:out value="${result.sportsman.name} ${result.sportsman.surname}"/></td>
                <spring:url value="/results/${result.id}" var="detailUrl"/>
                <td class="text-right">
                    <a href="${detailUrl}" class="btn btn-success btn-xs"><spring:message code="link.detail"/></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
