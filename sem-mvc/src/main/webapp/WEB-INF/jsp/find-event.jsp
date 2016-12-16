<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page trimDirectiveWhitespaces="true" %>

<spring:url value="/find-event" var="findUrl"/>

<div id="find-event">
    <div class="container">

        <div class="col-md-8">
            <form class="form-horizontal" name="f" action="${findUrl}" method="GET">
                <div class="col-md-5">
                    <input id="search" name="search" type="text" class="form-control input-lg" style="border-color:#006064;" placeholder="<spring:message code="page.find-event.label" />">
                <div>
                <div class="col-md-5">
                    <button class="btn btn-primary center-block" style="color:white; background-color:#006064; margin-top:15px;">Find</button>
                </div>
            </form>
        </div>
        <table class="table" style="margin-top:15px;">
            <c:if test="${not empty events}">
                <div class="col-md-8">
                    <c:forEach items="${events}" var="event">
                        <tr>
                            <td>Event Name: <c:out value="${event.name}"/></td>
                            <td>Event Description: <c:out value="${event.description}"/></td>
                        </tr>
                    </c:forEach>
                </div>
            </c:if>
        </table>
    </div>
</div>
