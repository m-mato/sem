<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page trimDirectiveWhitespaces="true" %>

<spring:url value="/find-event" var="findUrl"/>
<spring:url value="/my-account" var="myAccountUrl"/>
<spring:url value="/events" var="eventsUrl"/>

<div id="find-event">
    <div class="container">

        <div>
            <form class="form-horizontal" id="fe" name="fe" action="${findUrl}" method="GET">
                <div>
                    <c:choose>
                        <c:when test="${not empty search}">
                            <input form="fe" id="search" name="search" type="text" class="form-control input-lg" value="${search}">
                        </c:when>
                        <c:otherwise>
                            <input form="fe" id="search" name="search" type="text" class="form-control input-lg" placeholder="<spring:message code="page.find-event.label"/>">
                        </c:otherwise>
                    </c:choose>
                <div>
                <div>
                    <button class="btn btn-primary btn-lg center-block" style="margin-top:15px;"><spring:message code="page.find-event.button.find"/></button>
                </div>
            </form>
        </div>

        <c:if test="${not empty events}">
            <table class="table table-striped" style="margin-top:15px;">
                <thead>
                    <tr>
                        <td><strong><spring:message code="page.find-event.thead.name"/></strong></td>
                        <td><strong><spring:message code="page.find-event.thead.desc"/></strong></td>
                        <td><strong><spring:message code="page.find-event.thead.admin"/></strong></td>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${events}" var="event">
                        <tr>
                            <td style="vertical-align: middle;"><c:out value="${event.name}"/></td>
                            <td style="vertical-align: middle;"><c:out value="${event.description}"/></td>
                            <sec:authorize access="isAuthenticated()">
                                <c:choose>
                                    <c:when test="${loggedUser.email eq event.admin.email}">
                                        <td style="vertical-align: middle;"><a href="${myAccountUrl}"><spring:message code="page.find-event.tbody.you"/></a></td>
                                    </c:when>
                                    <c:otherwise>
                                        <td style="vertical-align: middle;"><c:out value="${event.admin.name} ${event.admin.surname}"/></td>
                                    </c:otherwise>
                                </c:choose>
                            </sec:authorize>
                            <sec:authorize access="isAnonymous()">
                                <td style="vertical-align: middle;"><c:out value="${event.admin.name} ${event.admin.surname}"/></td>
                            </sec:authorize>
                            <td style="vertical-align: middle;">
                                <form id="d" name="d" action="${eventsUrl}/${event.id}" method="GET">
                                    <button form="d" class="btn btn-default btn-sm"><spring:message code="page.find-event.button.detail"/></button>
                                <form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>
</div>
