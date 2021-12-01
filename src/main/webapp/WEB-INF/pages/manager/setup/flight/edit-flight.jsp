<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>
        <c:if test="${action=='add'}">
            Add Flight
        </c:if>
        <c:if test="${action=='update'}">
            Edit Flight
        </c:if>
    </title>
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
                                <div class="page-title">Add Flight</div>
                            </c:if>
                            <c:if test="${action == 'update'}">
                                <div class="page-title">Edit Flight</div>
                            </c:if>
                        </div>
                        <ol class="breadcrumb page-breadcrumb pull-right">
                            <li><i class="fa fa-home"></i>&nbsp;<a class="parent-item" href="index.html">Home</a>&nbsp;<i class="fa fa-angle-right"></i>
                            </li>
                            <li><a class="parent-item" href="">Staff</a>&nbsp;<i class="fa fa-angle-right"></i>
                            </li>
                            <c:if test="${action == 'add'}">
                                <li class="active">Add Flight</li>
                            </c:if>
                            <c:if test="${action == 'update'}">
                                <li class="active">Edit Flight</li>
                            </c:if>

                        </ol>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <div class="card-box">
                            <div class="card-head">
                                <c:if test="${action == 'add'}">
                                    <header>Add Flight</header>
                                </c:if>
                                <c:if test="${action == 'update'}">
                                    <header>Edit Flight</header>
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
                            <mvc:form action="${pageContext.request.contextPath}/manager/flight/result"
                                      modelAttribute="flight" method="post" id="flight">
                                <div class="card-body row">
                                    <input name="action" type="text" value="${action}" hidden/>
                                    <c:if test="${action == 'update'}">
                                        <input class="mdl-textfield__input" value="${flight.id}" name="id" id="id" hidden/>
                                    </c:if>
                                    <div class="col-lg-6 p-t-20">
                                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label txt-full-width">
                                            <select name="aircraft.id" class="mdl-textfield__input" id="aircraft">
                                                <c:forEach items="${aircrafts}" var="aircraft">
                                                    <c:if test="${aircraft.name == flight.aircraft.name}">
                                                        <option value="${aircraft.id}" selected>${aircraft.name}</option>
                                                    </c:if>
                                                    <c:if test="${aircraft.name != flight.aircraft.name}">
                                                        <option value="${aircraft.id}">${aircraft.name}</option>
                                                    </c:if>
                                                </c:forEach>
                                            </select>
                                            <label class="mdl-textfield__label">Aircraft</label>
                                        </div>
                                    </div>
                                    <div class="col-lg-6 p-t-20">
                                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label txt-full-width">
                                            <select name="flightRoute.id" class="mdl-textfield__input" id="flightRoute">
                                                <c:forEach items="${flightRoutes}" var="flightRoute">
                                                    <c:if test="${flightRoute.destination.airportName == flight.flightRoute.destination.airportName
                                                                && flightRoute.departure.airportName == flight.flightRoute.departure.airportName}">
                                                        <option value="${flightRoute.id}" selected><c:out value="${flightRoute.departure.city.cityName} to
                                                            ${flightRoute.destination.city.cityName}" /></option>
                                                    </c:if>
                                                    <c:if test="${flightRoute.destination.airportName != flight.flightRoute.destination.airportName
                                                                && flightRoute.departure.airportName != flight.flightRoute.departure.airportName}">
                                                        <option value="${flightRoute.id}" ><c:out value="${flightRoute.departure.city.cityName} to
                                                            ${flightRoute.destination.city.cityName}" /></option>
                                                    </c:if>
                                                </c:forEach>
                                            </select>
                                            <label class="mdl-textfield__label">Flight Route</label>
                                        </div>
                                    </div>
                                    <div class="col-lg-6 p-t-20">
                                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label txt-full-width">
                                            <mvc:select class="mdl-textfield__input" path="promotionIds"  multiple="true">
                                                 <mvc:options items="${promotions}" itemLabel="name" itemValue="id" />
                                            </mvc:select>
                                            <label class="mdl-textfield__label">Promotion</label>
                                        </div>
                                    </div>
                                    <div class="col-lg-6 p-t-20">
                                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label txt-full-width">
                                            <select name="operation.id" class="mdl-textfield__input" id="operation">
                                                <c:forEach items="${operations}" var="operation">
                                                    <c:if test="${operation.name == flight.operation.name}">
                                                        <option value="${operation.id}" selected>${operation.name}</option>
                                                    </c:if>
                                                    <c:if test="${operation.name != flight.operation.name}">
                                                        <option value="${operation.id}">${operation.name}</option>
                                                    </c:if>
                                                </c:forEach>
                                            </select>
                                            <label class="mdl-textfield__label">Operation</label>
                                        </div>
                                    </div>
                                    <div class="col-lg-6 p-t-20">
                                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label txt-full-width">
                                            <fmt:formatDate value="${flight.depart}" type="both" pattern="yyyy-MM-dd'T'HH:mm" var="departT"/>
                                            <input class="mdl-textfield__input" type="datetime-local" name="departs" value="${departT}" id="departS">
                                            <label class="mdl-textfield__label">Depart</label>
                                        </div>
                                    </div>
                                    <div class="col-lg-6 p-t-20">
                                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label txt-full-width">
                                            <fmt:formatDate value="${flight.arrival}" type="both" pattern="yyyy-MM-dd'T'HH:mm" var="arrivalT"/>
                                            <input class="mdl-textfield__input" type="datetime-local" name="arrivalS" value="${arrivalT}" id="arrivalS">
                                            <label class="mdl-textfield__label">Arrival</label>
                                        </div>
                                    </div>
                                    <div class="col-lg-6 p-t-20">
                                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label txt-full-width">
                                            <select name="status" class="mdl-textfield__input" id="status">
                                                <c:forEach items="${status}" var="s">
                                                    <c:if test="${s == flight.status}">
                                                        <option value="${s}" selected>${s}</option>
                                                    </c:if>
                                                    <c:if test="${s != flight.status}">
                                                        <option value="${s}">${s}</option>
                                                    </c:if>
                                                </c:forEach>
                                            </select>
                                            <label class="mdl-textfield__label">Status</label>
                                        </div>
                                    </div>
                                    <div class="col-lg-12 p-t-20 text-center">
                                        <button type="submit" id="submit" class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect m-b-10 m-r-20 btn-pink">Submit</button>
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
        <%--$("#submit").on('click', function (){--%>
        <%--    var promotions = $("select#promotion option:checked").val();--%>
        <%--    var id= $("#id").val();--%>
        <%--    var aircraft=$("#aircraft").val();--%>
        <%--    var flightRoute = $("#flightRoute").val();--%>
        <%--    var departs = $("#departS").val();--%>
        <%--    var arrivals = $("#arrivalS").val();--%>
        <%--    var status = $("#status").val();--%>
        <%--    var operation=$("#operation").val();--%>
        <%--    var flight= {--%>
        <%--        id:id,--%>
        <%--        depart:departs,--%>
        <%--        arrival:arrivals,--%>
        <%--        aircraft:aircraft,--%>
        <%--        flightRoute:flightRoute,--%>
        <%--        promotions:promotions,--%>
        <%--        operation:operation,--%>
        <%--        status:status--%>
        <%--    };--%>
        <%--    $.ajax({--%>
        <%--        type: "POST",--%>
        <%--        url: '${pageContext.request.contextPath}/api/test',--%>
        <%--        contentType: 'application/json',--%>
        <%--        accept: 'application/json',--%>
        <%--        dataType:'json',--%>
        <%--        data: JSON.stringify(flight), // Note it is important--%>
        <%--        success :function(result) {--%>
        <%--            // do what ever you want with data--%>
        <%--            alert('ok');--%>
        <%--        }--%>
        <%--    });--%>
        <%--});--%>

        $.validator.addMethod("duration", function (value, element){
            return /^([01]?[0-9]|2[0-3])(:[0-5][0-9]){2}$/.test(value);
        }, "Invalid time format.");
        $("#flight").validate({
            errorClass: "my-error-class",
            rules: {
                duration: {
                    required: true,
                    duration: true
                },
                distance: {
                    required: true,
                    digits: true
                },
                price: {
                    required: true,
                    number: true
                }
            }
        });
    });
</script>
</body>
</html>
