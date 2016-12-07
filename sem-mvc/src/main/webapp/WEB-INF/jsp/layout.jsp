<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>--%>
<%@ page trimDirectiveWhitespaces="true" %>

<c:set var="tilesTitle"><tiles:getAsString name="title" ignore="true"/></c:set>

<spring:url value="/bootstrap/css/bootstrap.min.css" var="bootstrapCssUrl"/>
<spring:url value="/bootstrap/js/bootstrap.min.js" var="bootstrapJsUrl"/>
<spring:url value="/css/style.css" var="styleCssUrl"/>
<spring:url value="/img/cz.png" var="czImgUrl"/>
<spring:url value="/img/en.png" var="enImgUrl"/>
<spring:url value="/img/sk.png" var="skImgUrl"/>
<spring:url value="/img/favicon.ico" var="faviconImgUrl"/>
<spring:url value="/img/logo.png" var="logoImgUrl"/>
<spring:url value="/js/html5shiv.min.js" var="html5shivJsUrl"/>
<spring:url value="/js/jquery.min.js" var="jqueryJsUrl"/>
<spring:url value="/js/respond.min.js" var="respondJsUrl"/>
<spring:url value="/js/script.js" var="scriptJsUrl"/>

<spring:url value="/" var="frontUrl"/>
<spring:url value="?lang=cs" var="csLangUrl"/>
<spring:url value="?lang=en" var="enLangUrl"/>
<spring:url value="?lang=sk" var="skLangUrl"/>

<!DOCTYPE html>
<html lang="${pageContext.request.locale}">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title><c:if test="${!isFront}"><spring:message code="${tilesTitle}"/> | </c:if><spring:message code="sem"/></title>
        <link type="text/css" rel="stylesheet" media="all" href="${bootstrapCssUrl}">
        <link type="text/css" rel="stylesheet" media="all" href="${styleCssUrl}"/>
        <link rel="shortcut icon" href="${faviconImgUrl}"/>
        <!--[if lt IE 9]>
            <script type="text/javascript" src="${html5shivJsUrl}"></script>
            <script type="text/javascript" src="${respondJsUrl}"></script>
        <![endif]-->

    </head>

    <body <c:if test="${isFront}">class="front"</c:if>>

        <a href="${frontUrl}" title="<spring:message code="link.index"/>"><img src="${logoImgUrl}"/></a>

        <a href="${enLangUrl}" title="<spring:message code="link.lang.en"/>"><img src="${enImgUrl}"/></a>
        <a href="${csLangUrl}" title="<spring:message code="link.lang.cs"/>"><img src="${czImgUrl}"/></a>
        <a href="${skLangUrl}" title="<spring:message code="link.lang.sk"/>"><img src="${skImgUrl}"/></a>

        <h1><spring:message code="sem"/></h1>

        <tiles:insertAttribute name="body"/>

        <script type="text/javascript" src="${jqueryJsUrl}"></script>
        <script type="text/javascript" src="${bootstrapJsUrl}"></script>
        <script type="text/javascript" src="${scriptJsUrl}"></script>

    </body>

</html>
