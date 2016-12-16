<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:useBean id="now" class="java.util.Date"/>


<spring:url value="/img/search-icon.jpg" var="searchImgUrl"/>
<spring:url value="/events/${event.id}/unenroll" var="unEnrollUrl"/>





<div id="event-detail">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
                <h2><c:out value="${event.name}"/></h2>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6 event-info">
                <p><strong><spring:message code="page.event.where"/>:</strong><c:out value=" ${event.address}, ${event.city}"/></p>
                <p><strong><spring:message code="page.event.when"/>:</strong><fmt:formatDate type="both" dateStyle="full" value="${event.date.time}" pattern="dd.MM.yyyy"/></p>
                <p><strong><spring:message code="page.event.what"/>:</strong><c:out value="${event.sport.name}"/></p>
            </div>
            <div class="col-md-6 event-admin div-border">
                <p><strong><spring:message code="page.event.admin"/></strong><p>
                <p><c:out value="${event.admin.name}"/></p>
                 <p><strong>Email:</strong><c:out value="${event.admin.email}"/></p>
            </div>
        </div><br>
        <div class="row">
            <div class="col-md-12">
                <p><c:out value="${page.description}"/>Food is my passion. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
            </div>
        </div><br>
        <c:if test="${not empty result}">
        <div class="row">
            <div class="col-md-4 event-admin div-border">
                <p><strong><big><spring:message code="page.event.result.title"/></big></strong><p>
                <p><strong><spring:message code="page.event.result.result"/>:</strong><c:out value="${result.performance}, ${result.performanceUnit}"/></p>
                <p><strong><spring:message code="page.event.result.position"/>:</strong><c:out value="${result.position}"/></p>
                <div class="col-md-8">
                    <p><c:out value="${page.description}"/></p>
                </div>
            </div>
        </div><br>
        </c:if>
    <%--//it is past event, there is nothing todo--%>
        <%--<c:if test="${event.date.time gt now}">--%>
        <div class="row">
            <div class="col-md-4">
                    <p>
                        <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
                            <spring:message code="page.event.unenroll.main"/>
                        </a>
                    </p>
                    <div class="collapse" id="collapseExample">
                        <div class="card card-block">
                            <spring:message code="page.event.unenroll.msg"/>
                        </div>
                        <form class="form-horizontal" name="f" action="${unEnrollUrl}" method="GET">
                            <button type="submit" class="btn btn-danger"><spring:message code="page.event.unenroll.confirm"/></button>
                            <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
                                <spring:message code="page.event.unenroll.cancel"/>
                            </button>
                        </form>
                    </div>
            </div>
            <%--no possible to invite another sportmans if capacity is full--%>
            <c:if test="${ fn:length(event.participants) lt event.capacity}">
            <div class="col-md-8">
                <form class="form-inline">
                    <label for="InputEmail"><spring:message code="page.event.invite.title"/></label><br>
                    <select class="fetchData event-item" id="inputEmail">
                        <option value="2" selected="selected"></option>
                    </select>
                    <button type="submit" class="btn btn-primary"><spring:message code="page.event.invite.button"/></button>
                </form>
            </div>
            </c:if>
        </div>
        <%--</c:if>--%>

    </div>

<br><br>
</div>
