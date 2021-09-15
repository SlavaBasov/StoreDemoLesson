<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Store Main</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
          crossorigin="anonymous">
</head>
<body>
<c:if test="${sessionScope.user == null}">
    <c:import url="headers/headerNoneUser.jsp"></c:import>
</c:if>
<c:if test="${sessionScope.user != null}">
    <c:import url="headers/headerUser.jsp"></c:import>
</c:if>

<div class="container" style="margin-top: 20px">
    <h2>Your order is successfully proccessed. Wait for a call from a specialist.</h2>
    <a href="${pageContext.request.contextPath}/" class="btn btn-outline-danger float-none" role="button">Back to main page</a>
</div>

</body>
</html>