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
		<fmt:message key="reg.title" />
	</div>
	<div style="width: 250px; padding-left: 310px; padding-top: 10px">
		<c:if test="${not empty message }">
			<div style="margin-bottom: 10px; color: black; font-size: 18px">
				<fmt:message key="${message }" />
			</div>
		</c:if>
		<form method="POST" action="Controller">
			<div style="padding-bottom: 20px; padding-top: 20px">
				<b style="color: black; font-size: 14px;">* </b><i
					style="color: black; font-size: 14px;"><fmt:message
						key="reg.info.fields" /></i>
			</div>
			<div>
				<input name="action" type="hidden" value="registration" />
			</div>
			<div style="font-size: 18px; margin-bottom: 5px">
				<b style="color: black; font-size: 14px;">* </b>
				<fmt:message key="reg.login" />
			</div>
			<div>
				<input id="regInput" type="text" name="login" />
			</div>
			<div>
				<i style="color: black; font-size: 14px"><fmt:message
						key="reg.info.login" /></i>
			</div>
			<div style="font-size: 18px; margin-top: 12px; margin-bottom: 5px">
				<b style="color: black; font-size: 14px;">* </b>
				<fmt:message key="reg.password" />
			</div>
			<div>
				<input id="regInput" type="password" name="password" />
			</div>
			<div>
				<i style="color: black; font-size: 14px;"> <fmt:message
						key="reg.info.password" />
				</i>
			</div>
			<div style="font-size: 18px; margin-top: 12px; margin-bottom: 5px">
				<b style="color: black; font-size: 14px;">* </b>
				<fmt:message key="reg.password.repeat" />
			</div>
			<div>
				<input id="regInput" type="password" name="password_repeat" />
			</div>
			<div style="font-size: 18px; margin-top: 12px; margin-bottom: 5px">
				<b style="color: black; font-size: 14px;">* </b>
				<fmt:message key="reg.email" />
			</div>
			<div>
				<input id="regInput" type="text" name="email" />
			</div>
			<div>
				<i style="color: black; font-size: 14px;"><fmt:message
						key="reg.info.email" /></i>
			</div>
			<div style="margin-top: 16px">
				<input id="regButton" type="submit"
					value="<fmt:message key="menu.button.register" />" />
			</div>
		</form>
	</div>
</body>
</html>