<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="ctg" uri="customtags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="css/style.css" />
<link rel="shortcut icon" href="images/favicon.jpg" />
<title>Insert title here</title>
</head>
<body>
	<div class="title">${product.name}</div>
	<form method="POST" action="Controller">
		<div id="detProductImage">
			<center>
				<img src="${product.picturePath}" width="70%" align="middle">
			</center>
		</div>
		<div id="detProductInf">
			<div class="detProductInfBlock">
				<fmt:message key="product.name" />
				${product.name}
			</div>
			<div class="detProductInfBlock">
				<fmt:message key="product.disease" />
				${product.disease}
			</div>
			<div class="detProductInfBlock">
				<fmt:message key="product.producer" />
				${product.producer }
			</div>
			<div class="detProductInfBlock">
				<fmt:message key="product.dosage" />
				${product.dosage }
				<fmt:message key="product.measure" />
			</div>
			<div class="detProductInfBlock">
				<fmt:message key="product.price" />
				${product.price }
				<fmt:message key="products.money" />
			</div>
		</div>
		<div style="clear: left"></div>
		<div id="detProductDescription">
			<fmt:message key="product.description" />
			${product.description}
		</div>
		<div id="detBuy">
			<input name="action" type="hidden" value="add_to_cart" /> <input
				name="product_id" type="hidden" value="${product.id }" /> <input
				class="buyButton" type="submit"
				value="<fmt:message key="products.buy" />" />
		</div>
	</form>
	<c:if test="${sessionScope.user.accessLevel == 1 }">
		<div style="width: 120px; padding-left: 41%; margin-top: 20px">
			<form method="POST" action="Controller">
				<input name="action" type="hidden" value="delete_product" /> <input
					name="product_id" type="hidden" value="${product.id }" /> <input
					class="buyButton" type="submit"
					value="<fmt:message key="product.delete" />" />
			</form>
		</div>
		<div style="width: 120px; padding-left: 41%; margin-top: 20px">
			<form method="POST" action="Controller">
				<input name="action" type="hidden" value="edit_product_page" /> <input
					name="product_id" type="hidden" value="${product.id }" /> <input
					class="buyButton" type="submit"
					value="<fmt:message key="product.edit" />" />
			</form>
		</div>
	</c:if>
	<div style="clear: left"></div>
</body>
</html>