<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page trimDirectiveWhitespaces="true" %>

<spring:url value="/img/sports.jpg" var="sportsImgUrl"/>

<spring:url value="/login" var="userLoginUrl"/>
<spring:url value="/logout" var="userLogoutUrl"/>
<spring:url value="/register" var="userRegisterUrl"/>
<spring:url value="/my-account" var="myAccountUrl"/>

<c:if test="${param.logout != null}">
    <div class="alert alert-success" role="alert">
        <spring:message code="page.index.alert.logout"/>
    </div>
</c:if>

<div class="row">
    <div class="col-md-5">
        <p class="lead"><spring:message code="page.index.welcome"/></p>
        <sec:authorize access="isAnonymous()">
            <a href="${userLoginUrl}" class="btn btn-primary btn-lg"><spring:message code="link.user.login"/></a>
            <a href="${userRegisterUrl}" class="btn btn-danger btn-lg"><spring:message code="link.user.register"/></a>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            <a href="${myAccountUrl}" class="btn btn-primary btn-lg"><spring:message code="link.user.detail"/></a>
            <a href="${userLogoutUrl}" class="btn btn-danger btn-lg"><spring:message code="link.user.logout"/></a>
        </sec:authorize>
    </div>
    <div class="col-md-6 text-center">
        <img src="${sportsImgUrl}"/>
    </div>
</div>
