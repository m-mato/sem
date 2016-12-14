<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page trimDirectiveWhitespaces="true" %>

<spring:url value="/img/search-icon.jpg" var="searchImgUrl"/>

<div class="container-fluid">
    <div class="row content">
        <div class="col-sm-3 sidenav">
            <form>
                <h2><spring:message code="page.filter.title"/></h2>
                <div class="form-group">
                    <label for="myInput"><spring:message code="page.filter.search.label"/></label>
                    <input type="text" class="form-control" id="myInput" onkeyup="" placeholder="<spring:message code="page.filter.search.placeholder"/>">
                </div>
                <div class="btn-group">
                    <label><spring:message code="page.filter.sports.title"/></label><br>
                    <select class="custom-select">
                        <option selected><spring:message code="page.filter.sports.all"/></option>
                        <option value="1"><spring:message code="page.filter.sports.all"/></option>
                        <option value="2">Football</option>
                        <option value="3">Tennis</option>
                    </select>
                </div>
                <div class="check-box">
                    <label><spring:message code="page.filter.byDate.title"/></label><br>
                    <label class="custom-control custom-checkbox">
                        <input type="checkbox" class="custom-control-input">
                        <span class="custom-control-indicator"></span>
                        <span class="custom-control-description"><spring:message code="page.filter.byDate.past"/></span>
                    </label>
                    <label class="custom-control custom-checkbox">
                        <input type="checkbox" class="custom-control-input">
                        <span class="custom-control-indicator"></span>
                        <span class="custom-control-description"><spring:message code="page.filter.byDate.future"/></span>
                    </label>
                </div>
            </form>
                    <h2><spring:message code="page.list.title"/></h2>
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
            <jsp:include page="event-participant.jsp">
                <jsp:param name="event" value="${event}"/>
                <jsp:param name="result" value="${result}"/>
            </jsp:include>
        </div>
    </div>
</div>
