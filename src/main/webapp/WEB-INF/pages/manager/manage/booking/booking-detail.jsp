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
                                                    <c:if test="${st==booking.bookingStatus}">
                                                        <option value="${bStatus.status}" selected>${bStatus.status}</option>
                                                    </c:if>
                                                    <c:if test="${st!=booking.bookingStatus}">
                                                        <option value="${bStatus.status}">${bStatus.status}</option>
                                                    </c:if>
                                                </c:forEach>
                                            </select>
                                            <input type="hidden" name="id" value="${booking.id}">
                                        </p>
                                        <p>
                                            <c:if test="${booking.bookingStatus.status!='CANCEL'}">
                                                <button type="submit" class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect m-b-10 m-r-20 btn-pink">Change</button>
                                            </c:if>
                                        </p>
                                    </mvc:form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                    <div class="row">
                        <c:forEach items="${bookingDetails}" var="detail" varStatus="index">
                            <div class="col-md-6">
                                <div class="card card-box">
                                    <div class="card-head center">
                                        <header>Passenger Information: ${index.index+1}</header>
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
                                            <p>
                                                <c:if test="${booking.bookingStatus.status=='BOOKED'}">
                                                    Service
                                                </c:if>
                                            </p>
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
                                            <p>
                                                <c:if test="${booking.bookingStatus.status=='BOOKED'}">
                                                     <c:forEach items="${detail.serviceBookings}" var="sv">
                                                         <p>${sv.service.name}</p>
                                                     </c:forEach>
                                                </c:if>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
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
    $(document).ready(function (){
        $("#submit").click(function (){
            var bookingId= $("#bookingId").val();
            var status =  $("#status").val();
            var statusJson= JSON.stringify(status);
            console.log(statusJson);
            $.ajax({
                type: 'POST',
                url:"${pageContext.request.contextPath}/manager/booking/status/"+bookingId,
                data: statusJson,
                contentType: "application/json; charset=utf-8",
                success: function (result){
                    if(result=='CANCEL'){
                        $("#submit").hide();
                    }
                }
            });
            event.preventDefault();
        });
    });
</script>
</body>
</html>