<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="mvc" uri="http://www.springframework.org/tags/form" %>

<c:forEach var="flight" items="${flights}">
  <option value="${flight.key}" >${flight.value.departure.airportName} to ${flight.value.destination.airportName}</option>
</c:forEach>
