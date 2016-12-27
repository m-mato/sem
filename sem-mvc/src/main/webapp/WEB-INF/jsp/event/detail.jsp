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
<spring:url value="/events/invite" var="inviteUrl"/>


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

<sec:authorize access="hasRole('ROLE_ADMIN')">
    <p>
        <a href="${updateUrl}" class="btn btn-primary"><spring:message code="link.update"/></a>
        <c:if test="${fn:length(results) == 0}">
            <a href="${deleteUrl}" class="btn btn-danger"><spring:message code="link.delete"/></a>
        </c:if>
    </p>
</sec:authorize>



<jsp:useBean id="now" class="java.util.Date"/>


<spring:url value="/img/search-icon.jpg" var="searchImgUrl"/>
<spring:url value="/events/${event.id}/unenroll" var="unEnrollUrl"/>
<spring:url value="/events/${event.id}/enroll" var="enrollUrl"/>





<div id="event-detail">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
                <h2><c:out value="${event.name}"/></h2>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6 event-info">
                <p><strong><spring:message code="page.event.detail.where"/>: </strong><c:out value="${event.address}, ${event.city}"/></p>
                <p><strong><spring:message code="page.event.detail.when"/>: </strong><fmt:formatDate type="both" dateStyle="full" value="${event.date.time}" pattern="dd.MM.yyyy"/></p>
                <p><strong><spring:message code="page.event.detail.what"/>: </strong><c:out value="${event.sport.name}"/></p>
            </div>
            <div class="col-md-6 event-admin div-border">
                <p><strong><spring:message code="page.event.detail.admin"/>: </strong><c:out value="${event.admin.name}"/></p>
                 <p><strong>Email: </strong><c:out value="${event.admin.email}"/></p>
            </div>
        </div><br>
        <div class="row">
            <div class="col-md-12">
                <p><strong><spring:message code="page.events.description"/>: </strong><c:out value="${event.description}"/></p>
            </div>
        </div><br>
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
        </div><br>
        </c:if>
    <%--//it is past event, there is nothing todo--%>
        <%--<c:if test="${event.date.time gt now}">--%>
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
                    <c:otherwise> <%--//not enrolled, can enroll //only if there is a enough capacity--%>
                        <c:if test="${ fn:length(event.participants) lt event.capacity}">
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
                        </c:if>
                    </c:otherwise>
                </c:choose>

        <%--no possible to invite another sportmans if capacity is full--%>
            <c:if test="${ fn:length(event.participants) lt event.capacity && isParticipant}">
            <div class="col-md-8">
                <form class="form-inline" name="invite" action="${inviteUrl}" method="POST">
                    <label for="InputEmail"><spring:message code="page.event.detail.invite.title"/></label><br>
                    <input type="number" name="inv_event_id" value="${event.id}" id="inv_event_id" hidden>
                    <select class="fetchSportsmans myWidth" name="inputEmail" id="inputEmail">
                        <option value="2" name="sel" selected="selected"></option>
                    </select>
                    <button id="invite_button" type="button" class="btn btn-primary"><spring:message code="page.event.detail.invite.button"/></button>
                </form>
            </div>
            </c:if>
        </div>
        <%--</c:if>--%>

    </div>

<br><br>
</div>
