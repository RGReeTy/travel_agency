<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<%@ taglib prefix="ctg" uri="customtags" %>--%>
<%@ page isELIgnored="false" %>

<c:if test="${sessionScope.locale == 'en'}"> <fmt:setLocale value="en" scope="session"/></c:if>
<c:if test="${sessionScope.locale == 'ru'}"> <fmt:setLocale value="ru" scope="session"/></c:if>
<fmt:setBundle basename="locale"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="${param.lang}">
<head>
    <title><fmt:message key="main.account"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Alex+Brush" rel="stylesheet">

    <link rel="stylesheet" href="css/open-iconic-bootstrap.min.css">
    <link rel="stylesheet" href="css/animate.css">

    <link rel="stylesheet" href="css/owl.carousel.min.css">
    <link rel="stylesheet" href="css/owl.theme.default.min.css">
    <link rel="stylesheet" href="css/magnific-popup.css">

    <link rel="stylesheet" href="css/aos.css">

    <link rel="stylesheet" href="css/ionicons.min.css">

    <link rel="stylesheet" href="css/bootstrap-datepicker.css">
    <link rel="stylesheet" href="css/jquery.timepicker.css">


    <link rel="stylesheet" href="css/flaticon.css">
    <link rel="stylesheet" href="css/icomoon.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" type="text/css" href="css/custom_style.css"/>
    <link rel="stylesheet" type="text/css" href="css/table_button_style.css"/>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
    <div class="container">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp"><fmt:message key="main.text"/></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav"
                aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="oi oi-menu"></span> <fmt:message key="main.menu"/>
        </button>

        <div class="collapse navbar-collapse" id="ftco-nav">
            <ul class="navbar-nav ml-auto">
                <li>
                    <div id="header">
                        <form method="POST" action="Controller">
                            <input name="action" type="hidden" value="change_locale"/> <input
                                id="enButton" type="submit" name="locale" value="en">
                            <input name="page" value="path.page.main" type="hidden"/>
                        </form>
                        <form method="POST" action="Controller">
                            <input name="action" type="hidden" value="change_locale"/> <input
                                id="ruButton" type="submit" name="locale" value="ru">
                            <input name="page" value="path.page.main" type="hidden"/>
                        </form>
                    </div>
                </li>
                <li class="nav-item active"><a href="${pageContext.request.contextPath}/index.jsp"
                                               class="nav-link"><fmt:message key="main.home"/></a>
                </li>
                <li class="nav-item"><a href="${pageContext.request.contextPath}/about.jsp"
                                        class="nav-link"><fmt:message key="main.about"/></a></li>
                <li class="nav-item"><a href="Controller?action=show_tours" class="nav-link">
                    <fmt:message key="main.tour"/></a></li>
                <li class="nav-item"><a href="Controller?action=show_all_hotels" class="nav-link">
                    <fmt:message key="main.hotels"/></a></li>
                <li class="nav-item"><a href="${pageContext.request.contextPath}/contact.jsp"
                                        class="nav-link"><fmt:message key="main.contact"/></a></li>


                <c:choose>
                    <c:when test="${empty sessionScope.user}">
                        <li class="nav-item cta"><a href="Controller?action=go_to_page&page=path.page.register"
                                                    class="nav-link"><span><fmt:message key="main.register"/></span></a>
                            <br clear="left">
                            <p align="center">
                                <a href="Controller?action=go_to_page&page=path.page.login"><span><fmt:message
                                        key="main.login"/></span></a>
                            </p></li>
                    </c:when>
                    <c:otherwise>
                        <div>
                            <div style="font-size: 15px; text-align: center; color: limegreen;">
                                <fmt:message key="menu.user.appeal"/>
                                <b style="color: red;">${sessionScope.user.login}</b>
                            </div>
                            <li class="nav-item">
                                <div>
                                    <form method="POST" action="Controller">
                                        <input name="action" type="hidden" value="show_account"/> <input
                                            style="border:1px solid transparent; background-color: #07377d; border-radius: 20px;
                                        color: white; margin-bottom: 3px; cursor: pointer;"
                                            type="submit" name="accounts"
                                            value="<fmt:message key="menu.button.account" />">
                                    </form>
                                </div>
                            </li>
                            <c:choose>
                                <c:when test="${sessionScope.user.level_access < 2 }">
                                    <%--CONTROL PAGE--%>
                                    <form action="Controller" method="POST">
                                        <input name="action" type="hidden" value="control"/> <input
                                            style="border:1px solid transparent; background-color: #07377d; border-radius: 20px;
                                        color: white; margin-bottom: 3px; cursor: pointer;"
                                            type="submit" name="manager"
                                            value="<fmt:message key="menu.button.control"/>"/>
                                    </form>
                                </c:when>
                            </c:choose>

                            <div>
                                <form method="POST" action="Controller">
                                    <input name="action" type="hidden" value="logout"/> <input
                                        style="border:1px solid transparent; background-color: #07377d; border-radius: 20px;
                                        color: white;  cursor: pointer;"
                                        class="button" type="submit" name="log_out"
                                        value="<fmt:message key="menu.button.exit" />">
                                </form>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</nav>
