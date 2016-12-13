<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<spring:url value="/img/search-icon.jpg" var="searchImgUrl"/>





<div id="event-detail">
<h2><c:out value="${event.name}"/></h2>
    <p><strong>Dátum konania:</strong><fmt:formatDate type="both" dateStyle="full" value="${event.date.time}"/></p>
    <p><strong>Adresa:</strong><c:out value="${event.address}, ${event.city}"/></p>
<p><strong>Organizátor:</strong><c:out value="${event.admin.name}, ${event.admin.email}"/></p>
    <p><strong>Sport:</strong><c:out value="${event.sport.name}"/></p>
    <p><strong>Kapacita:</strong><c:out value="${fn:length(event.participants)}/${event.capacity}"/></p>
    <p><c:out value="${event.description}"/>Food is my passion. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
<br><br>
</div>