<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page trimDirectiveWhitespaces="true" %>

<spring:url value="/img/sports.jpg" var="sportsImgUrl"/>

<c:if test="${param.logout != null}">
    <div class="alert alert-success" role="alert">
        <spring:message code="page.index.logout"/>
    </div>
</c:if>

<div class="row">
    <div class="col-md-5">
        <h2><spring:message code="page.index.welcome"/></h2>
    </div>
    <div class="col-md-6 text-center">
        <img src="${sportsImgUrl}"/>
    </div>
</div>
