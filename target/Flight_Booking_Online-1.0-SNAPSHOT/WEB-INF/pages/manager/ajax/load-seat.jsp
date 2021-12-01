<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="mvc" uri="http://www.springframework.org/tags/form" %>

<c:forEach var="seat" items="${seats}">
    <option value="${seat.key}" >${seat.value}</option>
</c:forEach>