<!-- END nav -->

<div class="hero-wrap js-fullheight" style="background-image: url('images/bg_6.jpg');">
    <div class="overlay"></div>
    <div class="container">
        <div class="row no-gutters slider-text js-fullheight align-items-center justify-content-center"
             data-scrollax-parent="true">
            <div class="col-md-9 ftco-animate text-center" data-scrollax=" properties: { translateY: '70%' }">
                <p class="breadcrumbs" data-scrollax="properties: { translateY: '30%', opacity: 1.6 }"><span
                        class="mr-2"><a href="${pageContext.request.contextPath}/index.jsp"><fmt:message
                        key="main.home"/></a></span>
                    <span><fmt:message key="account.personal.account"/></span></p>
                <h1 class="mb-3 bread" data-scrollax="properties: { translateY: '30%', opacity: 1.6 }">
                    <fmt:message key="account.personal.account"/></h1>
            </div>
        </div>
    </div>
</div>


<c:choose>
    <c:when test="${empty sessionScope.user}">
        <li class="nav-item cta"><a href="Controller?action=go_to_page&page=path.page.register"
                                    class="nav-link"><span><fmt:message key="main.register"/></span></a>
            <br clear="left">
            <p align="center">
                <a href="Controller?action=go_to_page&page=path.page.login"><span><fmt:message
                        key="main.login"/></span></a>
            </p></li>
    </c:when>
    <c:otherwise>
        <div>
            <div style="font-size: 19px; text-align: center; color: limegreen;">
                <br>
            </div>
            <br>
            <c:choose>
                <c:when test="${not empty requestScope.user}">
                    <div id="personal-data">
                        <table class="personal">
                            <tr class="editable">
                                <td class="td_table"><fmt:message key="reg.firstname"/></td>
                                <td class="values" id="firstname_val"><c:out value="${user.firstname}"/></td>
                                <td><input type="text" style="display:none;" placeholder="New firstname"
                                           pattern="([a-zA-Zа-яА-Я]).{2,}" id="firstname" class="new_input"/></td>
                            </tr>
                            <tr class="editable">
                                <td class="td_table"><fmt:message key="reg.lastname"/></td>
                                <td class="values" id="lastname_val"><c:out value="${user.lastname}"/></td>
                                <td><input type="text" style="display:none;" placeholder="New lastname"
                                           pattern="([a-zA-Zа-яА-Я]).{2,}" id="lastname" class="new_input"/></td>
                            </tr>
                            <tr class="editable">
                                <td class="td_table"><fmt:message key="reg.email"/></td>
                                <td class="values" id="email_val"><c:out value="${user.email}"/></td>
                                <td><input type="email" style="display:none;" placeholder="New email" id="email"
                                           title="example@gmail.com" class="new_input"/></td>
                            </tr>
                            <tr class="editable">
                                <td class="td_table"><fmt:message key="reg.phone"/></td>
                                <td class="values" id="phone_val"><c:out value="${user.phone}"/></td>
                                <td><input type="text" style="display:none;" placeholder="New phone"
                                           pattern="\+375[\(]{0,1}\d{2}[\)]{0,1}\d{7}" id="phone" class="new_input"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="td_table"><fmt:message key="page.account.reqList.discount"/></td>
                                <td class="values"><c:out value="${user.id_discount}"/></td>
                            </tr>
                            <tr>
                                <td class="td_table"><fmt:message key="page.manager.totalMoney"/></td>
                                <td class="values"><c:out value="${totalMoneySpent}"/></td>
                            </tr>
                        </table>
                    </div>

                    <div align="center">
                        <button class="select-opt" id="button_edit" onclick="showEditableData()">
                            <fmt:message key="page.button.edit"/>
                        </button>
                        <button class="select-opt" id="applyEditData" onclick="applyEditData()" style="display:none;">
                            <fmt:message key="page.button.apply"/>
                        </button>
                        <button class="select-opt" id="cancelEditData" style="display:none;" onclick="cancelEditData()">
                            <fmt:message key="page.button.cancel"/>
                        </button>
                    </div>
                </c:when>

                <c:otherwise>
                    <h1 align="center">
                        <fmt:message key="page.account.reqList.missing"/>
                    </h1>
                </c:otherwise>
            </c:choose>
        </div>
    </c:otherwise>
