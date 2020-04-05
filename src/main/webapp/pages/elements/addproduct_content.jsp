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
		<fmt:message key="addproduct.title" />
	</div>
	<div style="width: 300px; padding-left: 30%; padding-top: 10px">
		<c:if test="${not empty message }">
			<div style="margin-bottom: 10px; color: black; font-size: 18px">
				<fmt:message key="${message }" />
			</div>
		</c:if>
		<form method="POST" action="Controller">
			<div style="padding-bottom: 5px; padding-top: 5px">
				<b style="color: black; font-size: 14px;">* </b><i
					style="color: black; font-size: 14px;"><fmt:message
						key="addproduct.info.fields" /></i>
			</div>

			<div>
				<input name="action" type="hidden" value="add_product" />
			</div>
			<div style="font-size: 18px; margin-top: 12px; margin-bottom: 5px">
				<b style="color: black; font-size: 14px;">* </b>
				<fmt:message key="product.name" />
			</div>
			<div>
				<input id="regInput" type="text" name="name" />
			</div>
			<div>
				<i style="color: black; font-size: 14px"><fmt:message
						key="addproduct.info.name" /></i>
			</div>

			<div style="font-size: 18px; margin-top: 12px; margin-bottom: 5px">
				<b style="color: black; font-size: 14px;">* </b>
				<fmt:message key="products.price" />
				<fmt:message key="product.money" />
			</div>
			<div>
				<input id="regInput" type="text" name="price" />
			</div>
			<div>
				<i style="color: black; font-size: 14px"><fmt:message
						key="addproduct.info.price" /></i>
			</div>

			<div style="font-size: 18px; margin-top: 12px; margin-bottom: 5px">
				<b style="color: black; font-size: 14px;">* </b>
				<fmt:message key="product.description" />
			</div>
			<div>
				<textarea rows="10" cols="34" name="description">
				</textarea>
			</div>

			<div style="font-size: 18px; margin-top: 12px; margin-bottom: 5px">
				<b style="color: black; font-size: 14px;">* </b>
				<fmt:message key="product.image" />
			</div>
			<div style="font-size: 18px; margin-top: 12px; margin-bottom: 12px">
				<select name="picture"><c:forEach items="${picturePath}"
						var="path">
						<option>${path }</option>
					</c:forEach></select>
			</div>

			<div style="font-size: 18px; margin-top: 12px; margin-bottom: 5px">
				<b style="color: black; font-size: 14px;">* </b>
				<fmt:message key="product.producer" />
			</div>
			<div>
				<input id="regInput" type="text" name="producer" />
			</div>
			<div>
				<i style="color: black; font-size: 14px"><fmt:message
						key="addproduct.info.producer" /></i>
			</div>

			<div style="font-size: 18px; margin-top: 12px; margin-bottom: 5px">
				<b style="color: black; font-size: 14px;">* </b>
				<fmt:message key="products.dosage" />

			</div>
			<div>
				<input id="regInput" type="text" name="dosage" />
			</div>
			<div>
				<i style="color: black; font-size: 14px"><fmt:message
						key="addproduct.info.dosage" /></i>
			</div>

			<div style="font-size: 18px; margin-top: 12px; margin-bottom: 5px">
				<b style="color: black; font-size: 14px;">* </b>
				<fmt:message key="product.disease" />
			</div>
			<div style="font-size: 18px; margin-top: 12px; margin-bottom: 12px">
				<select name="disease"><c:forEach items="${diseases}"
						var="disease">
						<option>${disease}</option>
					</c:forEach></select>
			</div>

			<div style="margin-top: 16px; padding-bottom: 20px">
				<input id="regButton" type="submit"
					value="<fmt:message key="addproduct.button" />" />
			</div>
		</form>
	</div>
</body>
</html>