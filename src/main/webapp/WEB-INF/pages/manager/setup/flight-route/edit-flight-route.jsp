<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Flight Route</title>
    <jsp:include page="/WEB-INF/pages/include/management/css-page.jsp"/>
    <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js"></script>
    <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js" type="text/javascript"></script>
    <style>
        .my-error-class {
            color:#FF0000;  /* red */
        }
    </style>
</head>
<body class="page-header-fixed sidemenu-closed-hidelogo page-content-white page-md header-white dark-sidebar-color logo-dark">
<div class="page-wrapper">
    <!-- start header -->
    <jsp:include page="/WEB-INF/pages/include/management/header.jsp" />
    <!-- end header -->
    <!-- start page container -->
    <div class="page-container">
        <!-- start sidebar menu -->
        <jsp:include page="/WEB-INF/pages/include/management/sidebar-menu.jsp" />
        <!-- end sidebar menu -->
        <!-- start page content -->
        <div class="page-content-wrapper">
            <div class="page-content">
                <div class="page-bar">
                    <div class="page-title-breadcrumb">
                        <div class=" pull-left">
                            <c:if test="${action == 'add'}">
                                <div class="page-title">Add Flight Route</div>
                            </c:if>
                            <c:if test="${action == 'update'}">
                                <div class="page-title">Edit Flight Route</div>
                            </c:if>
                        </div>
                        <ol class="breadcrumb page-breadcrumb pull-right">
                            <li><i class="fa fa-home"></i>&nbsp;<a class="parent-item" href="index.html">Home</a>&nbsp;<i class="fa fa-angle-right"></i>
                            </li>
                            <li><a class="parent-item" href="">Staff</a>&nbsp;<i class="fa fa-angle-right"></i>
                            </li>
                            <c:if test="${action == 'add'}">
                                <li class="active">Add Flight Route</li>
                            </c:if>
                            <c:if test="${action == 'update'}">
                                <li class="active">Edit Flight Route</li>
                            </c:if>

                        </ol>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <div class="card-box">
                            <div class="card-head">
                                <c:if test="${action == 'add'}">
                                    <header>Add Flight Route</header>
                                </c:if>
                                <c:if test="${action == 'update'}">
                                    <header>Edit Flight Route</header>
                                </c:if>
                                <button id="panel-button" class="mdl-button mdl-js-button mdl-button--icon pull-right" data-upgraded=",MaterialButton">
                                    <i class="material-icons">more_vert</i>
                                </button>
                                <ul class="mdl-menu mdl-menu--bottom-right mdl-js-menu mdl-js-ripple-effect" data-mdl-for="panel-button">
                                    <li class="mdl-menu__item"><i class="material-icons">assistant_photo</i>Action
                                    </li>
                                    <li class="mdl-menu__item"><i class="material-icons">print</i>Another action
                                    </li>
                                    <li class="mdl-menu__item"><i class="material-icons">favorite</i>Something else
                                        here</li>
                                </ul>
                            </div>
                            <mvc:form action="${pageContext.request.contextPath}/manager/flight-route/result"
                                      modelAttribute="flightRoute" method="post" id="flight-route">
                                <div class="card-body row">
                                    <input name="action" type="text" value="${action}" hidden/>
                                    <c:if test="${action == 'update'}">
                                        <input class="mdl-textfield__input" type="text" value="${flightRoute.id}" name="id" hidden/>
                                    </c:if>
                                    <div class="col-lg-6 p-t-20">
                                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label txt-full-width">
                                            <input class="mdl-textfield__input" type="text" value="${flightRoute.duration}" name="duration">
                                            <label class="mdl-textfield__label">Duration</label>
                                        </div>
                                    </div>
                                    <div class="col-lg-6 p-t-20">
                                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label txt-full-width">
                                            <fmt:formatNumber type = "number"
                                                              maxFractionDigits = "0" value = "${flightRoute.price}" var="price" />
                                            <input class="mdl-textfield__input" type="number" value="${price}" name="price" id="price">
                                            <label class="mdl-textfield__label">Price</label>
                                        </div>
                                    </div>
                                    <div class="col-lg-6 p-t-20">
                                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label txt-full-width">
                                            <input class="mdl-textfield__input" type="number" value="${flightRoute.distance}" name="distance">
                                            <label class="mdl-textfield__label">Distance</label>
                                        </div>
                                    </div>
                                    <div class="col-lg-6 p-t-20">
                                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label txt-full-width">
                                            <select name="departure.id" class="mdl-textfield__input" id="departure">
                                                <option value="default">Select Departure</option>
                                                <c:forEach items="${airports}" var="airport">
                                                    <c:if test="${airport.city.cityName == flightRoute.departure.city.cityName}">
                                                        <option value="${airport.id}" selected>${airport.city.cityName}</option>
                                                    </c:if>
                                                    <c:if test="${airport.city.cityName != flightRoute.departure.city.cityName}">
                                                        <option value="${airport.id}">${airport.city.cityName}</option>
                                                    </c:if>
                                                </c:forEach>
                                            </select>
                                            <label class="mdl-textfield__label">Departure</label>
                                        </div>
                                    </div>
                                    <div class="col-lg-6 p-t-20">
                                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label txt-full-width">
                                            <select name="destination.id" class="mdl-textfield__input" id="destination">
                                            </select>
                                            <label class="mdl-textfield__label">Destination</label>
                                        </div>
                                    </div>
                                    <div class="col-lg-6 p-t-20">
                                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label txt-full-width">
                                            <select name="status" class="mdl-textfield__input">
                                                <c:forEach items="${status}" var="s">
                                                    <c:if test="${s == flightRoute.status}">
                                                        <option value="${s}" selected>${s}</option>
                                                    </c:if>
                                                    <c:if test="${s != flightRoute.status}">
                                                        <option value="${s}">${s}</option>
                                                    </c:if>
                                                </c:forEach>
                                            </select>
                                            <label class="mdl-textfield__label">Status</label>
                                        </div>
                                    </div>
                                    <div class="col-lg-12 p-t-20 text-center">
                                        <button type="submit" class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect m-b-10 m-r-20 btn-pink">Submit</button>
                                        <button type="reset" class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect m-b-10 btn-default">Cancel</button>
                                    </div>
                                </div>
                            </mvc:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- end page content -->
        <!-- start chat sidebar -->

        <!-- end chat sidebar -->
    </div>
    <!-- end page container -->
    <!-- start footer -->
    <jsp:include page="/WEB-INF/pages/include/management/footer.jsp" />
    <!-- end footer -->
