<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page trimDirectiveWhitespaces="true" %>

<spring:url value="/events/${event.id}/update" var="updateUrl"/>
<spring:url value="/events/${event.id}/delete" var="deleteUrl"/>
<spring:url value="/results/${event.id}/participants" var="participantsUrl"/>
<spring:url value="/events/invite" var="inviteUrl"/>

<jsp:useBean id="now" class="java.util.Date"/>

<c:set var="admin">
    <sec:authorize access="hasRole('ROLE_ADMIN')">true</sec:authorize>
</c:set>

<c:if test="${param.create != null}">
    <div class="alert alert-success" role="alert">
        <spring:message code="page.event.detail.alert.create"/>
    </div>
</c:if>
<c:if test="${param.update != null}">
    <div class="alert alert-success" role="alert">
        <spring:message code="page.event.detail.alert.update"/>
    </div>
</c:if>
<c:if test="${param.enrolled != null}">
    <div class="alert alert-success" role="alert">
        <spring:message code="page.event.detail.alert.enroll"/>
    </div>
</c:if>
<c:if test="${param.unenrolled != null}">
    <div class="alert alert-success" role="alert">
        <spring:message code="page.event.detail.alert.unenroll"/>
    </div>
</c:if>
<c:if test="${param.accepted != null}">
    <div class="alert alert-success" role="alert">
        <spring:message code="page.event.detail.alert.accept"/>
    </div>
</c:if>

<c:if test="${admin || event.admin.id == loggedUser.id}">
    <p>
        <c:if test="${event.date.time gt now}">
            <a href="${updateUrl}" class="btn btn-primary"><spring:message code="link.update"/></a>
            <a href="${deleteUrl}" class="btn btn-danger"><spring:message code="link.delete"/></a>
        </c:if>
        <c:if test="${event.date.time lt now}">
            <a href="${participantsUrl}" class="btn btn-success"><spring:message code="page.result.list.insert"/></a>
        </c:if>
    </p>
</c:if>


<spring:url value="/img/search-icon.jpg" var="searchImgUrl"/>
<spring:url value="/events/${event.id}/unenroll" var="unEnrollUrl"/>
<spring:url value="/events/${event.id}/enroll" var="enrollUrl"/>


