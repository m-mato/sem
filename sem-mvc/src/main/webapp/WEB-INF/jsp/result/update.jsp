<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page trimDirectiveWhitespaces="true" %>

<spring:url value="/results/${result.id}/update" var="formUrl"/>

<c:if test="${error != null}">
    <div class="alert alert-danger" role="alert">
        <spring:message code="page.result.update.alert.error"/>
    </div>
</c:if>

<form:form modelAttribute="result" class="form-horizontal" action="${formUrl}" method="POST">

    <form:hidden path="id"/>

    <spring:bind path="performance">
        <div class="form-group form-group-lg ${status.error ? 'has-error' : ''}">
            <form:label path="performance" class="col-sm-3 control-label">
                <spring:message code="entity.result.performance"/>
            </form:label>
            <div class="col-sm-5">
                <form:input path="performance" class="form-control"/>
            </div>
            <div class="col-sm-4">
                <form:errors path="performance"/>
            </div>
        </div>
    </spring:bind>

    <spring:bind path="performanceUnit">
        <div class="form-group form-group-lg ${status.error ? 'has-error' : ''}">
            <form:label path="performanceUnit" class="col-sm-3 control-label">
                <spring:message code="entity.result.performance-unit"/>
            </form:label>
            <div class="col-sm-5">
                <form:select path="performanceUnit" class="form-control">
                    <form:option value="">-</form:option>
                    <form:options items="${performanceUnits}"/>
                </form:select>
            </div>
            <div class="col-sm-4">
                <form:errors path="performanceUnit"/>
            </div>
        </div>
    </spring:bind>

    <spring:bind path="position">
        <div class="form-group form-group-lg ${status.error ? 'has-error' : ''}">
            <form:label path="position" class="col-sm-3 control-label">
                <spring:message code="entity.result.position"/>
            </form:label>
            <div class="col-sm-5">
                <form:input path="position" class="form-control"/>
            </div>
            <div class="col-sm-4">
                <form:errors path="position"/>
            </div>
        </div>
    </spring:bind>

    <spring:bind path="sportsman">
        <div class="form-group form-group-lg ${status.error ? 'has-error' : ''}">
            <form:label path="sportsman" class="col-sm-3 control-label">
                <spring:message code="entity.result.sportsman"/>
            </form:label>
            <div class="col-sm-5">
                <form:select path="sportsman" class="form-control">
                    <form:option value="">-</form:option>
                    <form:options items="${sportsmans}" itemvalue="id" itemLabel="email"/>
                </form:select>
            </div>
            <div class="col-sm-4">
                <form:errors path="sportsman"/>
            </div>
        </div>
    </spring:bind>

    <spring:bind path="event">
        <div class="form-group form-group-lg ${status.error ? 'has-error' : ''}">
            <form:label path="event" class="col-sm-3 control-label">
                <spring:message code="entity.result.event"/>
            </form:label>
            <div class="col-sm-5">
                <form:select path="event" class="form-control">
                    <form:option value="">-</form:option>
                    <form:options items="${events}" itemvalue="id" itemLabel="name"/>
                </form:select>
            </div>
            <div class="col-sm-4">
                <form:errors path="event"/>
            </div>
        </div>
    </spring:bind>

    <spring:bind path="note">
        <div class="form-group form-group-lg ${status.error ? 'has-error' : ''}">
            <form:label path="note" class="col-sm-3 control-label">
                <spring:message code="entity.result.note"/>
            </form:label>
            <div class="col-sm-5">
                <form:textarea path="note" class="form-control"/>
            </div>
            <div class="col-sm-4">
                <form:errors path="note"/>
            </div>
        </div>
    </spring:bind>

    <div class="form-group">
        <div class="col-sm-3 col-sm-offset-3">
            <form:button class="btn btn-primary btn-lg">
                <spring:message code="page.result.update.submit"/>
            </form:button>
        </div>
        <div class="col-sm-2 text-right">
            <button type="button" class="btn btn-warning btn-lg" onclick="window.history.back()">
                <spring:message code="link.back"/>
            </button>
        </div>
    </div>

</form:form>
