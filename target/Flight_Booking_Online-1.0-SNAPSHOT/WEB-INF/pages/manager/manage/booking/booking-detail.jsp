<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Home</title>
    <jsp:include page="/WEB-INF/pages/include/management/css-page.jsp"/>
    <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js"></script>
    <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js" type="text/javascript"></script>
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
                            <div class="page-title">Booking Detail</div>
                        </div>
                        <ol class="breadcrumb page-breadcrumb pull-right">
                            <li><i class="fa fa-home"></i>&nbsp;<a class="parent-item" href="index.html">Home</a>&nbsp;<i class="fa fa-angle-right"></i>
                            </li>
                            <li><a class="parent-item" href="">Booking</a>&nbsp;<i class="fa fa-angle-right"></i>
                            </li>
                            <li class="active">Booking Detail</li>
                        </ol>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-4 offset-4">
                        <div class="card card-box">
                            <div class="card-head center">
                                <header>Booking Infomation</header>
                            </div>
                            <div class="card-body ">
                                <input type="hidden" value="${booking.id}" id="bookingId">
                                <div class="col-md-6 div-float-left">
                                    <p>Name</p>
                                    <p>Address</p>
                                    <p>Phone Number</p>
                                    <p>Birthday</p>
                                    <p>Gender</p>
                                    <p>Booking Date</p>
                                    <p>Booking Number</p>
                                    <p>Status</p>
                                </div>
                                <div class="col-md-6 div-float-left" >
                                    <p>${booking.fullName}</p>
                                    <p>${booking.address}</p>
                                    <p>${booking.phoneNumber}</p>
                                    <p>${booking.birthDay}</p>
                                    <p>${booking.gender}</p>
                                    <p>${booking.bookingDate}</p>
                                    <p>${booking.bookingNumber}</p>
                                    <mvc:form action="${pageContext.request.contextPath}/manager/booking/status" method="get">
                                        <p>
                                            <select name="status" id="status">
                                                <c:forEach items="${bookingStatus}" var="bStatus">
                                                    <c:if test="${bStatus.status==booking.bookingStatus.status}">
                                                        <option value="${bStatus.status}" selected>${bStatus.status}</option>
                                                    </c:if>
                                                    <c:if test="${bStatus.status!=booking.bookingStatus.status}">
                                                        <option value="${bStatus.status}">${bStatus.status}</option>
                                                    </c:if>
                                                </c:forEach>
                                            </select>
                                            <input type="hidden" name="id" value="${booking.id}">
                                        </p>
                                        <p>
                                            <c:if test="${booking.bookingStatus.status!='CANCEL'}">
                                                <button type="submit" class="mdl-button mdl-js-button
                                                mdl-button--raised mdl-js-ripple-effect m-b-10 m-r-20
                                                btn-pink">Change</button>
                                            </c:if>
                                        </p>
                                    </mvc:form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <c:if test="${direction=='OneTrip'}">
                    <div class="row" id="value">
                        <c:forEach items="${bookingDetails}" var="detail" varStatus="index">
                            <div class="col-md-6">
                                <div class="card card-box">
                                    <div class="card-head center">
                                        <header>Ticket information</header> <br>
                                        <header>Passenger Information: ${index.count}</header>
                                    </div>
                                    <div class="card-body ">
                                        <div class="col-md-6 div-float-left">
                                            <p>Name</p>
                                            <p>Flight Route</p>
                                            <p>Depart Date</p>
                                            <p>Arrival Date</p>
                                            <p>Address</p>
                                            <p>Phone Number</p>
                                            <p>Email</p>
                                            <p>Birthday</p>
                                            <p>Gender</p>
                                            <p>Ident Code</p>
                                            <p>Operations</p>
                                            <p>Aircraft Number</p>
                                            <p>Seat</p>
                                            <p>Service</p>
                                        </div>
                                        <div class="col-md-6 div-float-left" >
                                            <p>${detail.passenger.fullName}</p>
                                            <p>From ${detail.flight.flightRoute.departure.city.cityName} to
                                                    ${detail.flight.flightRoute.destination.city.cityName}
                                            </p>
                                            <p>${detail.flight.depart}</p>
                                            <p>${detail.flight.arrival}</p>
                                            <p>${detail.passenger.address}</p>
                                            <p>${detail.passenger.phoneNumber}</p>
                                            <p>${detail.passenger.email}</p>
                                            <p>${detail.passenger.birthDay}</p>
                                            <p>${detail.passenger.gender}</p>
                                            <p>${detail.passenger.identCode}</p>
                                            <p>${detail.flight.operation.name}</p>
                                            <p>${detail.flight.aircraft.number}</p>
                                            <p>${detail.seat.seatNumber}</p>
                                            <div>
                                                <c:if test="${booking.bookingStatus.status.name()=='BOOKED'}">
                                                    <a href="#" data-toggle="modal" data-target="#myModal${detail.id}" >Add Servie</a>
                                                </c:if>
                                                <table>
                                                    <c:forEach items="${detail.serviceBookings}" var="sv">
                                                        <tr>
                                                            <td>${sv.service.name}</td>
