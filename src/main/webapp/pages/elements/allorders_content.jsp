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
<div class="title">
    <fmt:message key="allorders.title"/>
</div>
<div style="width: 100%; padding-left: 15%; padding-top: 30px">
    <table id="orderTable">
        <tr>
            <th><fmt:message key="orders.id"/></th>
            <th><fmt:message key="user.login"/></th>
            <th><fmt:message key="orders.status"/></th>
            <th><fmt:message key="orders.delete"/></th>
        </tr>
        <c:forEach items="${orders}" var="order">
            <tr>
                <td>
                    <form method="POST" action="Controller">
                        <input name="action" type="hidden" value="show_order"/> <input
                            name="order_id" type="hidden" value="${order.id }"/> <input
                            style="border-style: none; cursor: pointer; background-color: white; text-decoration: underline"
                            type="submit" value="${order.id }"/>
                    </form>
                </td>
                <td>${order.user.login }</td>
                <td>${order.status }</td>
                <td>
                    <div style="width: 140px">
                        <form method="POST" action="Controller">
                            <input name="action" type="hidden" value="delete_order"/> <input
                                name="order_id" type="hidden" value="${order.id }"/> <input
                                class="buyButton" type="submit"
                                value="<fmt:message key="orders.delete" />"/>
                        </form>
                    </div>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>