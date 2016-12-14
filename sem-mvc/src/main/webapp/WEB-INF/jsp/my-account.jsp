<%--
  Created by IntelliJ IDEA.
  User: Veronika Aksamitova
--%>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page trimDirectiveWhitespaces="true" %>

<%--c:if test="${param.error != null}">
       <div class="alert alert-danger" role="alert">
          <spring:message code="page.error.403.message"/>
       </div>
</c:if--%>

<div class="container">
        <p><strong><spring:message code="page.name"/>:</strong><c:out value=" ${name}"/></p>
        <p><strong><spring:message code="page.surname"/>:</strong><c:out value=" ${surname}"/></p>
        <p><strong><spring:message code="page.email"/>:</strong><c:out value=" ${email}"/></p>
        <p><strong><spring:message code="page.birthdate"/>:</strong><fmt:formatDate type="both" dateStyle="full" value="${birthdate}" pattern="dd.MM.yyyy"/></p>
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
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${invitations}" var="invite">
                    <tr>
                        <td>${invite.event.name}</td>
                        <td>${invite.event.description}</td>
                        <td>${invite.event.admin.surname}, ${invite.event.admin.name}</td>
                        <td>${invite.state}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>