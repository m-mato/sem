<%--
  Created by IntelliJ IDEA.
  User: Veronika Aksamitova
--%>
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker.min.css" />
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker3.min.css" />

<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.min.js"></script>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<form class="form-horizontal" name="f" action="create" method="POST">
    <div class="form-group form-group-lg">
        <label class="col-sm-3 control-label" for="name"><spring:message code="page.createEvent.name"/></label>
        <div class="col-sm-5">
            <input type="text" class="form-control" name="name" id="name"/>
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-3 control-label" for="event_date"><spring:message code="page.createEvent.eventDate"/></label>
        <div class="col-xs-5 date">
            <div class="input-group input-append date" id="event_date">
                <input type=text  data-date-format='yy-mm-dd'  >
                <input type="text" class="form-control" name="event_date" />
                <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
            </div>
        </div>
    </div>
    <div class="form-group">
        <form:label path="sport" class="col-sm-3 control-label" ><spring:message code="page.createEvent.sport"/></form:label>
        <div class="col-sm-10">
            <form:select path="sport" cssClass="form-control">
                <c:forEach items="${sports}" var="sport">
                    <form:option value="${sport.id}">${sport.name}</form:option>
                </c:forEach>
            </form:select>
            <form:errors path="sport" cssClass="error"/>
        </div>
    </div>


    <div class="form-group form-group-lg">
        <label class="col-sm-3 control-label" for="capacity"><spring:message code="page.createEvent.capacity"/></label>
        <div class="col-sm-5">
            <input type="number" class="form-control" name="capacity" id="capacity"/>
        </div>
    </div>
    <div class="form-group form-group-lg">
        <label class="col-sm-3 control-label" for="city"><spring:message code="page.createEvent.city"/></label>
        <div class="col-sm-5">
            <input type="text" class="form-control" name="city" id="city"/>
        </div>
    </div>
    <div class="form-group form-group-lg">
        <label class="col-sm-3 control-label" for="address"><spring:message code="page.createEvent.address"/></label>
        <div class="col-sm-5">
            <input type="text" class="form-control" name="address" id="address"/>
        </div>
    </div>
    <div class="form-group form-group-lg">
        <label class="col-sm-3 control-label" for="description"><spring:message code="page.createEvent.description"/></label>
        <div class="col-sm-5">
            <input type="text" class="form-control" name="description" id="description"/>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-5 col-sm-offset-3">
            <button type="submit" class="btn btn-primary btn-lg"><spring:message code="page.createEvent.submit"/></button>
        </div>
    </div>
</form>
