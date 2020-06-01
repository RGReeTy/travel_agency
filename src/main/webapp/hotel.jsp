<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<%@ taglib prefix="ctg" uri="customtags" %>--%>
<%@ page isELIgnored="false" %>

<c:if test="${sessionScope.locale == 'en'}"> <fmt:setLocale value="en" scope="session"/></c:if>
<c:if test="${sessionScope.locale == 'ru'}"> <fmt:setLocale value="ru" scope="session"/></c:if>
<fmt:setBundle basename="locale"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- <html lang="en"> -->
<html lang="${param.lang}">
<head>
    <title><fmt:message key="main.text"/></title>
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
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
    <div class="container">
        <a class="navbar-brand" href="index.jsp"><fmt:message key="main.text"/></a>
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
                        </form>
                        <form method="POST" action="Controller">
                            <input name="action" type="hidden" value="change_locale"/> <input
                                id="ruButton" type="submit" name="locale" value="ru">
                        </form>
                    </div>
                </li>
                <li class="nav-item active"><a href="Controller" class="nav-link"><fmt:message key="main.home"/></a></li>
                <li class="nav-item"><a href="about.jsp" class="nav-link"><fmt:message key="main.about"/></a></li>
                <li class="nav-item"><a href="Controller?action=show_tours" class="nav-link">
                    <fmt:message key="main.tour"/></a></li>
                <li class="nav-item"><a href="Controller?action=show_all_hotels" class="nav-link">
                    <fmt:message key="main.hotels"/></a></li>
                <li class="nav-item"><a href="contact.jsp" class="nav-link"><fmt:message key="main.contact"/></a></li>


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
                                <%--CONTROL PAGE--%>
                            <form action="Controller" method="POST">
                                <input name="action" type="hidden" value="control"/> <input
                                    style="border:1px solid transparent; background-color: #07377d; border-radius: 20px;
                                        color: white; margin-bottom: 3px; cursor: pointer;"
                                    type="submit" name="manager"
                                    value="<fmt:message key="menu.button.control"/>"/>
                            </form>
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

<div class="hero-wrap js-fullheight" style="background-image: url('images/bg_5.jpg');">
    <div class="overlay"></div>
    <div class="container">
        <div class="row no-gutters slider-text js-fullheight align-items-center justify-content-center"
             data-scrollax-parent="true">
            <div class="col-md-9 ftco-animate text-center" data-scrollax=" properties: { translateY: '70%' }">
                <p class="breadcrumbs" data-scrollax="properties: { translateY: '30%', opacity: 1.6 }"><span
                        class="mr-2"><a href="index.jsp"><fmt:message key="main.home"/></a></span>
                    <span><fmt:message key="main.hotels"/></span></p>
                <h1 class="mb-3 bread" data-scrollax="properties: { translateY: '30%', opacity: 1.6 }">
                    <fmt:message key="main.hotels"/></h1>
            </div>
        </div>
    </div>
</div>


<%--<c:choose>--%>
<%--    <c:when test="${not empty requestScope.hotels}">--%>
<%--        <h1 align="center">Hotel list:</h1>--%>
<%--        <table border="1" align="center" width="90%">--%>
<%--            <thead align="center">--%>
<%--            <tr>--%>
<%--                <th scope="col">ID</th>--%>
<%--                <th scope="col">Title</th>--%>
<%--                <th scope="col">Country</th>--%>
<%--                <th scope="col">City</th>--%>
<%--                <th scope="col">Stars</th>--%>
<%--                <th scope="col">Free rooms</th>--%>
<%--                <th scope="col">Nutrition</th>--%>
<%--            </tr>--%>
<%--            </thead>--%>
<%--            <tbody>--%>
<%--            <c:forEach var="hotels" items="${hotels}" varStatus="status">--%>
<%--                <tr align="center">--%>
<%--                    <td><c:out value="${hotels.id}"/></td>--%>
<%--                    <td><c:out value="${hotels.title}"/></td>--%>
<%--                    <td><c:out value="${hotels.country}"/></td>--%>
<%--                    <td><c:out value="${hotels.city}"/></td>--%>
<%--                    <td><c:out value="${hotels.stars}"/></td>--%>
<%--                    <td><c:out value="${hotels.freeRooms}"/></td>--%>
<%--                    <td><c:out value="${hotels.nutrition}"/></td>--%>
<%--                </tr>--%>
<%--            </c:forEach>--%>
<%--            </tbody>--%>
<%--        </table>--%>
<%--    </c:when>--%>
<%--</c:choose>--%>


