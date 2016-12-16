<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page trimDirectiveWhitespaces="true" %>

<div id="sports">
    <div class="container">

        <c:if test="${not empty sports}">
            <table class="table table-striped" style="margin-top:15px;">
                <thead>
                    <tr>
                        <td><strong><spring:message code="page.sports.thead.name"/></strong></td>
                        <td><strong><spring:message code="page.sports.thead.desc"/></strong></td>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${sports}" var="sport">
                        <tr>
                            <td><c:out value="${sport.name}"/></td>
                            <td><c:out value="${sport.description}"/></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>

        <button class="btn btn-primary" data-toggle="modal" data-target="#addSportModal" data-backdrop="static">
            <spring:message code="page.sports.button.add"/>
        </button>

        <!-- Modal -->
        <div class="modal fade" id="addSportModal" role="dialog">
            <div class="modal-dialog">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title"><spring:message code="page.sports.modal.title"/></h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal" name="f" action="sport/create" method="POST">
                            <div class="form-group form-group-lg">
                                <label class="col-sm-3 control-label" for="name"><spring:message code="page.sports.modal.label.name"/></label>
                                <div class="col-sm-5">
                                    <input type="text" class="form-control" name="name" id="name"/>
                                </div>
                                <label class="col-sm-3 control-label" for="name"><spring:message code="page.sports.modal.label.desc"/></label>
                                <div class="col-sm-5">
                                    <input type="text" class="form-control" name="description" id="description"/>
                                </div>
                                <div class="col-sm-5 col-sm-offset-3">
                                    <button type="submit" class="btn btn-primary btn-lg"><spring:message code="page.sports.modal.button.submit"/></button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">
                            <spring:message code="page.sports.modal.button.close"/>
                        </button>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>