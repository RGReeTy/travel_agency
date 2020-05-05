<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>

<c:if test="${sessionScope.locale == 'ru'}"> <fmt:setLocale value="ru" scope="session"/></c:if>
<c:if test="${sessionScope.locale == 'en'}"> <fmt:setLocale value="en" scope="session"/></c:if>
<fmt:setBundle basename="locale"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link type="text/css" rel="stylesheet" href="css/style.css"/>
    <link rel="shortcut icon" href="images/favicon.jpg"/>
    <title><fmt:message key="title.about"/></title>
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
            <div
                    style="width: 100%; text-align: center; font-size: 20px; padding-top: 40px">
                <fmt:message key="author.about"/>
            </div>
            <div
                    style="width: 100%; text-align: center; font-size: 20px; margin-top: 30px">
                <fmt:message key="author.email"/>
            </div>
            <div style="width: 50%; padding-left: 25%; padding-top: 30px">
                <img src="images/favicon.jpg" align="middle" width="100%"></img>
            </div>
        </div>
    </div>
    <div style="clear: left"></div>
    <%@include file="/pages/elements/footer_content.jsp" %>
</div>
</body>
</html>