<div id="event-detail">

    <h2><c:out value="${event.name}"/></h2>

    <div class="row">
        <div class="col-md-6 event-info">
            <p><strong><spring:message code="page.event.detail.where"/>: </strong><c:out value="${event.address}, ${event.city}"/></p>
            <p><strong><spring:message code="page.event.detail.when"/>: </strong><fmt:formatDate type="both" dateStyle="full" value="${event.date.time}" pattern="dd.MM.yyyy"/></p>
            <p><strong><spring:message code="page.event.detail.what"/>: </strong><c:out value="${event.sport.name}"/></p>
        </div>
        <div class="col-md-6">
            <div class="event-admin div-border" style="padding: 20px 20px 10px 20px;">
                <p><strong><spring:message code="page.event.detail.admin"/>: </strong><c:out value="${event.admin.name}"/></p>
                <p><strong>Email: </strong><c:out value="${event.admin.email}"/></p>
            </div>
        </div>
    </div>
    <br>

    <p><strong><spring:message code="page.events.description"/>: </strong><c:out value="${event.description}"/></p>
    <br>

    <c:if test="${not empty result}">
        <div class="row">
            <div class="col-md-4 event-admin div-border">
                <p><strong><big><spring:message code="page.event.detail.result.title"/></big></strong><p>
                <p><strong><spring:message code="page.event.detail.result.result"/>:</strong><c:out value="${result.performance}, ${result.performanceUnit}"/></p>
                <p><strong><spring:message code="page.event.detail.result.position"/>:</strong><c:out value="${result.position}"/></p>
                <div class="col-md-8">
                    <p><c:out value="${page.description}"/></p>
                </div>
            </div>
        </div>
        <br>
    </c:if>
    <%--//it is past event, there is nothing todo--%>
    <c:if test="${event.date.time gt now}">
        <div class="row">
            <c:choose>
                <c:when test="${isParticipant}">
                    <div class="col-md-4">
                        <p>
                            <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
                                <spring:message code="page.event.detail.unenroll.main"/>
                            </a>
                        </p>
                        <div class="collapse" id="collapseExample">
                            <div class="card card-block">
                                <spring:message code="page.event.detail.unenroll.msg"/>
                            </div>
                            <form class="form-horizontal" name="f" action="${unEnrollUrl}" method="GET">
                                <button type="submit" class="btn btn-danger"><spring:message code="page.event.detail.unenroll.confirm"/></button>
                                <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
                                    <spring:message code="page.event.detail.unenroll.cancel"/>
                                </button>
                            </form>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <c:choose>
                        <c:when test="${empty invitation}">
                            <c:choose><%--//not enrolled, can enroll //only if there is a enough capacity--%>
                                <c:when test="${ fn:length(event.participants) lt event.capacity}">
                                    <div class="col-md-4">
                                        <p>
                                            <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
                                                <spring:message code="page.event.detail.enroll.main"/>
                                            </a>
                                        </p>
                                        <div class="collapse" id="collapseExample">
                                            <div class="card card-block">
                                                <spring:message code="page.event.detail.enroll.msg"/>
                                            </div>
                                            <form class="form-horizontal" name="f" action="${enrollUrl}" method="GET">
                                                <button type="submit" class="btn btn-danger"><spring:message code="page.event.detail.enroll.confirm"/></button>
                                                <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
                                                    <spring:message code="page.event.detail.enroll.cancel"/>
                                                </button>
                                            </form>
                                        </div>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="col-md-4">
                                        <p>
                                            <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" aria-expanded="false" aria-controls="collapseExample" disabled="true" style="text-decoration: line-through">
                                                <spring:message code="page.event.detail.enroll.main"/>
                                            </a>
                                        </p>
                                        <p class="text-danger"><spring:message code="page.event.detail.enroll.full" /></p>
                                    </div>
                                </c:otherwise>
                            </c:choose> <%--//not enrolled, can enroll //only if there is a enough capacity--%>
                        </c:when>
                        <c:otherwise>
                            <div class="col-md-12">
                                <p><strong><spring:message code="page.event.detail.invite.msg"/></strong></p>
                                <span>
                                    <spring:url value="/accept/${invitation.id}" var="acceptUrl"/>
                                    <spring:url value="/decline/${invitation.id}" var="declineUrl"/>
                                    <a href="${acceptUrl}" class="btn btn-primary"><spring:message code="page.user.detail.invitation.accept"/></a>
                                    <a href="${declineUrl}" class="btn btn-danger"><spring:message code="page.user.detail.invitation.decline"/></a>
                                </span>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </c:otherwise>
            </c:choose>

            <%--no possible to invite another sportmans if capacity is full--%>
            <c:if test="${ fn:length(event.participants) lt event.capacity && isParticipant}">
                <div class="col-md-8">
                    <form class="form-inline" name="invite" action="${inviteUrl}" method="POST">
                        <label for="InputEmail"><spring:message code="page.event.detail.invite.title"/></label><br>
                        <input type="number" name="inv_event_id" value="${event.id}" id="inv_event_id" hidden>
                        <select class="fetchSportsmans myWidth" name="inputEmail" id="inputEmail">
                            <option name="sel" selected="selected"></option>
                        </select>
                        <button id="invite_button" type="button" class="btn btn-primary"><spring:message code="page.event.detail.invite.button"/></button>
                    </form>
                </div>
            </c:if>
        </div>
    </c:if>

</div>

<br>

<c:if test="${fn:length(results) > 0}">
    <h2><spring:message code="title.result.list"/></h2>
    <table class="table">
        <thead>
        <tr>
            <th><spring:message code="entity.result.position"/></th>
            <th><spring:message code="entity.result.performance"/></th>
            <th><spring:message code="entity.result.sportsman"/></th>
            <th><spring:message code="entity.result.note"/></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${results}" var="result">
            <c:choose>
                <c:when test="${loggedUser.email==result.sportsman.email}">
                    <tr class="row-colored">
                </c:when>
                <c:otherwise>
                    <tr>
                </c:otherwise>
            </c:choose>
                <td><c:out value="${result.position}"/></td>
                <td><c:out value="${result.performance}"/><spring:message code="performance-units.${result.performanceUnit}"/></td>
                <td><c:out value="${result.sportsman.name} ${result.sportsman.surname}"/></td>
                <td><c:out value="${result.note}"/></td>
                <spring:url value="/results/${result.id}" var="detailUrl"/>
                <td class="text-right">
                    <a href="${detailUrl}" class="btn btn-success btn-xs"><spring:message code="link.detail"/></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