<%--                                                            <c:if test="${booking.bookingStatus.status.name()=='BOOKED'}">--%>
<%--                                                                <td>--%>
<%--                                                                    <button onclick="location.href='<c:url--%>
<%--                                                                            value="/manager/booking/serviceBooking/delete/${booking.id}/${sv.id}"/>'"--%>
<%--                                                                            class="btn btn-tbl-delete btn-xs m-l-20">--%>
<%--                                                                        <i class="fa fa-trash-o "></i>--%>
<%--                                                                    </button>--%>
<%--                                                                </td>--%>
<%--                                                            </c:if>--%>
                                                        </tr>
                                                    </c:forEach>
                                                    <tr>
                                                    </tr>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- bengin modal-->
                            <div class="modal fade" id="myModal${detail.id}" role="dialog">
                                <div class="modal-dialog">
                                    <!-- Modal content-->
                                    <div class="modal-content" style="margin-top: 200px">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                                            <h4 class="modal-title">Add Service</h4>
                                        </div>
                                        <div class="modal-body">
                                            <label class="visually-hidden" for="inline-form-name">Name</label>
                                            <label class="visually-hidden" for="inline-form-name" style="margin-left: 130px">Price</label>
                                            <label class="visually-hidden" for="inline-form-name" style="margin-left: 130px">Quantity</label>
                                            <c:forEach var="service" items="${services}">
                                                <form class="row row-cols-lg-auto g-3">
                                                    <div class="col">
                                                        <label>${service.name}</label>
                                                    </div>
                                                    <div class="col">
                                                        <fmt:formatNumber type="number" pattern="###,###,### VND" value="${service.price}"/>
                                                    </div>
                                                    <div class="col">
                                                        <input onchange="test(${service.id}, this.value)" type="number" class="form-control" id="inline-form-website" min="0" value="0"
                                                        >
                                                    </div>
                                                </form>
                                            </c:forEach>
                                            <label >Tổng tiền: </label>
                                            <label id="amount-price" style="margin-left: 100px">
                                            </label>
                                            <div class="col">
                                                <button onclick="addService(${detail.id},${detail.booking.id})"  class="btn btn-primary" id="click-service" data-dismiss="modal">Submit</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- end modal-->
                        </c:forEach>
                    </div>
                </c:if>
                <c:if test="${direction=='TwoTrip'}">
                    <div class="row" id="value">
                        <c:forEach items="${bookingDetails}" var="detail" varStatus="loop">
                            <div class="col-md-6">
                                <div class="card card-box">
                                    <div class="card-head center">
                                        <c:if test="${loop.count <= bookingDetails.size()/2}">
                                            <header>One-way Ticket Information</header><br>
                                            <header>Passenger Information: ${loop.count}</header>
                                        </c:if>
                                        <c:if test="${loop.count>bookingDetails.size()/2}">
                                            <header>Return Ticket Information</header><br>
                                            <header>Passenger Information: <fmt:formatNumber value="${loop.count-bookingDetails.size()/2}"></fmt:formatNumber></header>
                                        </c:if>
                                    </div>
                                    <div class="card-body ">
                                        <div class="col-md-6 div-float-left">
                                            <p>Name</p>
                                            <p>Flight Route</p>
                                            <p>Depart Date</p>
                                            <p>Arrival Date</p>
                                            <p>Address</p>
                                            <p>Phone Number</p>
                                            <p>Email</p>
                                            <p>Birthday</p>
                                            <p>Gender</p>
                                            <p>Ident Code</p>
                                            <p>Operations</p>
                                            <p>Aircraft Number</p>
                                            <p>Seat</p>
                                            <p>Service</p>
                                        </div>
                                        <div class="col-md-6 div-float-left" >
                                            <p>${detail.passenger.fullName}</p>
                                            <p>From ${detail.flight.flightRoute.departure.city.cityName} to
                                                    ${detail.flight.flightRoute.destination.city.cityName}
                                            </p>
                                            <p>${detail.flight.depart}</p>
                                            <p>${detail.flight.arrival}</p>
                                            <p>${detail.passenger.address}</p>
                                            <p>${detail.passenger.phoneNumber}</p>
                                            <p>${detail.passenger.email}</p>
                                            <p>${detail.passenger.birthDay}</p>
                                            <p>${detail.passenger.gender}</p>
                                            <p>${detail.passenger.identCode}</p>
                                            <p>${detail.flight.operation.name}</p>
                                            <p>${detail.flight.aircraft.number}</p>
                                            <p>${detail.seat.seatNumber}</p>
                                            <div>
                                                <table>
                                                    <c:forEach items="${detail.serviceBookings}" var="sv">
                                                        <tr>
                                                            <td>${sv.service.name}</td>
