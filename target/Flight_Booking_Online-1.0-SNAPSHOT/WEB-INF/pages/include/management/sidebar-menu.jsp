<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- start sidebar menu -->
<div class="sidebar-container">
    <div class="sidemenu-container navbar-collapse collapse fixed-menu">
        <div id="remove-scroll">
            <ul class="sidemenu page-header-fixed p-t-20" data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200">
                <li class="sidebar-toggler-wrapper hide">
                    <div class="sidebar-toggler">
                        <span></span>
                    </div>
                </li>
                <li class="sidebar-user-panel">
                    <div class="user-panel">
                        <div class="row">
                            <div class="sidebar-userpic">
                                <img src="<c:url value="/resources-management/assets/img/avatar.png"/>" class="img-responsive" alt=""> </div>
                        </div>
                        <div class="profile-usertitle">
                            <div class="sidebar-userpic-name"> Manh Lee </div>
                            <div class="profile-usertitle-job"> Manager </div>
                        </div>
                        <div class="sidebar-userpic-btn">
                            <a class="tooltips" href="user_profile.html" data-placement="top" data-original-title="Profile">
                                <i class="material-icons">person_outline</i>
                            </a>
                            <a class="tooltips" href="email_inbox.html" data-placement="top" data-original-title="Mail">
                                <i class="material-icons">mail_outline</i>
                            </a>
                            <a class="tooltips" href="chat.html" data-placement="top" data-original-title="Chat">
                                <i class="material-icons">chat</i>
                            </a>
                            <a class="tooltips" href="login.html" data-placement="top" data-original-title="Logout">
                                <i class="material-icons">input</i>
                            </a>
                        </div>
                    </div>
                </li>
                <li class="nav-item start">
                    <a href="#" class="nav-link nav-toggle">
                        <i class="material-icons">flight_land</i>
                        <span class="title">Aircrafts</span>
                        <span class="arrow"></span>
                    </a>
                    <ul class="sub-menu">
                        <li class="nav-item ">
                            <a href="<c:url value="/manager/view/aircraft"/>" class="nav-link ">
                                <span class="title">View Aircraft</span>
                            </a>
                        </li>
                        <li class="nav-item ">
                            <a href="<c:url value="/manager/view/aircraft"/>" class="nav-link ">
                                <span class="title">Add Aircraft</span>
                            </a>
                        </li>
<%--                        <li class="nav-item">--%>
<%--                            <a href="<c:url value="/manager/aircraft-seat/add"/> " class="nav-link ">--%>
<%--                                <span class="title">Set Seat</span>--%>
<%--                            </a>--%>
<%--                        </li>--%>
<%--                        <li class="nav-item">--%>
<%--                            <a href="<c:url value="/manager/aircraft-seat/view"/> " class="nav-link ">--%>
<%--                                <span class="title">View Seat</span>--%>
<%--                            </a>--%>
<%--                        </li>--%>
                    </ul>
                </li>
                <li class="nav-item">
                    <a href="#" class="nav-link nav-toggle">
                        <i class="material-icons">account_balance</i>
                        <span class="title">Airports</span>
                        <span class="arrow"></span>
<%--                        <span class="label label-rouded label-menu label-danger">new</span>--%>
                    </a>
                    <ul class="sub-menu">
                        <li class="nav-item">
                            <a href="<c:url value="/manager/view/airport"/>" class="nav-link ">
                                <span class="title">View Airport</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="" class="nav-link ">
                                <span class="title">Add Airport</span>
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="nav-item">
                    <a href="#" class="nav-link nav-toggle">
                        <i class="material-icons">flight</i>
                        <span class="title">Flight Route</span>
                        <span class="arrow"></span>
                        <%--                        <span class="label label-rouded label-menu label-danger">new</span>--%>
                    </a>
                    <ul class="sub-menu">
                        <li class="nav-item">
                            <a href="<c:url value="/manager/flight-route/add"/>" class="nav-link ">
                                <span class="title">Add</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="<c:url value="/manager/flight-route/view"/> " class="nav-link ">
                                <span class="title">View</span>
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="nav-item start">
                    <a href="#" class="nav-link nav-toggle">
                        <i class="material-icons">flight_takeoff</i>
                        <span class="title">Flight</span>
                        <span class="arrow"></span>
                    </a>
                    <ul class="sub-menu">
                        <li class="nav-item ">
                            <a href="<c:url value="/manager/flight/view"/>" class="nav-link ">
                                <span class="title">View Flight</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="<c:url value="/manager/flight/add"/> " class="nav-link ">
                                <span class="title">Add Flight</span>
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="nav-item">
                    <a href="#" class="nav-link nav-toggle">
                        <i class="material-icons">group</i>
                        <span class="title">Booking</span>
                        <span class="arrow"></span>
                    </a>
                    <ul class="sub-menu">
                        <li class="nav-item">
                            <a href="<c:url value="/manager/booking/view"/>" class="nav-link ">
                                <span class="title">All Bookings</span>
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="nav-item">
                    <a href="#" class="nav-link nav-toggle">
                        <i class="material-icons">local_taxi</i>
                        <span class="title">Service</span>
                        <span class="arrow"></span>
                    </a>
                    <ul class="sub-menu">
                        <li class="nav-item">
                            <a href="<c:url value="/manager/service/view"/>" class="nav-link ">
                                <span class="title">View Services</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="<c:url value="/manager/service/add-service"/>" class="nav-link ">
                                <span class="title">Add Service</span>
                            </a>
                        </li>
                        </li>
                    </ul>
                </li>
                <li class="nav-item">
                    <a href="#" class="nav-link nav-toggle">
                        <i class="material-icons">event</i>
                        <span class="title">Promotion</span>
                        <span class="arrow"></span>
                    </a>
                    <ul class="sub-menu">
                        <li class="nav-item">
                            <a href="<c:url value="/manager/promotion/view"/>" class="nav-link ">
                                <span class="title">View Promotions</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="<c:url value="/manager/promotion/add-promotion"/>" class="nav-link ">
                                <span class="title">Add Promotion</span>
                            </a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</div>
<!-- end sidebar menu -->