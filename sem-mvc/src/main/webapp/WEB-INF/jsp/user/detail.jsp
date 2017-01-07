<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page trimDirectiveWhitespaces="true" %>

<c:if test="${param.login != null}">
    <div class="alert alert-success" role="alert">
        <spring:message code="page.user.detail.alert.login"/>
    </div>
</c:if>

<p>
    <a href="events/create" style="float:right;" class="btn btn-primary">Create Event</a>
</p>
<div class="container">
        <p><strong><spring:message code="entity.sportsman.name"/>:</strong> <c:out value="${name}"/></p>
        <p><strong><spring:message code="entity.sportsman.surname"/>:</strong> <c:out value="${surname}"/></p>
        <p><strong><spring:message code="entity.sportsman.email"/>:</strong> <c:out value="${email}"/></p>
        <p><strong><spring:message code="entity.sportsman.birth-date"/>:</strong> <fmt:formatDate value="${birthdate.time}" type="both" dateStyle="full" pattern="dd.MM.yyyy"/></p>
        <br/>

        <table class="table">
            <thead>
                <tr>
                    <th><spring:message code="entity.event.name"/></th>
                    <th><spring:message code="entity.event.date"/></th>
                    <th><spring:message code="entity.event.description"/></th>
                    <th><spring:message code="entity.event.admin"/></th>
                    <th><spring:message code="entity.result.performance"/></th>
                    <th><spring:message code="entity.result.performance-unit"/></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${results}" var="result">
                    <tr>
                        <td>${result.event.name}</td>
                        <td><fmt:formatDate value="${result.event.date.time}" pattern="dd.MM.yyyy"/></td>
                        <td>${result.event.description}</td>
                        <td>${result.event.admin.surname}, ${result.event.admin.name}</td>
                        <c:if test="${result.position >= 0}">
                            <td><c:out value="${result.performance}"/></td>
                            <td><spring:message code="performance-units.${result.performanceUnit}"/></td>
                        </c:if>
                        <c:if test="${result.position < 0}">
                            <td> -- </td>
                            <td> -- </td>
                        </c:if>
                        <spring:url value="/events/${result.event.id}" var="detailUrl"/>
                        <td><a href="${detailUrl}" class="btn btn-success btn-xs"><spring:message code="link.detail"/></a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <table class="table">
            <thead>
                <tr>
                    <th><spring:message code="entity.sportsman.invitations"/></th>
                    <th><spring:message code="entity.event.description"/></th>
                    <th><spring:message code="entity.event.admin"/></th>
                    <th><spring:message code="entity.invitation.state"/></th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${invitations}" var="invite">
                    <tr>
                        <td>${invite.event.name}</td>
                        <td>${invite.event.description}</td>
                        <td>${invite.event.admin.surname}, ${invite.event.admin.name}</td>
                        <td>${invite.state}</td>
                        <td>
                            <c:if test="${invite.state != 'DECLINED'}">
                                <c:if test="${invite.state != 'ACCEPTED'}">
                                        <span>
                                            <a href="accept/${invite.id}" style="float:right;" class="btn btn-primary">Accept</a>
                                            <a href="decline/${invite.id}" style="float:right;" class="btn btn-primary">Decline</a>

                                        </span>
                                </c:if>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
