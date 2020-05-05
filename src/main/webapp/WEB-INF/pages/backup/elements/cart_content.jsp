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
		<fmt:message key="cart.title" />
	</div>
	<c:forEach items="${cart}" var="product">
		<div id="cart">
			<div id="cartName">${product.name }</div>
			<div id="cartPrice">${product.price }
				<fmt:message key="products.money" />
			</div>
			<div>
				<form method="POST" action="Controller">
					<input name="action" type="hidden" value="remove_from_cart" /> <input
						name="product_id" type="hidden" value="${product.id }" /> <input
						id="deleteFromCartButton" type="submit"
						value="<fmt:message key="product.remove" />" />
				</form>
			</div>
		</div>
		<div style="clear: left"></div>
	</c:forEach>
	<div align="center">
		<fmt:message key="cart.price.message" />
		: ${full_price }
		<fmt:message key="products.money" />
	</div>
	<c:choose>
		<c:when test="${user.account < full_price}">
			<center>
				<div
					style="color: red; font-size: 20px; padding-top: 20px; padding-bottom: 20px">
					<fmt:message key="message.little.account" />
				</div>
			</center>
		</c:when>
		<c:otherwise>
			<div align="center">
				<form method="POST" action="Controller">
					<input name="action" type="hidden" value="make_order" /> <input
						name="full_price" type="hidden" value="${full_price }" /> <input
						id="makeOrderButton" type="submit"
						value="<fmt:message key="cart.makeorder" />" />
				</form>
			</div>
		</c:otherwise>
	</c:choose>
</body>
</html>