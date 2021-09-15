<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Products</title>
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

<div class="container row" style="margin-top: 20px; margin-left: 15px;">
    <c:if test="${sessionScope.receipt != null}">
        <h2>Basket</h2>
        <p>
            <c:forEach var="product" items="${sessionScope.receipt.products}">
                    <p>${product.name}  -  ${product.price}</p>
            </c:forEach>
        </p>
    </c:if>
    <p><b>Sum</b>: ${sessionScope.sum}</p>
    <a href="${pageContext.request.contextPath}/order" class="btn btn-outline-danger float-none col-2" role="button">Order</a>
</div>

</body>
</html>
