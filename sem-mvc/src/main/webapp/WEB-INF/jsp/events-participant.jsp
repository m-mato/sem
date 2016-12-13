<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page trimDirectiveWhitespaces="true" %>

<spring:url value="/img/search-icon.jpg" var="searchImgUrl"/>

<div class="container-fluid">
    <div class="row content">
        <div class="col-sm-3 sidenav">
                <div class="container event-item">
                    <h2>Filtering</h2>
                    <input class="event-item" type="text" id="myInput" onkeyup="myFunction()" placeholder="Search for events.." title="Type in a event">
                </div>
                <div class="container">
                <h2>My events</h2>
                <div class="col-xs-12 list-group list">
                    <c:forEach items="${events}" var="event">
                        <a href="#" class="list-group-item event-item">
                            <p name="event-id" hidden>${event.id}</p>
                            <h3 class="list-group-item-heading"><c:out value="${event.name}"/></h3>
                            <hr>
                            <p class="list-group-item-text"><c:out value="${event.sport.name}"/></p>
                            <p class="list-group-item-text"><fmt:formatDate type="both" dateStyle="full" value="${event.date.time}" pattern="dd.MM.yyyy"/>, <c:out value="${event.city}"/></p>
                        </a>
                    </c:forEach>
                </div>
            </div>
        </div>
        <div class="col-sm-9">
            <jsp:include page="event-participant.jsp">
                <jsp:param name="event" value="${event}"/>
            </jsp:include>
        </div>
    </div>
</div>
