<%--
  Created by IntelliJ IDEA.
  User: Veronika Aksamitova
--%>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<form class="form-horizontal" name="f" action="/PA165/create" method="POST">
    <div class="form-group form-group-lg">
        <label class="col-sm-3 control-label" for="name"><spring:message code="page.event.create.name"/></label>
        <div class="col-sm-5">
            <input type="text" class="form-control" name="name" id="name"/>
        </div>
    </div>

    <div class="form-group form-group-lg">
        <label class="col-sm-3 control-label" for="event_date"><spring:message code="page.event.create.eventDate"/></label>
        <div class="col-xs-5 date">
                <input type="datetime-local" id="event_date" class="form-control" name="event_date" />
        </div>
    </div>
    <%--div class="form-group form-group-lg">
        <form:label path="sport" class="col-sm-3 control-label" ><spring:message code="page.event.create.sport"/></form:label>
        <div class="col-sm-10">
            <form:select path="sport" cssClass="form-control">
                <c:forEach items="${sports}" var="sport">
                    <form:option value="${sport.id}">${sport.name}</form:option>
                </c:forEach>
            </form:select>
            <form:errors path="sport" cssClass="error"/>
        </div>
    </div--%>


    <div class="form-group form-group-lg">
        <label class="col-sm-3 control-label" for="capacity"><spring:message code="page.event.create.capacity"/></label>
        <div class="col-sm-5">
            <input type="number" class="form-control" name="capacity" id="capacity"/>
        </div>
    </div>
    <div class="form-group form-group-lg">
        <label class="col-sm-3 control-label" for="city"><spring:message code="page.event.create.city"/></label>
        <div class="col-sm-5">
            <input type="text" class="form-control" name="city" id="city"/>
        </div>
    </div>
    <div class="form-group form-group-lg">
        <label class="col-sm-3 control-label" for="address"><spring:message code="page.event.create.address"/></label>
        <div class="col-sm-5">
            <input type="text" class="form-control" name="address" id="address"/>
        </div>
    </div>
    <div class="form-group form-group-lg">
        <label class="col-sm-3 control-label" for="description"><spring:message code="page.event.create.description"/></label>
        <div class="col-sm-5">
            <input type="text" class="form-control" name="description" id="description"/>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-5 col-sm-offset-3">
            <button type="submit" class="btn btn-primary btn-lg"><spring:message code="page.event.create.submit"/></button>
        </div>
    </div>
</form>