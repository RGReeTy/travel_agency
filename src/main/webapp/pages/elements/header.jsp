<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<div id="header">
    <div>
        <form method="POST" action="Controller">
            <input name="action" type="hidden" value="change_locale"/> <input
                id="enButton" type="submit" name="locale" value="en">
        </form>
        <form method="POST" action="Controller">
            <input name="action" type="hidden" value="change_locale"/> <input
                id="ruButton" type="submit" name="locale" value="ru">
        </form>
    </div>
    <div>
        <form method="POST" action="Controller">
            <input name="action" type="hidden" value="search_products"/> <input
                id="searchField" type="text" name="text_search"
                value="<fmt:message key="field.search" />"/> <input
                id="searchButton" type="submit" value=""/>
        </form>
    </div>
</div>
</body>
</html>