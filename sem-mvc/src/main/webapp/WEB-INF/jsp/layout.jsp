<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
<spring:url value="/login" var="loginUrl"/>
<spring:url value="/logout" var="logoutUrl"/>
<spring:url value="?lang=cs" var="csLangUrl"/>
<spring:url value="?lang=en" var="enLangUrl"/>
<spring:url value="?lang=sk" var="skLangUrl"/>

<!DOCTYPE html>
<html lang="${language}">

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

        <nav class="navbar navbar-default">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only"><spring:message code="link.nav-toggle"/></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="${frontUrl}" title="<spring:message code="link.index"/>">
                        <img class="navbar-logo" src="${logoImgUrl}"/>
                    </a>
                </div>
                <div class="collapse navbar-collapse" id="navbar">
                    <ul class="nav navbar-nav">
                        <li><a href="${frontUrl}"><spring:message code="link.index"/></a></li>
                        <sec:authorize access="isAnonymous()">
                            <li><a href="${loginUrl}"><spring:message code="link.login"/></a></li>
                        </sec:authorize>
                        <sec:authorize access="isAuthenticated()">
                            <li><a href="${logoutUrl}"><spring:message code="link.logout"/></a></li>
                        </sec:authorize>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="${enLangUrl}" title="<spring:message code="link.lang.en"/>">
                            <img class="navbar-lang" src="${enImgUrl}"/>
                        </a></li>
                        <li><a href="${csLangUrl}" title="<spring:message code="link.lang.cs"/>">
                            <img class="navbar-lang" src="${czImgUrl}"/>
                        </a></li>
                        <li><a href="${skLangUrl}" title="<spring:message code="link.lang.sk"/>">
                            <img class="navbar-lang" src="${skImgUrl}"/>
                        </a></li>
                    </ul>
                </div>
            </div>
        </nav>

        <div class="container">

            <div class="page-header">
                <h1><spring:message code="${tilesTitle}"/></h1>
            </div>

            <div class="content">
                <tiles:insertAttribute name="body"/>
            </div>

        </div>

        <script type="text/javascript" src="${jqueryJsUrl}"></script>
        <script type="text/javascript" src="${bootstrapJsUrl}"></script>
        <script type="text/javascript" src="${scriptJsUrl}"></script>

    </body>

</html>
