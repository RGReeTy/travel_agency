<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="ctg" uri="customtags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<ul>
		<li><form action="Controller" method="POST">
				<input name="action" type="hidden" value="show_products" /> <input
					name="product_type" type="hidden" value="Allergies" /> <input
					class="button" type="submit"
					value="<fmt:message key="menu.button.allergies"/>" />
			</form></li>
		<li><form action="Controller" method="POST">
				<input name="action" type="hidden" value="show_products" /> <input
					name="product_type" type="hidden" value="Anti Viral" /> <input
					class="button" type="submit"
					value="<fmt:message key="menu.button.anti"/>" />
			</form></li>
		<li><form action="Controller" method="POST">
				<input name="action" type="hidden" value="show_products" /> <input
					name="product_type" type="hidden" value="Blood Pressure" /> <input
					class="button" type="submit"
					value="<fmt:message key="menu.button.pressure"/>" />
			</form></li>
		<li><form action="Controller" method="POST">
				<input name="action" type="hidden" value="show_products" /> <input
					name="product_type" type="hidden" value="Diabetes" /> <input
					class="button" type="submit"
					value="<fmt:message key="menu.button.diabetes"/>" />
			</form></li>
		<li><form action="Controller" method="POST">
				<input name="action" type="hidden" value="show_products" /> <input
					name="product_type" type="hidden" value="Asthma" /> <input
					class="button" type="submit"
					value="<fmt:message key="menu.button.asthma"/>" />
			</form></li>
		<li><form action="Controller" method="POST">
				<input name="action" type="hidden" value="show_products" /> <input
					name="product_type" type="hidden" value="Muscle Relaxant" /> <input
					class="button" type="submit"
					value="<fmt:message key="menu.button.muscle"/>" />
			</form></li>
		<li><form action="Controller" method="POST">
				<input name="action" type="hidden" value="show_products" /> <input
					name="product_type" type="hidden" value="Depression" /> <input
					class="button" type="submit"
					value="<fmt:message key="menu.button.depression"/>" />
			</form></li>
		<li><form action="Controller" method="POST">
				<input name="action" type="hidden" value="show_products" /> <input
					name="product_type" type="hidden" value="Other" /> <input
					class="button" type="submit"
					value="<fmt:message key="menu.button.other"/>" />
			</form></li>
	</ul>
</body>
</html>