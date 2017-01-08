<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page trimDirectiveWhitespaces="true" %>

<spring:url value="/sports/${sport.id}/update" var="updateUrl"/>
<spring:url value="/sports/${sport.id}/delete" var="deleteUrl"/>

<c:if test="${param.create != null}">
    <div class="alert alert-success" role="alert">
        <spring:message code="page.sport.detail.alert.create"/>
    </div>
</c:if>
<c:if test="${param.update != null}">
    <div class="alert alert-success" role="alert">
        <spring:message code="page.sport.detail.alert.update"/>
    </div>
</c:if>

<sec:authorize access="hasRole('ROLE_ADMIN')">
    <p>
        <a href="${updateUrl}" class="btn btn-primary"><spring:message code="link.update"/></a>
        <c:if test="${fn:length(events) == 0}">
            <a href="${deleteUrl}" class="btn btn-danger"><spring:message code="link.delete"/></a>
        </c:if>
    </p>
</sec:authorize>

<table class="table table-striped">
    <tbody>
        <tr>
            <td><spring:message code="entity.sport.name"/></td>
            <td class="lead"><strong><c:out value="${sport.name}"/></strong></td>
        </tr>
        <tr>
            <td><spring:message code="entity.sport.description"/></td>
            <td><c:out value="${sport.description}"/></td>
        </tr>
    </tbody>
</table>

<c:if test="${fn:length(events) > 0}">
    <h2><spring:message code="title.event.list"/></h2>
    <table class="table table-striped">
        <thead>
            <tr>
                <th><spring:message code="entity.event.name"/></th>
                <th><spring:message code="entity.event.date"/></th>
                <th><spring:message code="entity.event.city"/></th>
                <th></th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${events}" var="event">
            <tr>
                <td><c:out value="${event.name}"/></td>
                <td><fmt:formatDate value="${event.date.time}" pattern="dd.MM.yyyy"/></td>
                <td><c:out value="${event.city}"/></td>
                <spring:url value="/events/${event.id}" var="detailUrl"/>
                <td class="text-right">
                    <a href="${detailUrl}" class="btn btn-success btn-xs"><spring:message code="link.detail"/></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
