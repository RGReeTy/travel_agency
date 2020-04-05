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
		<fmt:message key="title.account" />
	</div>
	<div style="width: 100%; font-size: 20px; padding-top: 30px">
		<center>
			<fmt:message key="message.account.price" />
			: ${user.account }
			<fmt:message key="products.money" />
		</center>
	</div>
	<div style="width: 100%; font-size: 20px; padding-top: 30px">
		<center>
			<fmt:message key="message.account.add" />
		</center>
	</div>
	<div>
		<form method="POST" action="Controller">
			<div>
				<input name="action" type="hidden" value="add_account" /> <input
					id="account" type="text" name="account"
					value="<fmt:message key="field.account" />" />
			</div>
			<div>
				<input class="button" id="accountButton" type="submit" name="refill"
					value="<fmt:message key="menu.button.refill" />">
			</div>
		</form>
	</div>
</body>
</html>