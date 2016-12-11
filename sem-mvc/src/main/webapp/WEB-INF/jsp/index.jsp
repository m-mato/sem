<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page trimDirectiveWhitespaces="true" %>

<c:if test="${param.login != null}">
    <div class="alert alert-success" role="alert">
        <spring:message code="page.index.login"/>
    </div>
</c:if>
<c:if test="${param.logout != null}">
    <div class="alert alert-success" role="alert">
        <spring:message code="page.index.logout"/>
    </div>
</c:if>

<h2><spring:message code="page.index.welcome"/></h2>
