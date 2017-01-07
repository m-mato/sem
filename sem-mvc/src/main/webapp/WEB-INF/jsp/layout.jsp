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

<spring:url value="/js/datepicker/css/bootstrap-datepicker3.min.css" var="datepickerCssUrl"/>
<spring:url value="/js/datepicker/js/bootstrap-datepicker.min.js" var="datepickerJsUrl"/>
<spring:url value="/js/datepicker/locales/bootstrap-datepicker.${language}.min.js" var="datepickerLocaleJsUrl"/>
<spring:url value="/js/html5shiv/html5shiv.min.js" var="html5shivJsUrl"/>
<spring:url value="/js/jquery/jquery.min.js" var="jqueryJsUrl"/>
<spring:url value="/js/respond/respond.min.js" var="respondJsUrl"/>
<spring:url value="/js/select2/css/select2.min.css" var="select2CssUrl"/>
<spring:url value="/js/select2/js/select2.min.js" var="select2JsUrl"/>
<spring:url value="/js/script.js" var="scriptJsUrl"/>

<spring:url value="/" var="frontUrl"/>
<spring:url value="/my-account" var="userDetailUrl"/>
<spring:url value="/login" var="userLoginUrl"/>
<spring:url value="/logout" var="userLogoutUrl"/>
<spring:url value="/register" var="userRegisterUrl"/>
<spring:url value="/events" var="eventsUrl"/>
<spring:url value="/results" var="resultsUrl"/>
<spring:url value="/sports" var="sportsUrl"/>
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
        <link type="text/css" rel="stylesheet" media="all" href="${datepickerCssUrl}">
        <link type="text/css" rel="stylesheet" media="all" href="${select2CssUrl}">
        <link type="text/css" rel="stylesheet" media="all" href="${styleCssUrl}"/>
        <link rel="shortcut icon" href="${faviconImgUrl}"/>
        <!--[if lt IE 9]>
            <script type="text/javascript" src="${html5shivJsUrl}"></script>
            <script type="text/javascript" src="${respondJsUrl}"></script>
        <![endif]-->

    </head>

    <body <c:if test="${isFront}">class="front"</c:if>>

        <nav class="navbar navbar-default navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                            aria-expanded="false" aria-controls="navbar">
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
                        <li><a href="${eventsUrl}"><spring:message code="link.event.list"/></a></li>
                        <sec:authorize access="isAuthenticated()">
                            <li><a href="${resultsUrl}"><spring:message code="link.result.list"/></a></li>
                        </sec:authorize>
                        <li><a href="${sportsUrl}"><spring:message code="link.sport.list"/></a></li>
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
                        <sec:authorize access="isAnonymous()">
                            <li><a href="${userLoginUrl}"><spring:message code="link.user.login"/></a></li>
                            <li><a href="${userRegisterUrl}"><spring:message code="link.user.register"/></a></li>
                        </sec:authorize>
                        <sec:authorize access="isAuthenticated()">
                            <li><a href="${userDetailUrl}"><spring:message code="link.user.detail"/></a></li>
                            <li><a href="${userLogoutUrl}"><spring:message code="link.user.logout"/> -
                                <strong>${loggedUser.name}</strong></a></li>
                        </sec:authorize>
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

        <footer class="page-footer navbar-fixed-bottom">
            <div class="container">
                <p class="footer-text">&copy; 2016 Gold team PA165</p>
            </div>
        </footer>

        <script type="text/javascript" src="${jqueryJsUrl}"></script>
        <script type="text/javascript" src="${bootstrapJsUrl}"></script>
        <script type="text/javascript" src="${datepickerJsUrl}"></script>
        <c:if test="${language != 'en'}">
            <script type="text/javascript" src="${datepickerLocaleJsUrl}"></script>
        </c:if>
        <script type="text/javascript" src="${select2JsUrl}"></script>
        <script type="text/javascript" src="${scriptJsUrl}"></script>
    </body>

</html>