<%--                                                            <c:if test="${booking.bookingStatus.status.name()=='BOOKED'}">--%>
<%--                                                                <td>--%>
<%--                                                                    <button onclick="location.href='<c:url--%>
<%--                                                                            value="/manager/booking/serviceBooking/delete/${booking.id}/${sv.id}"/>'"--%>
<%--                                                                            class="btn btn-tbl-delete btn-xs m-l-20">--%>
<%--                                                                        <i class="fa fa-trash-o "></i>--%>
<%--                                                                    </button>--%>
<%--                                                                </td>--%>
<%--                                                            </c:if>--%>
                                                        </tr>
                                                    </c:forEach>
                                                </table>
                                                <c:if test="${booking.bookingStatus.status.name()=='BOOKED'}">
                                                    <a href="#" data-toggle="modal" data-target="#myModal${detail.id}" >Add Servie</a>
                                                </c:if>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- bengin modal-->
                            <div class="modal fade" id="myModal${detail.id}" role="dialog">
                                <div class="modal-dialog">
                                    <!-- Modal content-->
                                    <div class="modal-content" style="margin-top: 200px">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                                            <h4 class="modal-title">Add Service</h4>
                                        </div>
                                        <div class="modal-body">
                                            <label class="visually-hidden" for="inline-form-name">Name</label>
                                            <label class="visually-hidden" for="inline-form-name" style="margin-left: 130px">Price</label>
                                            <label class="visually-hidden" for="inline-form-name" style="margin-left: 130px">Quantity</label>
                                            <c:forEach var="service" items="${services}">
                                                <form class="row row-cols-lg-auto g-3">
                                                    <div class="col">
                                                        <label>${service.name}</label>
                                                    </div>
                                                    <div class="col">
                                                        <fmt:formatNumber type="number" pattern="###,### VND" value="${service.price}"/>
                                                    </div>
                                                    <div class="col">
                                                        <input onchange="test1(${service.id}, this.value,${loop.index})" type="number" class="form-control" id="inline-form-website" min="0" value="0"
                                                        >
                                                    </div>
                                                </form>
                                            </c:forEach>
                                            <label >Tổng tiền: </label>
                                            <label class="amount-price-two" style="margin-left: 100px">
                                            </label>
                                            <div class="col">
                                                <button onclick="addService(${detail.id},${detail.booking.id})"  class="btn btn-primary" id="click-service" data-dismiss="modal">Submit</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- end modal-->
                        </c:forEach>
                    </div>
                </c:if>
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
    function test(serviceId, quantity) {
        $.ajax({
            url: '${pageContext.request.contextPath}/api/change-price',
            type: 'get',
            data: {
                serviceId: serviceId,
                quantity: quantity
            },
            success: function (value) {
                console.log(value)
                if(${direction=='TwoTrip'}) {

                    $('#amount-price-two').text(value);
                    console.log('-----------------------------------------------------')
                } else {
                    $('#amount-price').text(value);
                }
            }
        });
    }

    function test1(serviceId, quantity,index) {
        $.ajax({
            url: '${pageContext.request.contextPath}/api/change-price',
            type: 'get',
            data: {
                serviceId: serviceId,
                quantity: quantity
            },
            success: function (value) {
                console.log(value)

                var testContent = document.querySelectorAll('.amount-price-two');
                testContent[index].innerText=value;
                console.log('-----------------------------------------------------')
            }
        });
    }


    function addService(bookingDetailId, bookingId) {
        $.ajax({
            url: '${pageContext.request.contextPath}/api/load-service',
            type: 'get',
            data: {
                bookingDetailId: bookingDetailId,
                bookingId: bookingId

            },
            success: function (value) {
                console.log('------------------------------------------')
                console.log(value)
                $('#loadService').html(value);
            }
        });
    }
</script>
</body>
</html>