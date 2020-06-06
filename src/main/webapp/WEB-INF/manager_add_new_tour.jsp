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

    <title><fmt:message key="page.manager.cr8ingTour"/></title>
</head>
<body>

<div class="container">
    <div class="row main-form">
        <form class="" method="POST" action="Controller">
            <label for="title" class="cols-sm-2 control-label"><h4><fmt:message key="page.manager.cr8ingTour"/></h4>
            </label>
            <div>
                <input name="action" type="hidden" value="CREATE_TOUR"/>
            </div>
            <div class="form-group">
                <label for="title" class="cols-sm-2 control-label"><fmt:message
                        key="page.manager.cr8ingTour.title"/></label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                        <input type="text" class="form-control" name="title" id="title"
                               placeholder="<fmt:message key="page.manager.cr8ingTour.title"/>"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label for="price" class="cols-sm-2 control-label"><fmt:message
                        key="page.manager.cr8ingTour.price"/></label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                        <input type="number" class="form-control" name="price" id="price" required
                               step="0.01" min="0" value="100"
                               title="<fmt:message key="page.manager.cr8ingTour.price"/>"
                               placeholder="<fmt:message key="page.manager.cr8ingTour.price"/>"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label for="typeOfTour" class="cols-sm-2 control-label"><fmt:message
                        key="page.manager.cr8ingTour.type"/></label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                        <p><select name="typeOfTour" id="typeOfTour" size="4"
                                   name="<fmt:message key="page.manager.cr8ingTour.type"/>">
                            <c:forEach var="type" items="${typeOfTourMap}" varStatus="status">
                                <option value="${type.key}"><fmt:message key="menu.button.${type.value}"/></option>
                            </c:forEach>
                        </select></p>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label for="hotTour" class="cols-sm-2 control-label"><fmt:message
                        key="page.manager.cr8ingTour.hotTour"/></label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-envelope fa" aria-hidden="true"></i></span>
                        <label name="hotTour" id="hotTour"><input type="radio" name="true"/>
                            <fmt:message key="page.manager.cr8ingTour.true"/></label>
                        <label name="hotTour"><input type="radio" checked name="false"/>
                            <fmt:message key="page.manager.cr8ingTour.false"/></label>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label for="numberOfPlaces" class="cols-sm-2 control-label">
                    <fmt:message key="page.manager.cr8ingTour.numberOfPlaces"/></label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                        <input type="number" class="form-control" name="numberOfPlaces" id="numberOfPlaces" required
                               step="1" min="0" value="10"
                               title="<fmt:message key="page.manager.cr8ingTour.numberOfPlaces"/>"
                               placeholder="<fmt:message key="page.manager.cr8ingTour.numberOfPlaces"/>"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label for="dateStart" class="cols-sm-2 control-label">
                    <fmt:message key="page.manager.cr8ingTour.dateStart"/></label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-users fa" aria-hidden="true"></i></span>
                        <input type="date" class="form-control" name="dateStart" id="dateStart"
                               value="DD-MM-YYYY"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label for="dateEnd" class="cols-sm-2 control-label">
                    <fmt:message key="page.manager.cr8ingTour.dateEnd"/></label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-users fa" aria-hidden="true"></i></span>
                        <input type="date" class="form-control" name="dateEnd" id="dateEnd"
                               value="DD-MM-YYYY"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label for="discount" class="cols-sm-2 control-label">
                    <fmt:message key="page.manager.cr8ingTour.discount"/></label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                        <p><select name="discount" id="discount" size="5"
                                   name="<fmt:message key="page.manager.cr8ingTour.discount"/>">
                            <c:forEach var="discount" items="${discounts}" varStatus="status">
                                <option value="${discount.key}">${discount.value}</option>
                            </c:forEach>
                        </select></p>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label for="hotel" class="cols-sm-2 control-label">
                    <fmt:message key="page.manager.cr8ingTour.hotel"/></label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                        <p><select name="hotel" id="hotel" size="5"
                                   name="<fmt:message key="page.manager.cr8ingTour.hotel"/>">
                            <c:forEach var="hotel" items="${hotelSet}" varStatus="status">
                                <option value="${hotel.id}">${hotel.title}</option>
                            </c:forEach>
                        </select></p>
                    </div>
                </div>
            </div>

            <div class="form-group ">
                <input class="btn btn-primary btn-lg btn-block login-button" type="submit" id="button"
                       value="<fmt:message key="page.manager.cr8ingTour.create"/>" target="_blank"/>

            </div>


        </form>
    </div>
</div>

</body>
</html>