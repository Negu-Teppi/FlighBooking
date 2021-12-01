
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Flight Pages</title>
    <jsp:include page="/WEB-INF/pages/include/management/css-page.jsp"/>
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
                            <div class="page-title">All Flight</div>
                        </div>
                        <ol class="breadcrumb page-breadcrumb pull-right">
                            <li><i class="fa fa-home"></i>&nbsp;<a class="parent-item" href="index.html">Home</a>&nbsp;<i class="fa fa-angle-right"></i>
                            </li>
                            <li><a class="parent-item" href="">Flight</a>&nbsp;<i class="fa fa-angle-right"></i>
                            </li>
                            <li class="active">All Flight</li>
                        </ol>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <div class="card card-box">
                            <div class="card-head">
                                <header>All Flight</header>
                                <div class="tools">
                                    <a class="fa fa-repeat btn-color box-refresh" href="javascript:;"></a>
                                    <a class="t-collapse btn-color fa fa-chevron-down" href="javascript:;"></a>
                                    <a class="t-close btn-color fa fa-times" href="javascript:;"></a>
                                </div>
                            </div>
                            <div class="card-body ">
                                <div class="row p-b-20">
                                    <div class="col-md-6 col-sm-6 col-6">
                                        <div class="btn-group">
                                            <a href='<c:url value="/manager/flight/add"/>' id="addRow" class="btn btn-info">
                                                Add New <i class="fa fa-plus"></i>
                                            </a>
                                        </div>
                                    </div>
                                    <div class="col-md-6 col-sm-6 col-6">
                                        <div class="btn-group pull-right">
                                            <a class="btn deepPink-bgcolor  btn-outline dropdown-toggle" data-toggle="dropdown">Tools
                                                <i class="fa fa-angle-down"></i>
                                            </a>
                                            <ul class="dropdown-menu pull-right">
                                                <li>
                                                    <a href="javascript:;">
                                                        <i class="fa fa-print"></i> Print </a>
                                                </li>
                                                <li>
                                                    <a href="javascript:;">
                                                        <i class="fa fa-file-pdf-o"></i> Save as PDF </a>
                                                </li>
                                                <li>
                                                    <a href="javascript:;">
                                                        <i class="fa fa-file-excel-o"></i> Export to Excel </a>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <div class="table-scrollable">
                                    <table class="table table-hover table-checkable order-column full-width" id="example4">
                                        <thead>
                                        <tr>
                                            <th class="center"> Departure </th>
                                            <th class="center"> Destination </th>
                                            <th class="center"> Operation </th>
                                            <th class="center"> Promotion </th>
                                            <th class="center"> Depart </th>
                                            <th class="center"> Arrival </th>
                                            <th class="center"> Status </th>
                                            <th class="center"> Action </th>
                                        </tr>
                                        </thead>
                                        <c:forEach items="${flights}" var="flight">
                                            <tbody>
                                            <tr class="odd gradeX">
                                                <td class="center">${flight.flightRoute.departure.city.cityName}</td>
                                                <td class="center">${flight.flightRoute.destination.city.cityName}</td>
                                                <td class="center">${flight.operation.name}</td>
                                                <td class="center">
                                                    <c:forEach items="${flight.promotions}" var="promotion">
                                                        ${promotion.name}<br/>
                                                    </c:forEach>
                                                </td>
                                                <td class="center">
                                                    <fmt:formatDate value="${flight.depart}" pattern="MM-dd-yyyy HH:mm"/>
                                                </td>
                                                <td class="center">
                                                    <fmt:formatDate value="${flight.arrival}" pattern="MM-dd-yyyy HH:mm"/>
                                                </td>
                                                <td class="center">${flight.status}</td>
                                                <td class="center">
                                                    <a href="<c:url
                                                        value="/manager/flight/edit/${flight.id}"/> "
                                                       class="btn btn-tbl-edit btn-xs">
                                                        <i class="fa fa-pencil"></i>
                                                    </a>
<%--                                                    <button onclick="location.href='<c:url value="/manager/flight/delete/${flight.id}"/>'" class="btn btn-tbl-delete btn-xs">--%>
<%--                                                        <i class="fa fa-trash-o "></i>--%>
<%--                                                    </button>--%>
                                                </td>
                                            </tr>
                                            </tbody>
                                        </c:forEach>
                                    </table>
                                </div>
                            </div>
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
</body>
</html>
