<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--<%@ taglib prefix="ctg" uri="customtags" %>--%>

<c:if test="${sessionScope.locale == 'en'}"> <fmt:setLocale value="en" scope="session"/></c:if>
<c:if test="${sessionScope.locale == 'ru'}"> <fmt:setLocale value="ru" scope="session"/></c:if>
<fmt:setBundle basename="locale"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%--    <link type="text/css" rel="stylesheet" href="../css/style.css"/>--%>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
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

    <title><fmt:message key="main.register"/></title>
</head>
<body>
<div class="container">
    <div class="row main-form">
        <form class="" method="POST" action="Controller">
            <label for="name" class="cols-sm-2 control-label"><h4><fmt:message key="reg.title"/></h4></label>
            <div>
                <input name="action" type="hidden" value="registration"/>
            </div>
            <div class="form-group">
                <label for="name" class="cols-sm-2 control-label"><fmt:message key="reg.login"/></label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                        <input type="text" class="form-control" name="login" id="name" pattern="[A-Za-z0-9]{3,}"
                               placeholder="<fmt:message key="reg.login"/>"
                               required title="<fmt:message key="reg.info.login"/>"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label for="password" class="cols-sm-2 control-label"><fmt:message key="reg.password"/></label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                        <input type="password" class="form-control" name="password" id="password" required
                               pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{4,}"
                               title="<fmt:message key="reg.info.password"/>"
                               placeholder="<fmt:message key="reg.password"/>"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label for="confirm" class="cols-sm-2 control-label"><fmt:message key="reg.password.repeat"/></label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                        <input type="password" class="form-control" name="password_repeat" id="confirm"
                               placeholder="<fmt:message key="reg.password.repeat"/>" required/>
                    </div>
                </div>
            </div>


            <div class="form-group">
                <label for="email" class="cols-sm-2 control-label"><fmt:message key="reg.email"/></label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-envelope fa" aria-hidden="true"></i></span>
                        <input type="email" class="form-control" name="email" id="email" required
                               placeholder="<fmt:message key="reg.info.email"/>"
                               title="example@gmail.com"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label for="firstname" class="cols-sm-2 control-label"><fmt:message key="reg.firstname"/></label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-users fa" aria-hidden="true"></i></span>
                        <input type="text" class="form-control" name="firstname" id="firstname"
                               placeholder="<fmt:message key="reg.firstname"/>"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label for="lastname" class="cols-sm-2 control-label"><fmt:message key="reg.lastname"/></label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-users fa" aria-hidden="true"></i></span>
                        <input type="text" class="form-control" name="lastname" id="lastname"
                               placeholder="<fmt:message key="reg.lastname"/>"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label for="phone" class="cols-sm-2 control-label"><fmt:message key="reg.phone"/></label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-users fa" aria-hidden="true"></i></span>
                        <input type="tel" class="form-control" name="phone" id="phone"
                               placeholder="<fmt:message key="reg.phone"/> +375(29)1234567"
                               pattern="\+375[\(]{0,1}\d{2}[\)]{0,1}\d{7}"
                               title="+375(29)1234567"/>
                    </div>
                </div>
            </div>


            <div class="form-group ">
                <input class="btn btn-primary btn-lg btn-block login-button" type="submit" id="button"
                       value="<fmt:message key="menu.button.register"/>" target="_blank"/>

            </div>


        </form>
    </div>
</div>

</body>
</html>