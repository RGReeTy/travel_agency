<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="ctg" uri="customtags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="title">
		<fmt:message key="order.title" />
		${order_id }
	</div>
	<form method="POST" action="Controller">
		<c:forEach items="${products}" var="product">
			<div id="cart">
				<div id="cartName">${product.name }</div>
				<div id="cartPrice">${product.price }
					<fmt:message key="products.money" />
				</div>
			</div>
			<div style="clear: left"></div>
		</c:forEach>
	</form>
	<div align="center">
		<fmt:message key="cart.price.message" />
		: ${full_price }
		<fmt:message key="products.money" />
	</div>
	<div align="center">
		<input id="makeOrderButton" type="button" onclick="history.back()"
			value="<fmt:message key="order.back.button" />">
	</div>
</body>
</html>