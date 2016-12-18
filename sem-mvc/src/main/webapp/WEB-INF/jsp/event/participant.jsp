<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page trimDirectiveWhitespaces="true" %>

<spring:url value="/img/search-icon.jpg" var="searchImgUrl"/>
<form>
    <div class="form-group">

            <select class="custom-select">
                <option selected><spring:message code="page.events.filter.sports.all"/></option>
                <option value="1"><spring:message code="page.events.filter.sports.all"/></option>
                <option value="2">Football</option>
                <option value="3">Tennis</option>
            </select>
            <label class="custom-control custom-checkbox">
                <input type="checkbox" class="custom-control-input">
                <span class="custom-control-indicator"></span>
                <span class="custom-control-description"><spring:message code="page.events.filter.by-date.past"/></span>
            </label>
            <label class="custom-control custom-checkbox">
                <input type="checkbox" class="custom-control-input">
                <span class="custom-control-indicator"></span>
                <span class="custom-control-description"><spring:message code="page.events.filter.by-date.future"/></span>
            </label>
            <input type="text" class="form-control" id="myInput" onkeyup="" placeholder="<spring:message code="page.events.filter.search.placeholder"/>">

    </div>
</form>
<div class="container-fluid">
    <div class="row content">
        <div class="col-sm-3 sidenav">

                    <h2><spring:message code="page.events.list.title"/></h2>
                    <div class="list-group list">
                        <c:forEach items="${events}" var="event" varStatus = "status">
                            <%--<a href="#" ${status.first ? 'class="list-group-item list-group-item-action active event-item"' : 'class = "list-group-item list-group-item-action event-item"'}>--%>
                            <a href="#" class = "list-group-item list-group-item-action event-item"}>
                                <p name="event-id" hidden>${event.id}</p>
                                <h5 class="list-group-item-heading">${event.name}</h5>
                                <p class="list-group-item-text"><fmt:formatDate type="both" dateStyle="full" value="${event.date.time}" pattern="dd.MM.yyyy"/>, <c:out value="${event.city}"/></p>
                            </a>
                        </c:forEach>
                    </div>
        </div>
        <div class="col-sm-9">
            <jsp:include page="detail.jsp">
                <jsp:param name="event" value="${event}"/>
                <jsp:param name="result" value="${result}"/>
            </jsp:include>
        </div>
    </div>
</div>
