<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Aircraft Page</title>
    <jsp:include page="/WEB-INF/pages/include/management/css-page.jsp"/>
    <style>
        .my-error-class {
            color:#FF0000;  /* red */
        }
        .img-width-80 {
            width: 150px;
            height: auto;
        }
        .img-margin-10 {
            margin: 0px 5px 0px 5px;
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
                                <div class="page-title">Update Aircraft</div>
                            </c:if>
                            <c:if test="${action == 'update'}">
                                <div class="page-title">Edit Aircraft</div>
                            </c:if>
                        </div>
                        <ol class="breadcrumb page-breadcrumb pull-right">
                            <li><i class="fa fa-home"></i>&nbsp;<a class="parent-item" href="index.html">Home</a>&nbsp;<i class="fa fa-angle-right"></i>
                            </li>
                            <li><a class="parent-item" href="">Staff</a>&nbsp;<i class="fa fa-angle-right"></i>
                            </li>
                            <c:if test="${action == 'add'}">
                                <li class="active">Add Aircraft</li>
                            </c:if>
                            <c:if test="${action == 'update'}">
                                <li class="active">Edit Aircraft</li>
                            </c:if>

                        </ol>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <div class="card-box">
                            <div class="card-head">
                                <c:if test="${action == 'add'}">
                                    <header>Aircraft Information</header>
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
                            <mvc:form action="${pageContext.request.contextPath}/manager/aircraft/result"
                                      modelAttribute="aircraft" method="post" enctype="multipart/form-data" id="aircraft">
                                <div class="card-body row">
                                    <input name="action" type="text" value="${action}" hidden/>
                                    <c:if test="${action == 'update'}">
                                        <input class="mdl-textfield__input" type="text" value="${aircraft.id}" name="id" hidden/>
                                    </c:if>
                                    <div class="col-lg-6 p-t-20">
                                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label txt-full-width">
                                            <input class="mdl-textfield__input" type="text" value="${aircraft.name}" name="name">
                                            <label class="mdl-textfield__label">Name</label>
                                        </div>
                                    </div>
                                    <div class="col-lg-6 p-t-20">
                                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label txt-full-width">
                                            <input class="mdl-textfield__input" type="number" value="${aircraft.number}" name="number">
                                            <label class="mdl-textfield__label">Number</label>
                                        </div>
                                    </div>
                                    <div class="col-lg-6 p-t-20">
                                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label txt-full-width">
                                            <input class="mdl-textfield__input" type="number" value="${aircraft.rowNumber}"
                                                   name="rowNumber">
                                            <label class="mdl-textfield__label">Row</label>
                                        </div>
                                    </div>
                                    <div class="col-lg-6 p-t-20">
                                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label txt-full-width">
                                            <input class="mdl-textfield__input" type="text" value="${aircraft.colNumber}"
                                                   name="colNumber">
                                            <label class="mdl-textfield__label">Column</label>
                                        </div>
                                    </div>
                                    <div class="col-lg-6 p-t-20">
                                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label txt-full-width">
                                            <select name="status" class="mdl-textfield__input">
                                                <c:forEach items="${status}" var="s">
                                                    <c:if test="${s == aircraft.status}">
                                                        <option value="${s}" selected>${s}</option>
                                                    </c:if>
                                                    <c:if test="${s != aircraft.status}">
                                                        <option value="${s}">${s}</option>
                                                    </c:if>
                                                </c:forEach>
                                            </select>
                                            <label class="mdl-textfield__label">Status</label>
                                            <span class="mdl-textfield__error">Text required!</span>
                                        </div>
                                    </div>
                                    <div class="col-lg-12 p-t-20">
                                        <label class="control-label col-md-3">Upload Room Photos</label>
                                        <br/>
                                        <c:if test="${action == 'update'}">
                                            <table>
                                                <tr>
                                                    <c:forEach items="${images}" var="image" varStatus="index">
                                                        <td>
                                                            <div class="image-area">
                                                                <img
                                                                     src="<c:url value="/resources-management/image/aircraft/"/>${image.name}"/>
                                                                <br/>
                                                                <a class="remove-image" href="#" style="display: inline;">&#215;</a>
                                                            </div>
                                                        </td>

                                                    </c:forEach>
                                                </tr>
                                            </table>

                                        </c:if>
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
        $.validator.addMethod("minletter", function (value, element) {
            return this.optional(element) || /^[A-Za-z]$/i.test(value);
        }, "Please enter column.");
        $("#aircraft").validate({
            errorClass: "my-error-class",
            rules: {
                name: {
                    required: true
                },
                number: {
                    required: true,
                    maxlength: 7
                },
                rowNumber: {
                    required: true,
                    maxlength: 2
                },
                colNumber: {
                    required: true,
                    minletter: true
                }
            },
            messages: {
                name: "Please enter aircraft name.",
                number: {
                   required: "Please enter number aircraft.",
                   maxlength: "Number aircraft maximum of 5 characters."
                },
                rowNumber: {
                    required: "Please enter row number.",
                    maxlength: "Row number aircraft maximum of 7 characters."
                },
                colNumber: {
                    required: "Please enter column number.",
                    minletter: "Column number aircraft must be at least 1 letter long."
                }
            },
            submitHandler: function(form) {
                form.submit();
            }
        });
    });
    var images= document.querySelectorAll(".img-width-80.img-margin-10")
    var imageArr = Array.prototype.slice.call(images);

</script>
</body>
</html>
