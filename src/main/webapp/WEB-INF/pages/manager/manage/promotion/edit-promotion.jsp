<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Home</title>
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
                                <div class="page-title">Add Promotion</div>
                            </c:if>
                            <c:if test="${action == 'update'}">
                                <div class="page-title">Edit Promotion</div>
                            </c:if>
                        </div>
                        <ol class="breadcrumb page-breadcrumb pull-right">
                            <li><i class="fa fa-home"></i>&nbsp;<a class="parent-item" href="index.html">Home</a>&nbsp;<i class="fa fa-angle-right"></i>
                            </li>
                            <li><a class="parent-item" href="">Staff</a>&nbsp;<i class="fa fa-angle-right"></i>
                            </li>
                            <c:if test="${action == 'add'}">
                                <li class="active">Add Promotion</li>
                            </c:if>
                            <c:if test="${action == 'update'}">
                                <li class="active">Edit Promotion</li>
                            </c:if>

                        </ol>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <div class="card-box">
                            <div class="card-head">
                                <c:if test="${action == 'add'}">
                                    <header>Promotion Information</header>
                                </c:if>
                                <c:if test="${action == 'update'}">
                                    <header>Update Information</header>
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
                            <mvc:form action="${pageContext.request.contextPath}/manager/promotion/result"
                                      modelAttribute="promotion" id="promotion" method="post" enctype="multipart/form-data">
                                <div class="card-body row">
                                    <input name="action" type="text" value="${action}" hidden/>
                                    <c:if test="${action == 'update'}">
                                        <input class="mdl-textfield__input" type="text" value="${promotion.id}" name="id" hidden/>
                                    </c:if>
                                    <div class="col-lg-6 p-t-20">
                                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label txt-full-width">
                                            <input class="mdl-textfield__input" type="text" value="${promotion.name}" name="name">
                                            <label class="mdl-textfield__label">Name</label>
                                        </div>
                                    </div>
                                    <div class="col-lg-6 p-t-20">
                                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label txt-full-width">
                                            <textarea class="mdl-textfield__input" type="text" name="description"></textarea>
                                            <label class="mdl-textfield__label">Description</label>
                                        </div>
                                    </div>
                                    <div class="col-lg-6 p-t-20">
                                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label txt-full-width">
                                            <input class="mdl-textfield__input" type="date" value="${promotion.startDate}" name="startDate"
                                            id="startDate">
                                            <label class="mdl-textfield__label">Start Date</label>
                                            <span class="mdl-textfield__error">Number or text required!</span>
                                        </div>
                                    </div>
                                    <div class="col-lg-6 p-t-20">
                                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label txt-full-width">
                                            <input class="mdl-textfield__input" type="date" value="${promotion.endDate}" name="endDate"
                                            id="endDate">
                                            <label class="mdl-textfield__label">End Date</label>
                                            <span class="mdl-textfield__error">Text required!</span>
                                        </div>
                                    </div>
                                    <div class="col-lg-6 p-t-20">
                                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label txt-full-width">
                                            <input class="mdl-textfield__input" type="number" value="${promotion.discount}"
                                                   pattern="-?[A-Z]*(\.[0-9]+)?" name="discount">
                                            <label class="mdl-textfield__label">Discount</label>
                                            <span class="mdl-textfield__error">Text required!</span>
                                        </div>
                                    </div>
                                    <div class="col-lg-6 p-t-20">
                                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label txt-full-width">
                                            <select name="status" class="mdl-textfield__input">
                                                <c:forEach items="${status}" var="st">
                                                    <option value="${st}"> ${st}</option>
                                                </c:forEach>
                                            </select>
                                            <label class="mdl-textfield__label">Status</label>
                                            <span class="mdl-textfield__error">Number or text required!</span>
                                        </div>
                                    </div>
                                    <div class="col-lg-12 p-t-20">

                                        <label class="control-label col-md-3">Upload Room Photos</label>
                                        <br/>
                                        <input type="file" name="files" id="image" multiple="multiple"/>
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
        $.validator.addMethod('maxStrict', function (value, el, param) {
            return value <= param;
        });
        $("#promotion").validate({
            errorClass: "my-error-class",
            rules: {
                name: {
                    required: true
                },
                description: {
                    required: true,
                    maxlength: 100
                },
                startDate: {
                    required: true,
                    date: true
                },
                endDate: {
                    required: true,
                    date: true
                },
                discount: {
                    required: true,
                    maxlength: 2,
                    digits: true,
                    minStrict: 0,
                    maxStrict: 50
                }
            },
            messages: {
                name: "Please enter promotion name.",
                discount: {
                    required: "Please enter price.",
                    minStrict: "Discount must greater than 0",
                    maxStrict: "Discount must less than 50"
                },
                description: {
                    required: "Please enter description.",
                    maxlength: "Can only enter up to 100 characters!"
                },
                startDate: {
                    required: "Please select start date.",
                    date: "Field must type date."
                },
                endDate: {
                    required: "Please select end date.",
                    date: "Field must type date."
                },
            },
            submitHandler: function(form) {
                form.submit();
            }
        });
    });
</script>
</body>
</html>
