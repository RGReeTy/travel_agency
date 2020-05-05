<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>

<c:if test="${sessionScope.locale == 'ru'}">
    <fmt:setLocale value="ru" scope="session"/>
</c:if>
<c:if test="${sessionScope.locale == 'en'}">
    <fmt:setLocale value="en" scope="session"/>
</c:if>
<fmt:setBundle basename="locale"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link type="text/css" rel="stylesheet" href="css/style.css"/>
    <link rel="shortcut icon" href="images/favicon.jpg"/>
    <title><fmt:message key="title.main"/></title>
</head>
<body>
<div>
    <%@include file="/pages/elements/header.jsp" %>
</div>
<div id="main">
    <div id="left-content">
        <div id="login">
            <%@include file="/pages/elements/login.jsp" %>
        </div>
        <div id="menu">
            <%@include file="/pages/elements/menu.jsp" %>
        </div>
    </div>
    <div id="right-content">
        <div id="content">
            <%@include file="/pages/elements/detailed_product_content.jsp" %>
        </div>
    </div>
    <div style="clear: left"></div>
    <%@include file="/pages/elements/footer_content.jsp" %>
</div>
</body>
</html>