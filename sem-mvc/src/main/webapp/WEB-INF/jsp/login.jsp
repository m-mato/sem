<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page trimDirectiveWhitespaces="true" %>

<spring:url value="/login" var="formUrl"/>

<c:if test="${param.error != null}">
    <div class="alert alert-danger" role="alert">
        <spring:message code="page.login.error"/>
    </div>
</c:if>

<form class="form-horizontal" name="f" action="${formUrl}" method="POST">
    <div class="form-group form-group-lg">
        <label class="col-sm-3 control-label" for="username"><spring:message code="page.login.username"/></label>
        <div class="col-sm-5">
            <input type="text" class="form-control" name="username" id="username"/>
        </div>
    </div>
    <div class="form-group form-group-lg">
        <label class="col-sm-3 control-label" for="password"><spring:message code="page.login.password"/></label>
        <div class="col-sm-5">
            <input type="password" class="form-control" name="password" id="password"/>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-5 col-sm-offset-3">
            <button type="submit" class="btn btn-primary btn-lg"><spring:message code="page.login.submit"/></button>
        </div>
    </div>
</form>
