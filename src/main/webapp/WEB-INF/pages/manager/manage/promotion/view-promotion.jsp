
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Home</title>
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
                            <div class="page-title">All Promotion</div>
                        </div>
                        <ol class="breadcrumb page-breadcrumb pull-right">
                            <li><i class="fa fa-home"></i>&nbsp;<a class="parent-item" href="index.html">Home</a>&nbsp;<i class="fa fa-angle-right"></i>
                            </li>
                            <li><a class="parent-item" href="">Promotion</a>&nbsp;<i class="fa fa-angle-right"></i>
                            </li>
                            <li class="active">All Promotion</li>
                        </ol>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <div class="card card-box">
                            <div class="card-head">
                                <header>All Promotion</header>
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
                                            <a href='<c:url value="/manager/promotion/add-promotion"/>' id="addRow" class="btn btn-info">
                                                Add New <i class="fa fa-plus"></i>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                                <div class="table-scrollable">
                                    <table class="table table-hover table-checkable order-column full-width" id="example4">
                                        <thead>
                                        <tr>
                                            <th class="center"> Image </th>
                                            <th class="center"> Name </th>
                                            <th class="center"> Start Date </th>
                                            <th class="center"> End Date </th>
                                            <th class="center"> Description </th>
                                            <th class="center"> Discount </th>
                                            <th class="center"> Status </th>
                                            <th class="center"> Action </th>
                                        </tr>
                                        </thead>
                                        <c:forEach items="${promotions.content}" var="promotion">
                                            <tbody>
                                            <tr class="odd gradeX">
                                                <td class="user-circle-img">
                                                    <c:forEach items="${promotion.images}" var="image">
                                                        <img class="img-w-100-h-75"
                                                             src="<c:url value="/resources-management/image/promotion/"/>${image.name}"/>
                                                    </c:forEach>
                                                </td>
                                                <td class="center">${promotion.name}</td>
                                                <td class="center">${promotion.startDate}</td>
                                                <td class="center">${promotion.endDate}</td>
                                                <td class="center">${promotion.description}</td>
                                                <td class="center">${promotion.discount}%</td>
                                                <td class="center">${promotion.status}</td>
                                                <td>
                                                    <img src="" alt="">
                                                </td>
                                                <td class="center">
                                                    <a href="<c:url
                                                        value="/manager/promotion/edit/${promotion.id}"/> "
                                                       class="btn btn-tbl-edit btn-xs">
                                                        <i class="fa fa-pencil"></i>
                                                    </a>
                                                </td>
                                            </tr>
                                            </tbody>
                                        </c:forEach>
                                    </table>
                                </div>
                                <div class="row p-b-20">
                                    <nav aria-label="Page navigation example" style="text-align: center">
                                        <ul class="pagination">
                                            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/manager/promotion/view?page=0">First</a></li>
                                            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/manager/promotion/view?page=${promotions.number - 1}">Previous</a></li>
                                            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/manager/promotion/view?page=${promotions.number + 1}">Next</a></li>
                                            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/manager/promotion/view?page=${promotions.totalPages - 1}">Last</a></li>
                                        </ul>
                                    </nav>
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