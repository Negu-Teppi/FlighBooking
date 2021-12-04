<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<%@taglib prefix="mvc" uri="http://www.springframework.org/tags/form" %>

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