<c:choose>
    <c:when test="${not empty requestScope.hotels}">
        <h1 align="center"><fmt:message key="page.tour.list.head"/></h1>
        <hr width="90%" align="center" size="5">
        <section class="ftco-section ftco-degree-bg">
        <div class="container">
        <div class="row">
        <div class="col-lg-9">
        <div class="row">
        <c:forEach var="hotels" items="${hotels}" varStatus="status">
            <div class="col-md-4 ftco-animate">
                <div class="destination">
                    <a href="#"
                       class="img img-2 d-flex justify-content-center align-items-center"
                       style="background-image: url(images/hotel-1.jpg);">
                        <div class="icon d-flex justify-content-center align-items-center">
                            <span class="icon-search2"></span>
                        </div>
                    </a>
                    <div class="text p-3">
                        <div class="d-flex">
                            <div class="one">
                                <h3><a href="#"><fmt:message key="page.tour.list.title"/> <c:out
                                        value="${hotels.title}"/>
                                    , <fmt:message key="page.tour.list.country"/> <c:out value="${hotels.country}"/></a>
                                </h3>
                                <p class="rate">
                                    <i class="icon-star"></i>
                                    <i class="icon-star"></i>
                                    <i class="icon-star"></i>
                                    <i class="icon-star"></i>
                                    <i class="icon-star-o"></i>
                                    <span>8 Rating</span>
                                </p>
                            </div>
                            <div class="two">
                                <span class="price per-price">$100<br><small>/night</small></span>
                            </div>
                        </div>
                        <p><fmt:message key="page.tour.list.city"/> <c:out value="${hotels.city}"/></p>
                        <p class="bottom-area d-flex">
                            <span><i class="icon-map-o"></i> <fmt:message key="page.tour.list.stars"/> <c:out
                                    value="${hotels.stars}"/></span>
                            <span class="ml-auto"><a href="#"><fmt:message key="page.tour.list.nutrition"/> <c:out
                                    value="${hotels.nutrition}"/></a>
                            <c:if test="${sessionScope.user.level_access == 1 }">
                                <fmt:message key="page.tour.list.manager.info"/>
                                <fmt:message key="page.tour.list.manager.id"/> <c:out value="${hotels.id}"/>
                                <fmt:message key="page.tour.list.manager.freeRooms"/> <c:out
                                    value="${hotels.freeRooms}"/>
                            </c:if>
                            </span>
                        </p>
                    </div>
                </div>
            </div>
            </div>
            <%--            <div class="row mt-5">--%>
            <%--                <div class="col text-center">--%>
            <%--                    <div class="block-27">--%>
            <%--                        <ul>--%>
            <%--                            <li><a href="#">&lt;</a></li>--%>
            <%--                            <li class="active"><span>1</span></li>--%>
            <%--                            <li><a href="#">2</a></li>--%>
            <%--                            <li><a href="#">3</a></li>--%>
            <%--                            <li><a href="#">4</a></li>--%>
            <%--                            <li><a href="#">5</a></li>--%>
            <%--                            <li><a href="#">&gt;</a></li>--%>
            <%--                        </ul>--%>
            <%--                    </div>--%>
            <%--                </div>--%>
            <%--            </div>--%>
            </div> <!-- .col-md-8 -->
            </div>
            </div>
            </section> <!-- .section -->
        </c:forEach>
    </c:when>
