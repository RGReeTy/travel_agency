<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${sessionScope.locale == 'en'}"> <fmt:setLocale value="en" scope="session"/></c:if>
<c:if test="${sessionScope.locale == 'ru'}"> <fmt:setLocale value="ru" scope="session"/></c:if>
<fmt:setBundle basename="locale"/>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><fmt:message key="main.login"/></title>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
    <link type="text/css" rel="stylesheet" href="../css/login-register.css"/>

    <style>
        .form-group {
            margin-bottom: 15px;
        }

        label {
            margin-bottom: 15px;
        }

        input,
        input::-webkit-input-placeholder {
            font-size: 11px;
            padding-top: 3px;
        }

        .form-control {
            height: auto !important;
            padding: 8px 12px !important;
        }

        .input-group {
            box-shadow: 0px 2px 5px 0px rgba(0, 0, 0, 0.21) !important;
        }

        #button {
            border: 1px solid #ccc;
            margin-top: 28px;
            padding: 6px 12px;
            color: #666;
            text-shadow: 0 1px #fff;
            cursor: pointer;
            border-radius: 3px 3px;
            box-shadow: 0 1px #fff inset, 0 1px #ddd;
            background: #f5f5f5;
            background: -moz-linear-gradient(top, #f5f5f5 0%, #eeeeee 100%);
            background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #f5f5f5), color-stop(100%, #eeeeee));
            background: -webkit-linear-gradient(top, #f5f5f5 0%, #eeeeee 100%);
            background: -o-linear-gradient(top, #f5f5f5 0%, #eeeeee 100%);
            background: -ms-linear-gradient(top, #f5f5f5 0%, #eeeeee 100%);
            background: linear-gradient(top, #f5f5f5 0%, #eeeeee 100%);
            filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#f5f5f5', endColorstr='#eeeeee', GradientType=0);
        }

        .main-form {
            margin-top: 30px;
            margin: 0 auto;
            max-width: 400px;
            padding: 10px 40px;
            background: #009edf;
            color: #FFF;
            text-shadow: none;
            box-shadow: 0px 3px 5px 0px rgba(0, 0, 0, 0.31);
        }

        span.input-group-addon i {
            color: #009edf;
            font-size: 17px;
        }

        .login-button {
            margin-top: 5px;
        }
    </style>
</head>

<body>


<div class="container">
    <div class="row main-form">
        <form class="" method="POST" action="Controller">
            <div>
                <input name="action" type="hidden" value="login"/>
            </div>
            <div class="form-group">
                <label for="name" class="cols-sm-2 control-label"><fmt:message key="reg.login"/></label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                        <input type="text" class="form-control" name="login" id="name"
                               placeholder="<fmt:message key="reg.login" />"
                               pattern="[A-Za-z0-9]{3,}" required title="<fmt:message key="reg.info.login"/>"/>
                    </div>
                </div>
            </div>

            <div class=" form-group">
                <label for="password" class="cols-sm-2 control-label"><fmt:message key="reg.password"/></label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                        <input type="password" class="form-control" name="password" id="password"
                               placeholder="<fmt:message key="reg.password"/>"
                        <%--                               pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{4,}"--%>
                               title="<fmt:message key="reg.info.password"/>" required/>
                    </div>
                </div>

                <div>
                    <input class="btn btn-primary btn-lg btn-block login-button" type="submit" id="button"
                           value="<fmt:message key="menu.button.login" />"/>
                </div>

        </form>

        <div class="form-group ">
            <a href="Controller?action=go_to_page&page=path.page.register" target="_blank" type="button" id="button"
               class="btn btn-primary btn-lg btn-block login-button"><fmt:message key="menu.button.register"/></a>
        </div>


    </div>
</div>

</body>

</html>