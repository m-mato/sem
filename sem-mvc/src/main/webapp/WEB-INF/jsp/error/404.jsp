<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page trimDirectiveWhitespaces="true" %>

<p><spring:message code="page.error.404.message"/></p>

<button type="button" class="btn btn-warning" onclick="window.history.back()">
    <spring:message code="link.back"/>
</button>