</div>
<jsp:include page="/WEB-INF/pages/include/management/js-page.jsp" />
<script>
    $(document).ready(function(){
        $.validator.addMethod("duration", function (value, element){
            return /^([01]?[0-9]|2[0-3])(:[0-5][0-9]){2}$/.test(value);
        }, "Invalid time format.");
        $.validator.addMethod("valueNotEquals", function(value, element, arg){
            return arg !== value;
        }, "Value must not equal arg.");
        $.validator.addMethod('minStrict', function (value, el, param) {
            return value > param;
        });
        $("#flight-route").validate({
            errorClass: "my-error-class",
            rules: {
                duration: {
                    required: true,
                    duration: true
                },
                distance: {
                    required: true,
                    digits: true,
                    minStrict: 0
                },
                price: {
                    required: true,
                    number: true,
                    minStrict: 0
                },
                departure: {
                    required: true,
                    valueNotEquals: "default"
                }
            },
            messages: {
                duration: {
                    required: "Please enter duration.",
                    duration: "Invalid time format."
                },
                distance: {
                    required: "Please enter duration.",
                    digits: "Distance must be digit.",
                    minStrict: "Field must greater than 0"
                },
                price: {
                    required: "Please enter price of flight route.",
                    minStrict: "Field must greater than 0"
                },
                departure: {
                    required: "Please select field.",
                    valueNotEquals: "Please select an item!"
                }
            },
            submitHandler: function(form) {
                form.submit();
            }
        });
        $("#departure").on('change', function (){
            var departureId =  $("#departure").val();
            $.ajax({
                type: 'GET',
                url:"${pageContext.request.contextPath}/api/getDestination",
                data: {departureId : departureId},
                success: function (result){
                    $('#destination').html(result);
                }
            });
        });
    });
</script>
</body>
</html>
