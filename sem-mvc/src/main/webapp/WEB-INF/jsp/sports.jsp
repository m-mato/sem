<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page trimDirectiveWhitespaces="true" %>

<div id="sports">
    <div class="container">
        <table class="table" style="margin-top:15px;">
            <c:if test="${not empty sports}">
                <div class="col-md-8">
                    <c:forEach items="${sports}" var="sport">
                        <tr>
                            <td>Sport Name: <c:out value="${sport.name}"/></td>
                            <td>Sport Description: <c:out value="${sport.description}"/></td>
                        </tr>
                    </c:forEach>
                 </div>
            </c:if>
         </table>
    </div>
</div>