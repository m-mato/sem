<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page trimDirectiveWhitespaces="true" %>

<spring:url value="/sports/${sport.id}/update" var="formUrl"/>

<c:if test="${error != null}">
    <div class="alert alert-danger" role="alert">
        <spring:message code="page.sport.update.alert.error"/>
    </div>
</c:if>

<form:form modelAttribute="sport" class="form-horizontal" action="${formUrl}" method="POST">

    <form:hidden path="id"/>

    <spring:bind path="name">
        <div class="form-group form-group-lg ${status.error ? 'has-error' : ''}">
            <form:label path="name" class="col-sm-3 control-label">
                <spring:message code="entity.sport.name"/>
            </form:label>
            <div class="col-sm-5">
                <form:input path="name" class="form-control"/>
            </div>
            <div class="col-sm-4">
                <form:errors path="name"/>
            </div>
        </div>
    </spring:bind>

    <spring:bind path="description">
        <div class="form-group form-group-lg ${status.error ? 'has-error' : ''}">
            <form:label path="description" class="col-sm-3 control-label">
                <spring:message code="entity.sport.description"/>
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
                <spring:message code="page.sport.update.submit"/>
            </form:button>
        </div>
        <div class="col-sm-2 text-right">
            <button type="button" class="btn btn-warning btn-lg" onclick="window.history.back()">
                <spring:message code="link.back"/>
            </button>
        </div>
    </div>

</form:form>
