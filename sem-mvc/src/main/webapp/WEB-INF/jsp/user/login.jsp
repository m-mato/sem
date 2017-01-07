<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page trimDirectiveWhitespaces="true" %>

<spring:url value="/login" var="formUrl"/>

<c:if test="${param.register != null}">
    <div class="alert alert-success" role="alert">
        <spring:message code="page.user.login.alert.register"/>
    </div>
</c:if>
<c:if test="${param.error != null}">
    <div class="alert alert-danger" role="alert">
        <spring:message code="page.user.login.alert.error"/>
    </div>
</c:if>

<form class="form-horizontal" name="f" action="${formUrl}" method="POST">
    <div class="form-group form-group-lg">
        <label class="col-sm-3 control-label" for="username"><spring:message code="entity.sportsman.email"/></label>
        <div class="col-sm-5">
            <div class="input-group">
                <input type="text" class="form-control" name="username" id="username"/>
                <span class="input-group-addon email">@</span>
            </div>
        </div>
    </div>
    <div class="form-group form-group-lg">
        <label class="col-sm-3 control-label" for="password"><spring:message code="entity.sportsman.password"/></label>
        <div class="col-sm-5">
            <div class="input-group">
                <input type="password" class="form-control" name="password" id="password"/>
                <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-3 col-sm-offset-3">
            <button type="submit" class="btn btn-primary btn-lg"><spring:message code="page.user.login.submit"/></button>
        </div>
        <div class="col-sm-2 text-right">
            <button type="button" class="btn btn-warning btn-lg" onclick="window.history.back()">
                <spring:message code="link.back"/>
            </button>
        </div>
    </div>
</form>
