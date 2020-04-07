<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<ul>
    <li>
        <form action="Controller" method="POST">
            <input name="action" type="hidden" value="show_products"/> <input
                name="product_type" type="hidden" value="Excursion"/> <input
                class="button" type="submit"
                value="<fmt:message key="menu.button.excursion"/>"/>
        </form>
    </li>
    <li>
        <form action="Controller" method="POST">
            <input name="action" type="hidden" value="show_products"/> <input
                name="product_type" type="hidden" value="Vacation"/> <input
                class="button" type="submit"
                value="<fmt:message key="menu.button.vacation"/>"/>
        </form>
    </li>
    <li>
        <form action="Controller" method="POST">
            <input name="action" type="hidden" value="show_products"/> <input
                name="product_type" type="hidden" value="Shopping"/> <input
                class="button" type="submit"
                value="<fmt:message key="menu.button.shopping"/>"/>
        </form>
    </li>

</ul>
</body>
</html>