</c:choose>
<c:if test="${not empty requestScope.defrayals}">
    <br><br>
    <h3 align="center"><fmt:message key="page.account.reqList"/></h3>
    <table border="1" align="center" width="90%">
        <thead align="center">
        <tr>
            <th scope="col"><fmt:message key="page.account.reqList.id"/></th>
            <th scope="col"><fmt:message key="page.account.reqList.dateOfPayment"/></th>
            <th scope="col"><fmt:message key="page.account.reqList.tour"/></th>
            <th scope="col"><fmt:message key="page.account.reqList.count"/></th>
            <th scope="col"><fmt:message key="page.account.reqList.paymentPercentage"/></th>
            <th scope="col"><fmt:message key="page.account.reqList.discount"/></th>
            <th scope="col"><fmt:message key="page.account.reqList.finalPrice"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="defrayal" items="${defrayals}" varStatus="status">
            <tr align="center">
                <td><c:out value="${defrayal.id}"/></td>
                <td><c:out value="${defrayal.dateOfPayment}"/></td>
                <td><c:out value="${defrayal.tour.title}"/></td>
                <td><c:out value="${defrayal.count}"/></td>
                <td><c:out value="${defrayal.paymentPercentage}"/>%</td>
                <td><c:out value="${defrayal.discount}"/></td>
                <td><c:out value="${defrayal.finalCount}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>

<br><br>
<div style="text-align: center; align-content: center">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp"><fmt:message
            key="menu.button.GoToMain"/></a>
</div>
<br>


<%--Footer start--%>
<footer class="ftco-footer ftco-bg-dark ftco-section">
    <div class="container">
        <div class="row mb-5">
            <div class="col-md">
                <div class="ftco-footer-widget mb-4">
                    <h2 class="ftco-heading-2"><fmt:message key="main.footer.author"/></h2>
                    <p><fmt:message key="main.footer.authorphrase"/></p>
                    <ul class="ftco-footer-social list-unstyled float-md-left float-lft mt-5">
                        <li class="ftco-animate"><a href="#"><span class="icon-twitter"></span></a></li>
                        <li class="ftco-animate"><a href="#"><span class="icon-facebook"></span></a></li>
                        <li class="ftco-animate"><a href="#"><span class="icon-instagram"></span></a></li>
                    </ul>
                </div>
            </div>
            <div class="col-md">
                <div class="ftco-footer-widget mb-4 ml-md-5">
                    <h2 class="ftco-heading-2"><fmt:message key="main.info.title"/></h2>
                    <ul class="list-unstyled">
                        <li><a href="#" class="py-2 d-block"><fmt:message key="main.info.about"/></a></li>
                        <li><a href="#" class="py-2 d-block"><fmt:message key="main.info.service"/></a></li>
                        <li><a href="#" class="py-2 d-block"><fmt:message key="main.info.terms"/></a></li>
                        <li><a href="#" class="py-2 d-block"><fmt:message key="main.info.partner"/></a></li>
                        <li><a href="#" class="py-2 d-block"><fmt:message key="main.info.price"/></a></li>
                        <li><a href="#" class="py-2 d-block"><fmt:message key="main.info.policy"/></a></li>
                    </ul>
                </div>
            </div>
            <div class="col-md">
                <div class="ftco-footer-widget mb-4">
                    <h2 class="ftco-heading-2"><fmt:message key="main.support.title"/></h2>
                    <ul class="list-unstyled">
                        <li><a href="#" class="py-2 d-block"><fmt:message key="main.support.faq"/></a></li>
                        <li><a href="#" class="py-2 d-block"><fmt:message key="main.support.payment"/></a></li>
                        <li><a href="#" class="py-2 d-block"><fmt:message key="main.support.tips"/></a></li>
                        <li><a href="#" class="py-2 d-block"><fmt:message key="main.support.how"/></a></li>
                        <li><a href="#" class="py-2 d-block"><fmt:message key="main.support.contact"/></a></li>
                    </ul>
                </div>
            </div>
            <div class="col-md">
                <div class="ftco-footer-widget mb-4">
                    <h2 class="ftco-heading-2"><fmt:message key="main.footer.question"/></h2>
                    <div class="block-23 mb-3">
                        <ul>
                            <li><span class="icon icon-map-marker"></span><span class="text">
                                <fmt:message key="main.footer.address"/></span>
                            </li>
                            <li><a href="#"><span class="icon icon-phone"></span><span
                                    class="text"><fmt:message key="main.footer.phone"/></span></a></li>
                            <li><a href="#"><span class="icon icon-envelope"></span><span class="text">
                                <fmt:message key="main.footer.email"/></span></a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</footer>


<!-- loader -->
<div id="ftco-loader" class="show fullscreen">
    <svg class="circular" width="48px" height="48px">
        <circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/>
        <circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10"
                stroke="#F96D00"/>
    </svg>
</div>


<script src="js/jquery.min.js"></script>
<script src="js/jquery-migrate-3.0.1.min.js"></script>
<script src="js/popper.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.easing.1.3.js"></script>
<script src="js/jquery.waypoints.min.js"></script>
<script src="js/jquery.stellar.min.js"></script>
<script src="js/owl.carousel.min.js"></script>
<script src="js/jquery.magnific-popup.min.js"></script>
<script src="js/aos.js"></script>
<script src="js/jquery.animateNumber.min.js"></script>
<script src="js/bootstrap-datepicker.js"></script>
<script src="js/jquery.timepicker.min.js"></script>
<script src="js/scrollax.min.js"></script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
<script src="js/google-map.js"></script>
<script src="js/main.js"></script>

<script src="js/personal_js.js"></script>

</body>
</html>