<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page trimDirectiveWhitespaces="true" %>

<spring:url value="/img/sports.jpg" var="sportsImgUrl"/>

<div class="col-md-8">
    <div class="col-md-5">
        <input type="text" class="form-control input-lg" placeholder="<spring:message code="page.find-event.label"/>">
    </div>
    <div class="col-md-5">
        <button class="btn btn-lg">Find</button>
    </div>
</div>
