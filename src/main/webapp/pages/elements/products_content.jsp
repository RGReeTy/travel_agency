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
	<div class="title">
		<fmt:message key="products.title" />
	</div>
	<c:forEach items="${products}" var="product">
		<div id="productInf">
			<div id="productImage">
				<center>
					<img src="${product.picturePath}" width="70%" align="middle">
				</center>
			</div>
			<div id="productNameDescription">
				<div id="productName">${product.name}</div>
				<div id="productsShortDescription">${product.description}
					<a href="Controller?action=show_product&product_id=${product.id}"><fmt:message
							key="product.button.more" /></a>
				</div>
			</div>
			<div id="priceBuy">
				<div id="productPrice">${product.price}
					<fmt:message key="products.money" />
				</div>
				<c:if
					test="${product.disease == 'Depression' || product.disease == 'Other'}">
					<form method="POST" action="Controller">
						<input name="action" type="hidden" value="make_recipe" /> <input
							name="product_id" type="hidden" value="${product.id}" /> <input
							class="buyButton" type="submit"
							value="<fmt:message key="get.recipe" />" />
					</form>
				</c:if>
				<div>
					<form method="POST" action="Controller">
						<input name="action" type="hidden" value="add_to_cart" /> <input
							name="product_id" type="hidden" value="${product.id}" /> <input
							class="buyButton" type="submit"
							value="<fmt:message key="products.buy" />" />
					</form>
				</div>
			</div>
		</div>
		<div style="clear: left"></div>
	</c:forEach>
</body>
</html>