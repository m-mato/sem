<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page trimDirectiveWhitespaces="true" %>

<c:if test="${param.login != null}">
    <div class="alert alert-success" role="alert">
        <spring:message code="page.my-account.login"/>
    </div>
</c:if>

<p>
    <a href="create-event/${id}" style="float:right;" class="btn btn-primary">Create Event</a>
</p>
<div class="container">
        <p><strong><spring:message code="page.my-account.name"/>:</strong><c:out value=" ${name}"/></p>
        <p><strong><spring:message code="page.my-account.surname"/>:</strong><c:out value=" ${surname}"/></p>
        <p><strong><spring:message code="page.my-account.email"/>:</strong><c:out value=" ${email}"/></p>
        <p><strong><spring:message code="page.my-account.birthdate"/>:</strong><fmt:formatDate type="both" dateStyle="full" value="${birthdate.time}" pattern="dd.MM.yyyy"/></p>
        <br/>

        <table class="table">
            <thead>
                <tr>
                    <th>Event</th>
                    <th>Event description</th>
                    <th>Admin</th>
                    <th>Result</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${results}" var="result">
                    <tr>
                        <td>${result.event.name}</td>
                        <td>${result.event.description}</td>
                        <td>${result.event.admin.surname}, ${result.event.admin.name}</td>
                        <td>${result.position}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <table class="table">
            <thead>
                <tr>
                    <th>Event invitation</th>
                    <th>Event description</th>
                    <th>Admin</th>
                    <th>State</th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${invitations}" var="invite">
                    <tr>
                        <td>${invite.event.name}</td>
                        <td>${invite.event.description}</td>
                        <td>${invite.event.admin.surname}, ${invite.event.admin.name}</td>
                        <td>${invite.state}</td>
                        <td>
                            <c:if test="${invite.state != 'DECLINED'}">

                                        <span>
                                            <a href="accept/${invite.id}" style="float:right;" class="btn btn-primary">Accept</a>
                                            <a href="decline/${invite.id}" style="float:right;" class="btn btn-primary">Decline</a>

                                        </span>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
