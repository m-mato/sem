<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page trimDirectiveWhitespaces="true" %>

<div id="events">
    <div class="container">

        <c:if test="${not empty events}">
            <table class="table table-striped" style="margin-top:15px;">
                <thead>
                <tr>
                    <td><strong><spring:message code="page.events.event_date"/></strong></td>
                    <td><strong><spring:message code="page.events.name"/></strong></td>
                    <td><strong><spring:message code="page.events.sport"/></strong></td>
                    <td><strong><spring:message code="page.events.capacity"/></strong></td>
                    <td><strong><spring:message code="page.events.city"/></strong></td>
                    <td><strong><spring:message code="page.events.address"/></strong></td>
                    <td><strong><spring:message code="page.events.admin"/></strong></td>
                    <td><strong><spring:message code="page.events.description"/></strong></td>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${events}" var="event">
                    <tr>
                        <c:choose>
                            <c:when test="${event.date == null}">
                                <td>&nbsp;</td>
                            </c:when>
                            <c:otherwise>
                                <td>
                                    <fmt:formatDate value="${event.date.time}" type="both" dateStyle="full" pattern="dd.MM.yyyy" />
                                </td>
                            </c:otherwise>
                        </c:choose>
                        <td><c:out value="${event.name}"/></td>
                        <td><c:out value="${event.sport.name}"/></td>
                        <td><c:out value="${event.capacity}"/></td>
                        <td><c:out value="${event.city}"/></td>
                        <td><c:out value="${event.address}"/></td>
                        <td><c:out value="${event.admin.surname}, ${event.admin.name}"/></td>
                        <td><c:out value="${event.description}"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>

        <button class="btn btn-primary" data-toggle="modal" data-target="#addEventModal" data-backdrop="static">
            <spring:message code="page.events.button.add"/>
        </button>

        <!-- Modal -->
        <div class="modal fade" id="addEventModal" role="dialog">
            <div class="modal-dialog">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title"><spring:message code="page.events.modal.title"/></h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal" name="f" action="event/create" method="POST">
                            <div class="form-group form-group-lg">
                                <label class="col-sm-3 control-label" for="name"><spring:message code="page.events.name"/></label>
                                <div class="col-sm-5">
                                    <input type="text" class="form-control" name="name" id="name"/>
                                </div>
                            </div>
                            <div class="form-group form-group-lg">
                                <label class="col-sm-3 control-label" for="capacity"><spring:message code="page.events.capacity"/></label>
                                <div class="col-sm-5">
                                    <input type="number" min="1" step="1" class="form-control" name="capacity" id="capacity"/>
                                </div>
                            </div>
                            <div class="form-group form-group-lg">
                                <label class="col-sm-3 control-label" for="city"><spring:message code="page.events.city"/></label>
                                <div class="col-sm-5">
                                    <input type="text" class="form-control" name="city" id="city"/>
                                </div>
                            </div>
                            <div class="form-group form-group-lg">
                                <label class="col-sm-3 control-label" for="address"><spring:message code="page.events.address"/></label>
                                <div class="col-sm-5">
                                    <input type="text" class="form-control" name="address" id="address"/>
                                </div>
                            </div>
                            <div class="form-group form-group-lg">
                                <label class="col-sm-3 control-label" for="description"><spring:message code="page.events.description"/></label>
                                <div class="col-sm-5">
                                    <input type="text" class="form-control" name="description" id="description"/>
                                </div>
                            </div>
                            <div class="form-group form-group-lg">
                                <div class="col-sm-5 col-sm-offset-3">
                                    <button type="submit" class="btn btn-primary btn-lg"><spring:message code="page.events.modal.button.submit"/></button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">
                            <spring:message code="page.events.modal.button.close"/>
                        </button>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>
