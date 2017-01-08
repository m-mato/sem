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
    <a href="events/create" style="float:right;" class="btn btn-primary"><spring:message code="page.event.create.submit"/></a>
</p>
<div class="container">
        <p><strong><spring:message code="entity.sportsman.name"/>:</strong> <c:out value="${name}"/></p>
        <p><strong><spring:message code="entity.sportsman.surname"/>:</strong> <c:out value="${surname}"/></p>
        <p><strong><spring:message code="entity.sportsman.email"/>:</strong> <c:out value="${email}"/></p>
        <p><strong><spring:message code="entity.sportsman.birth-date"/>:</strong> <fmt:formatDate value="${birthdate.time}" type="both" dateStyle="full" pattern="dd.MM.yyyy"/></p>
        <br/>

        <h2><spring:message code="page.user.detail.results"/></h2>
        <table class="table">
            <thead>
                <tr>
                    <th><spring:message code="entity.event.name"/></th>
                    <th><spring:message code="entity.event.date"/></th>
                    <th><spring:message code="entity.event.description"/></th>
                    <th><spring:message code="entity.event.admin"/></th>
                    <th><spring:message code="entity.result.performance"/></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${results}" var="result">
                    <tr>
                        <spring:url value="/events/${result.event.id}" var="detailUrl"/>
                        <td><strong><a style="color: black" href="${detailUrl}" class="fill-div">${result.event.name}</a></strong></td>
                        <td><fmt:formatDate value="${result.event.date.time}" pattern="dd.MM.yyyy"/></td>
                        <td>${result.event.description}</td>
                        <td>${result.event.admin.surname}, ${result.event.admin.name}</td>
                        <c:if test="${result.position >= 0}">
                            <td>
                                <c:out value="${result.performance}"/>&nbsp;<spring:message code="performance-units.${result.performanceUnit}"/>
                            </td>
                        </c:if>
                        <c:if test="${result.position < 0}">
                            <td>--</td>
                        </c:if>
                        <spring:url value="/events/${result.event.id}" var="detailUrl"/>
                        <td><a href="${detailUrl}" class="btn btn-success btn-xs"><spring:message code="link.detail"/></a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <h2><spring:message code="page.user.detail.invitations"/></h2>
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
                    <spring:url value="/events/${invite.event.id}" var="detailUrl"/>
                    <tr>
                        <%--<td><strong>${invite.event.name}</strong></td>--%>
                        <td><strong><a style="color: black" href="${detailUrl}" class="fill-div">${invite.event.name}</a></strong></td>
                        <td>${invite.event.description}</td>
                        <td>${invite.event.admin.surname}, ${invite.event.admin.name}</td>
                        <td>${invite.state}</td>
                        <td>
                            <c:if test="${invite.state != 'DECLINED'}">
                                <c:if test="${invite.state != 'ACCEPTED'}">
                                        <span>
                                            <a href="accept/${invite.id}" style="float:right;" class="btn btn-primary"><spring:message code="page.user.detail.invitation.accept"/></a>
                                            <a href="decline/${invite.id}" style="float:right;" class="btn btn-primary"><spring:message code="page.user.detail.invitation.decline"/></a>

                                        </span>
                                </c:if>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
