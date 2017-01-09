<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page trimDirectiveWhitespaces="true" %>

<spring:url value="/events/create" var="createUrl"/>
<spring:url value="/events" var="formUrl"/>

<c:set var="admin">
    <sec:authorize access="hasRole('ROLE_ADMIN')">true</sec:authorize>
</c:set>

<jsp:useBean id="now" class="java.util.Date"/>

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

<form class="form-inline search" action="${formUrl}" method="GET">
    <div class="form-group">
        <label for="search" class="sr-only"><spring:message code="link.search"/></label>
        <c:set var="placeholder">
            <spring:message code="entity.event.name"/>, <spring:message code="entity.event.sport"/>, <spring:message
                code="entity.event.city"/>, <spring:message code="entity.event.description"/>
        </c:set>
        <input name="search" type="text" id="search" class="form-control" value="${search}" placeholder="${fn:toLowerCase(placeholder)}"/>
    </div>
    <button class="btn btn-primary"><spring:message code="link.search"/></button>
</form>

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
        <sec:authorize access="isAuthenticated()">
            <th></th>
        </sec:authorize>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${events}" var="event">
        <tr <c:if test="${event.date.time gt now}">class="future"</c:if>>
            <td><strong><c:out value="${event.name}"/></strong></td>
            <td><fmt:formatDate value="${event.date.time}" pattern="dd.MM.yyyy"/></td>
            <td><c:out value="${event.sport.name}"/></td>
            <td><c:out value="${event.capacity}"/></td>
            <td><c:out value="${event.city}"/></td>
            <td><c:out value="${event.address}"/></td>
            <td><c:out value="${event.admin.name} ${event.admin.surname}"/></td>
            <td><c:out value="${event.description}"/></td>
            <sec:authorize access="isAuthenticated()">
                <td class="text-right">
                    <c:if test="${event.date.time gt now}">
                        <c:if test="${admin || event.admin.id == loggedUser.id}">
                            <spring:url value="/events/${event.id}/update" var="updateUrl"/>
                            <a href="${updateUrl}" class="btn btn-primary btn-xs"><spring:message code="link.update"/></a>
                        </c:if>
                    </c:if>
                    <c:if test="${event.date.time lt now}">
                        <c:if test="${admin || event.admin.id == loggedUser.id}">
                            <spring:url value="/results/${event.id}/participants" var="participantsUrl"/>
                            <a href="${participantsUrl}" class="btn btn-primary btn-xs"><spring:message code="link.result.list"/></a>
                        </c:if>
                    </c:if>
                    <spring:url value="/events/${event.id}" var="detailUrl"/>
                    <a href="${detailUrl}" class="btn btn-success btn-xs"><spring:message code="link.detail"/></a>
                </td>
            </sec:authorize>
        </tr>
    </c:forEach>
    </tbody>
</table>
