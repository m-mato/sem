<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page trimDirectiveWhitespaces="true" %>

<spring:url value="/events/create" var="createUrl"/>

<c:if test="${param.delete != null}">
    <div class="alert alert-success" role="alert">
        <spring:message code="page.event.list.alert.delete"/>
    </div>
</c:if>

<sec:authorize access="hasRole('ROLE_USER')">
    <p>
        <a href="${createUrl}" class="btn btn-primary"><spring:message code="page.event.list.create"/></a>
    </p>
</sec:authorize>

<table class="table table-striped">
    <thead>
    <tr>
        <th><spring:message code="entity.event.name"/></th>
        <th><spring:message code="entity.event.date"/></th>
        <th><spring:message code="entity.event.sport"/></th>
        <th><spring:message code="entity.event.capacity"/></th>
        <th><spring:message code="entity.event.city"/></th>
        <th><spring:message code="entity.event.address"/></th>
        <th><spring:message code="entity.event.admin"/></th>
        <th><spring:message code="entity.event.description"/></th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${events}" var="event">
        <tr>
            <td><c:out value="${event.name}"/></td>
            <td><fmt:formatDate value="${event.date.time}" pattern="dd.MM.yyyy"/></td>
            <td><c:out value="${event.sport.name}"/></td>
            <td><c:out value="${event.capacity}"/></td>
            <td><c:out value="${event.city}"/></td>
            <td><c:out value="${event.address}"/></td>
            <td><c:out value="${event.admin.name} ${event.admin.surname}"/></td>
            <td><c:out value="${event.description}"/></td>
            <td>
                <spring:url value="/events/${event.id}" var="detailUrl"/>
                <a href="${detailUrl}" class="btn btn-success btn-xs"><spring:message code="link.detail"/></a>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <spring:url value="/events/${event.id}/update" var="updateUrl"/>
                    <a href="${updateUrl}" class="btn btn-primary btn-xs"><spring:message code="link.update"/></a>
                </sec:authorize>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
