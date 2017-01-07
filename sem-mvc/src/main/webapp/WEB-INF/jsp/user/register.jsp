<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page trimDirectiveWhitespaces="true" %>

<spring:url value="/register" var="formUrl"/>

<c:if test="${error != null}">
    <div class="alert alert-danger" role="alert">
        <spring:message code="page.user.register.alert.error"/>
    </div>
</c:if>

<form:form modelAttribute="sportsman" class="form-horizontal" action="${formUrl}" method="POST">

    <form:hidden path="isManager"/>

    <spring:bind path="email">
        <div class="form-group form-group-lg ${status.error ? 'has-error' : ''}">
            <form:label path="email" class="col-sm-3 control-label">
                <spring:message code="entity.sportsman.email"/>
            </form:label>
            <div class="col-sm-5">
                <div class="input-group">
                    <form:input path="email" class="form-control"/>
                    <span class="input-group-addon email">@</span>
                </div>
            </div>
            <div class="col-sm-4">
                <form:errors path="email"/>
            </div>
        </div>
    </spring:bind>

    <spring:bind path="password">
        <div class="form-group form-group-lg ${status.error ? 'has-error' : ''}">
            <form:label path="password" class="col-sm-3 control-label">
                <spring:message code="entity.sportsman.password"/>
            </form:label>
            <div class="col-sm-5">
                <div class="input-group">
                    <form:password path="password" class="form-control"/>
                    <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                </div>
            </div>
            <div class="col-sm-4">
                <form:errors path="password"/>
            </div>
        </div>
    </spring:bind>

    <spring:bind path="name">
        <div class="form-group form-group-lg ${status.error ? 'has-error' : ''}">
            <form:label path="name" class="col-sm-3 control-label">
                <spring:message code="entity.sportsman.name"/>
            </form:label>
            <div class="col-sm-5">
                <form:input path="name" class="form-control"/>
            </div>
            <div class="col-sm-4">
                <form:errors path="name"/>
            </div>
        </div>
    </spring:bind>

    <spring:bind path="surname">
        <div class="form-group form-group-lg ${status.error ? 'has-error' : ''}">
            <form:label path="surname" class="col-sm-3 control-label">
                <spring:message code="entity.sportsman.surname"/>
            </form:label>
            <div class="col-sm-5">
                <form:input path="surname" class="form-control"/>
            </div>
            <div class="col-sm-4">
                <form:errors path="surname"/>
            </div>
        </div>
    </spring:bind>

    <spring:bind path="birthDate">
        <div class="form-group form-group-lg ${status.error ? 'has-error' : ''}">
            <form:label path="birthDate" class="col-sm-3 control-label">
                <spring:message code="entity.sportsman.birth-date"/>
            </form:label>
            <div class="col-sm-5">
                <div class="input-group date">
                    <form:input path="birthDate" class="form-control"/>
                    <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
                </div>
            </div>
            <div class="col-sm-4">
                <form:errors path="birthDate"/>
            </div>
        </div>
    </spring:bind>

    <div class="form-group">
        <div class="col-sm-3 col-sm-offset-3">
            <form:button class="btn btn-primary btn-lg">
                <spring:message code="page.user.register.submit"/>
            </form:button>
        </div>
        <div class="col-sm-2 text-right">
            <button type="button" class="btn btn-warning btn-lg" onclick="window.history.back()">
                <spring:message code="link.back"/>
            </button>
        </div>
    </div>

</form:form>
