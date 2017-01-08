<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page trimDirectiveWhitespaces="true" %>

<spring:url value="/events/${event.id}/update" var="formUrl"/>

<c:set var="admin">
    <sec:authorize access="hasRole('ROLE_ADMIN')">true</sec:authorize>
</c:set>

<c:if test="${error != null}">
    <div class="alert alert-danger" role="alert">
        <spring:message code="page.event.update.alert.error"/>
    </div>
</c:if>

<form:form modelAttribute="event" class="form-horizontal" action="${formUrl}" method="POST">

    <form:hidden path="id"/>

    <spring:bind path="name">
        <div class="form-group form-group-lg ${status.error ? 'has-error' : ''}">
            <form:label path="name" class="col-sm-3 control-label">
                <spring:message code="entity.event.name"/>
            </form:label>
            <div class="col-sm-5">
                <form:input path="name" class="form-control"/>
            </div>
            <div class="col-sm-4">
                <form:errors path="name"/>
            </div>
        </div>
    </spring:bind>

    <spring:bind path="date">
        <div class="form-group form-group-lg ${status.error ? 'has-error' : ''}">
            <form:label path="date" class="col-sm-3 control-label">
                <spring:message code="entity.event.date"/>
            </form:label>
            <div class="col-sm-5">
                <div class="input-group date">
                    <form:input path="date" class="form-control"/>
                    <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
                </div>
            </div>
            <div class="col-sm-4">
                <form:errors path="date"/>
            </div>
        </div>
    </spring:bind>

    <spring:bind path="sport">
        <div class="form-group form-group-lg ${status.error ? 'has-error' : ''}">
            <form:label path="sport" class="col-sm-3 control-label">
                <spring:message code="entity.event.sport"/>
            </form:label>
            <div class="col-sm-5">
                <form:select path="sport" class="form-control">
                    <form:option value="">-</form:option>
                    <form:options items="${sports}" itemvalue="id" itemLabel="name"/>
                </form:select>
            </div>
            <div class="col-sm-4">
                <form:errors path="sport"/>
            </div>
        </div>
    </spring:bind>

    <spring:bind path="capacity">
        <div class="form-group form-group-lg ${status.error ? 'has-error' : ''}">
            <form:label path="capacity" class="col-sm-3 control-label">
                <spring:message code="entity.event.capacity"/>
            </form:label>
            <div class="col-sm-5">
                <form:input path="capacity" class="form-control"/>
            </div>
            <div class="col-sm-4">
                <form:errors path="capacity"/>
            </div>
        </div>
    </spring:bind>

    <spring:bind path="city">
        <div class="form-group form-group-lg ${status.error ? 'has-error' : ''}">
            <form:label path="city" class="col-sm-3 control-label">
                <spring:message code="entity.event.city"/>
            </form:label>
            <div class="col-sm-5">
                <form:input path="city" class="form-control"/>
            </div>
            <div class="col-sm-4">
                <form:errors path="city"/>
            </div>
        </div>
    </spring:bind>

    <spring:bind path="address">
        <div class="form-group form-group-lg ${status.error ? 'has-error' : ''}">
            <form:label path="address" class="col-sm-3 control-label">
                <spring:message code="entity.event.address"/>
            </form:label>
            <div class="col-sm-5">
                <form:input path="address" class="form-control"/>
            </div>
            <div class="col-sm-4">
                <form:errors path="address"/>
            </div>
        </div>
    </spring:bind>

    <spring:bind path="admin">
        <div class="form-group form-group-lg ${!admin ? 'hidden' : ''} ${status.error ? 'has-error' : ''}">
            <form:label path="admin" class="col-sm-3 control-label">
                <spring:message code="entity.event.admin"/>
            </form:label>
            <div class="col-sm-5">
                <form:select path="admin" class="form-control">
                    <form:options items="${sportsmans}" itemvalue="id" itemLabel="email"/>
                </form:select>
            </div>
            <div class="col-sm-4">
                <form:errors path="admin"/>
            </div>
        </div>
    </spring:bind>

    <spring:bind path="description">
        <div class="form-group form-group-lg ${status.error ? 'has-error' : ''}">
            <form:label path="description" class="col-sm-3 control-label">
                <spring:message code="entity.event.description"/>
            </form:label>
            <div class="col-sm-5">
                <form:textarea path="description" class="form-control"/>
            </div>
            <div class="col-sm-4">
                <form:errors path="description"/>
            </div>
        </div>
    </spring:bind>

    <div class="form-group">
        <div class="col-sm-3 col-sm-offset-3">
            <form:button class="btn btn-primary btn-lg">
                <spring:message code="page.event.update.submit"/>
            </form:button>
        </div>
        <div class="col-sm-2 text-right">
            <button type="button" class="btn btn-warning btn-lg" onclick="window.history.back()">
                <spring:message code="link.back"/>
            </button>
        </div>
    </div>

</form:form>
