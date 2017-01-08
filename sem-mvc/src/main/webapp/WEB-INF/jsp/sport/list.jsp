<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page trimDirectiveWhitespaces="true" %>

<spring:url value="/sports/create" var="createUrl"/>

<c:if test="${param.delete != null}">
    <div class="alert alert-success" role="alert">
        <spring:message code="page.sport.list.alert.delete"/>
    </div>
</c:if>

<sec:authorize access="hasRole('ROLE_ADMIN')">
    <p>
        <a href="${createUrl}" class="btn btn-primary"><spring:message code="page.sport.list.create"/></a>
    </p>
</sec:authorize>

<table class="table table-striped">
    <thead>
        <tr>
            <th><spring:message code="entity.sport.name"/></th>
            <th><spring:message code="entity.sport.description"/></th>
            <th></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${sports}" var="sport">
            <tr>
                <td><c:out value="${sport.name}"/></td>
                <td><c:out value="${sport.description}"/></td>
                <td class="text-right">
                    <spring:url value="/sports/${sport.id}" var="detailUrl"/>
                    <a href="${detailUrl}" class="btn btn-success btn-xs"><spring:message code="link.detail"/></a>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <spring:url value="/sports/${sport.id}/update" var="updateUrl"/>
                        <a href="${updateUrl}" class="btn btn-primary btn-xs"><spring:message code="link.update"/></a>
                    </sec:authorize>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
