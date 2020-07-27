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

        select-opt {
            color: black;
            text-shadow: black;
        }

        /*the container must be positioned relative:*/
        .custom-select {
            position: relative;
            font-family: Arial;
        }

        .custom-select select {
            display: none; /*hide original SELECT element:*/
        }

        .select-selected {
            background-color: #009edf;
        }

        /*style the arrow inside the select element:*/
        .select-selected:after {
            position: absolute;
            content: "";
            top: 14px;
            right: 10px;
            width: 0;
            height: 0;
            border: 6px solid transparent;
            border-color: #fff transparent transparent transparent;
        }

        /*point the arrow upwards when the select box is open (active):*/
        .select-selected.select-arrow-active:after {
            border-color: transparent transparent #fff transparent;
            top: 7px;
        }

        /*style the items (options), including the selected item:*/
        .select-items div, .select-selected {
            color: #ffffff;
            padding: 8px 16px;
            border: 1px solid transparent;
            border-color: transparent transparent rgba(0, 0, 0, 0.1) transparent;
            cursor: pointer;
            user-select: none;
        }

        /*style items (options):*/
        .select-items {
            position: absolute;
            background-color: #009edf;
            top: 100%;
            left: 0;
            right: 0;
            z-index: 99;
        }

        /*hide the items when the select box is closed:*/
        .select-hide {
            display: none;
        }

        .select-items div:hover, .same-as-selected {
            background-color: rgba(0, 0, 0, 0.1);
        }
    </style>

    <title><fmt:message key="page.manager.cr8ingTour"/></title>
</head>
<body>

<div class="container">
    <div class="row main-form">
        <form class="" method="POST" action="Controller">
            <h4><fmt:message key="page.manager.cr8ingTour"/></h4>
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
                               step="0.01" min="0" value="100" max="10000"
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
                        <p>
                        <div class="custom-select" style="width:200px;">
                            <select name="typeOfTour" id="typeOfTour" size="1" class="select-opt"
                                    name="<fmt:message key="page.manager.cr8ingTour.type"/>">
                                <option selected disabled><fmt:message key="page.manager.cr8ingTour.type"/></option>
                                <c:forEach var="type" items="${typeOfTourMap}" varStatus="status">
                                    <option value="${type.key}"><fmt:message key="menu.button.${type.value}"/></option>
                                </c:forEach>
                            </select>
                        </div>
                        </p>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label for="hotTour" class="cols-sm-2 control-label"><fmt:message
                        key="page.manager.cr8ingTour.hotTour"/></label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-envelope fa" aria-hidden="true"></i></span>
                        <label name="hotTour" id="hotTour"><input type="radio" name="hotTour" value="true"/>
                            <fmt:message key="page.manager.cr8ingTour.true"/></label>
                        <label name="hotTour"><input type="radio" checked name="hotTour" value="false"/>
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
                        <p>
                        <div class="custom-select" style="width:200px;">
                            <select name="discount" id="discount" size="1" class="select-opt"
                                    name="<fmt:message key="page.manager.cr8ingTour.discount"/>">
                                <option selected disabled><fmt:message key="page.manager.cr8ingTour.discount"/></option>
                                <c:forEach var="discount" items="${discounts}" varStatus="status">
                                    <option value="${discount.key}">${discount.value}</option>
                                </c:forEach>
                            </select></div>
                        </p>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label for="hotel" class="cols-sm-2 control-label">
                    <fmt:message key="page.manager.cr8ingTour.hotel"/></label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                        <p>
                        <div class="custom-select" style="width:200px;">
                            <select name="hotel" id="hotel" size="5" class="select-opt"
                                    name="<fmt:message key="page.manager.cr8ingTour.hotel"/>">
                                <option selected disabled><fmt:message key="page.manager.cr8ingTour.hotel"/></option>
                                <c:forEach var="hotel" items="${hotelSet}" varStatus="status">
                                    <option value="${hotel.id}" style="color: black">${hotel.title}</option>
                                </c:forEach>
                            </select></div>
                        </p>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label for="description" class="cols-sm-2 control-label"><fmt:message
                        key="page.manager.cr8ingTour.description"/></label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                        <textarea name="description" cols="50" rows="3" class="form-control"
                                  id="description"></textarea>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label for="url_wallpaper" class="cols-sm-2 control-label"><fmt:message
                        key="page.manager.cr8ingTour.url_wallpaper"/></label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                        <input type="text" class="form-control" name="url_wallpaper" id="url_wallpaper"
                               placeholder="<fmt:message key="page.manager.cr8ingTour.url_wallpaper"/>"/>
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


<script>
    var x, i, j, l, ll, selElmnt, a, b, c;
    /*look for any elements with the class "custom-select":*/
    x = document.getElementsByClassName("custom-select");
    l = x.length;
    for (i = 0; i < l; i++) {
        selElmnt = x[i].getElementsByTagName("select")[0];
        ll = selElmnt.length;
        /*for each element, create a new DIV that will act as the selected item:*/
        a = document.createElement("DIV");
        a.setAttribute("class", "select-selected");
        a.innerHTML = selElmnt.options[selElmnt.selectedIndex].innerHTML;
        x[i].appendChild(a);
        /*for each element, create a new DIV that will contain the option list:*/
        b = document.createElement("DIV");
        b.setAttribute("class", "select-items select-hide");
        for (j = 1; j < ll; j++) {
            /*for each option in the original select element,
            create a new DIV that will act as an option item:*/
            c = document.createElement("DIV");
            c.innerHTML = selElmnt.options[j].innerHTML;
            c.addEventListener("click", function (e) {
                /*when an item is clicked, update the original select box,
                and the selected item:*/
                var y, i, k, s, h, sl, yl;
                s = this.parentNode.parentNode.getElementsByTagName("select")[0];
                sl = s.length;
                h = this.parentNode.previousSibling;
                for (i = 0; i < sl; i++) {
                    if (s.options[i].innerHTML == this.innerHTML) {
                        s.selectedIndex = i;
                        h.innerHTML = this.innerHTML;
                        y = this.parentNode.getElementsByClassName("same-as-selected");
                        yl = y.length;
                        for (k = 0; k < yl; k++) {
                            y[k].removeAttribute("class");
                        }
                        this.setAttribute("class", "same-as-selected");
                        break;
                    }
                }
                h.click();
            });
            b.appendChild(c);
        }
        x[i].appendChild(b);
        a.addEventListener("click", function (e) {
            /*when the select box is clicked, close any other select boxes,
            and open/close the current select box:*/
            e.stopPropagation();
            closeAllSelect(this);
            this.nextSibling.classList.toggle("select-hide");
            this.classList.toggle("select-arrow-active");
        });
    }

    function closeAllSelect(elmnt) {
        /*a function that will close all select boxes in the document,
        except the current select box:*/
        var x, y, i, xl, yl, arrNo = [];
        x = document.getElementsByClassName("select-items");
        y = document.getElementsByClassName("select-selected");
        xl = x.length;
        yl = y.length;
        for (i = 0; i < yl; i++) {
            if (elmnt == y[i]) {
                arrNo.push(i)
            } else {
                y[i].classList.remove("select-arrow-active");
            }
        }
        for (i = 0; i < xl; i++) {
            if (arrNo.indexOf(i)) {
                x[i].classList.add("select-hide");
            }
        }
    }

    /*if the user clicks anywhere outside the select box,
    then close all select boxes:*/
    document.addEventListener("click", closeAllSelect);
</script>
</body>
</html>