</c:choose>
<%--<div class="col-md-4 ftco-animate">--%>
<%--    <div class="destination">--%>
<%--        <a href="hotel-single.html"--%>
<%--           class="img img-2 d-flex justify-content-center align-items-center"--%>
<%--           style="background-image: url(images/hotel-2.jpg);">--%>
<%--            <div class="icon d-flex justify-content-center align-items-center">--%>
<%--                <span class="icon-search2"></span>--%>
<%--            </div>--%>
<%--        </a>--%>
<%--        <div class="text p-3">--%>
<%--            <div class="d-flex">--%>
<%--                <div class="one">--%>
<%--                    <h3><a href="hotel-single.html">Hotel, Italy</a></h3>--%>
<%--                    <p class="rate">--%>
<%--                        <i class="icon-star"></i>--%>
<%--                        <i class="icon-star"></i>--%>
<%--                        <i class="icon-star"></i>--%>
<%--                        <i class="icon-star"></i>--%>
<%--                        <i class="icon-star-o"></i>--%>
<%--                        <span>8 Rating</span>--%>
<%--                    </p>--%>
<%--                </div>--%>
<%--                <div class="two">--%>
<%--                    <span class="price per-price">$40<br><small>/night</small></span>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <p>Far far away, behind the word mountains, far from the countries</p>--%>
<%--            <hr>--%>
<%--            <p class="bottom-area d-flex">--%>
<%--                <span><i class="icon-map-o"></i> Miami, Fl</span>--%>
<%--                <span class="ml-auto"><a href="#">Book Now</a></span>--%>
<%--            </p>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>
<%--<div class="col-md-4 ftco-animate">--%>
<%--    <div class="destination">--%>
<%--        <a href="hotel-single.html"--%>
<%--           class="img img-2 d-flex justify-content-center align-items-center"--%>
<%--           style="background-image: url(images/hotel-3.jpg);">--%>
<%--            <div class="icon d-flex justify-content-center align-items-center">--%>
<%--                <span class="icon-search2"></span>--%>
<%--            </div>--%>
<%--        </a>--%>
<%--        <div class="text p-3">--%>
<%--            <div class="d-flex">--%>
<%--                <div class="one">--%>
<%--                    <h3><a href="hotel-single.html">Hotel, Italy</a></h3>--%>
<%--                    <p class="rate">--%>
<%--                        <i class="icon-star"></i>--%>
<%--                        <i class="icon-star"></i>--%>
<%--                        <i class="icon-star"></i>--%>
<%--                        <i class="icon-star"></i>--%>
<%--                        <i class="icon-star-o"></i>--%>
<%--                        <span>8 Rating</span>--%>
<%--                    </p>--%>
<%--                </div>--%>
<%--                <div class="two">--%>
<%--                    <span class="price per-price">$40<br><small>/night</small></span>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <p>Far far away, behind the word mountains, far from the countries</p>--%>
<%--            <hr>--%>
<%--            <p class="bottom-area d-flex">--%>
<%--                <span><i class="icon-map-o"></i> Miami, Fl</span>--%>
<%--                <span class="ml-auto"><a href="#">Book Now</a></span>--%>
<%--            </p>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>
<%--<div class="col-md-4 ftco-animate">--%>
<%--    <div class="destination">--%>
<%--        <a href="hotel-single.html"--%>
<%--           class="img img-2 d-flex justify-content-center align-items-center"--%>
<%--           style="background-image: url(images/hotel-4.jpg);">--%>
<%--            <div class="icon d-flex justify-content-center align-items-center">--%>
<%--                <span class="icon-search2"></span>--%>
<%--            </div>--%>
<%--        </a>--%>
<%--        <div class="text p-3">--%>
<%--            <div class="d-flex">--%>
<%--                <div class="one">--%>
<%--                    <h3><a href="hotel-single.html">Hotel, Italy</a></h3>--%>
<%--                    <p class="rate">--%>
<%--                        <i class="icon-star"></i>--%>
<%--                        <i class="icon-star"></i>--%>
<%--                        <i class="icon-star"></i>--%>
<%--                        <i class="icon-star"></i>--%>
<%--                        <i class="icon-star-o"></i>--%>
<%--                        <span>8 Rating</span>--%>
<%--                    </p>--%>
<%--                </div>--%>
<%--                <div class="two">--%>
<%--                    <span class="price per-price">$40<br><small>/night</small></span>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <p>Far far away, behind the word mountains, far from the countries</p>--%>
<%--            <hr>--%>
<%--            <p class="bottom-area d-flex">--%>
<%--                <span><i class="icon-map-o"></i> Miami, Fl</span>--%>
<%--                <span class="ml-auto"><a href="#">Book Now</a></span>--%>
<%--            </p>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>
<%--<div class="col-md-4 ftco-animate">--%>
<%--    <div class="destination">--%>
<%--        <a href="hotel-single.html"--%>
<%--           class="img img-2 d-flex justify-content-center align-items-center"--%>
<%--           style="background-image: url(images/hotel-5.jpg);">--%>
<%--            <div class="icon d-flex justify-content-center align-items-center">--%>
<%--                <span class="icon-search2"></span>--%>
<%--            </div>--%>
<%--        </a>--%>
<%--        <div class="text p-3">--%>
<%--            <div class="d-flex">--%>
<%--                <div class="one">--%>
<%--                    <h3><a href="hotel-single.html">Hotel, Italy</a></h3>--%>
<%--                    <p class="rate">--%>
<%--                        <i class="icon-star"></i>--%>
<%--                        <i class="icon-star"></i>--%>
<%--                        <i class="icon-star"></i>--%>
<%--                        <i class="icon-star"></i>--%>
<%--                        <i class="icon-star-o"></i>--%>
<%--                        <span>8 Rating</span>--%>
<%--                    </p>--%>
<%--                </div>--%>
<%--                <div class="two">--%>
<%--                    <span class="price per-price">$40<br><small>/night</small></span>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <p>Far far away, behind the word mountains, far from the countries</p>--%>
<%--            <hr>--%>
<%--            <p class="bottom-area d-flex">--%>
<%--                <span><i class="icon-map-o"></i> Miami, Fl</span>--%>
<%--                <span class="ml-auto"><a href="#">Book Now</a></span>--%>
<%--            </p>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>
<%--<div class="col-md-4 ftco-animate">--%>
<%--    <div class="destination">--%>
<%--        <a href="hotel-single.html"--%>
<%--           class="img img-2 d-flex justify-content-center align-items-center"--%>
<%--           style="background-image: url(images/hotel-6.jpg);">--%>
<%--            <div class="icon d-flex justify-content-center align-items-center">--%>
<%--                <span class="icon-search2"></span>--%>
<%--            </div>--%>
<%--        </a>--%>
<%--        <div class="text p-3">--%>
<%--            <div class="d-flex">--%>
<%--                <div class="one">--%>
<%--                    <h3><a href="hotel-single.html">Hotel, Italy</a></h3>--%>
<%--                    <p class="rate">--%>
<%--                        <i class="icon-star"></i>--%>
<%--                        <i class="icon-star"></i>--%>
<%--                        <i class="icon-star"></i>--%>
<%--                        <i class="icon-star"></i>--%>
<%--                        <i class="icon-star-o"></i>--%>
<%--                        <span>8 Rating</span>--%>
<%--                    </p>--%>
<%--                </div>--%>
<%--                <div class="two">--%>
<%--                    <span class="price per-price">$40<br><small>/night</small></span>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <p>Far far away, behind the word mountains, far from the countries</p>--%>
<%--            <hr>--%>
<%--            <p class="bottom-area d-flex">--%>
<%--                <span><i class="icon-map-o"></i> Miami, Fl</span>--%>
<%--                <span class="ml-auto"><a href="#">Book Now</a></span>--%>
<%--            </p>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="row mt-5">--%>
<%--    <div class="col text-center">--%>
<%--        <div class="block-27">--%>
<%--            <ul>--%>
<%--                <li><a href="#">&lt;</a></li>--%>
<%--                <li class="active"><span>1</span></li>--%>
<%--                <li><a href="#">2</a></li>--%>
<%--                <li><a href="#">3</a></li>--%>
<%--                <li><a href="#">4</a></li>--%>
<%--                <li><a href="#">5</a></li>--%>
<%--                <li><a href="#">&gt;</a></li>--%>
<%--            </ul>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>
<%--</div> <!-- .col-md-8 -->--%>
<%--</div>--%>
<%--</div>--%>
<%--</section> <!-- .section -->--%>

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

</body>
</html>