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
    <h2>Products</h2>
    <c:forEach var="product" items="${requestScope.products}">
        <div class="card col-3" style="width: 18rem;">
            <img src="${product.imgPath}" class="card-img-top" style="height: 200px" alt="...">
            <div class="card-body">
                <h5 class="card-title">${product.name}</h5>
                <p class="card-text">${product.description}</p>
                <p class="card-text">Price: ${product.price}</p>
                <c:if test="${sessionScope.user == null}">
                    <a href="${pageContext.request.contextPath}/entry" class="btn btn-primary">Add</a>
                </c:if>
                <c:if test="${sessionScope.user != null}">
                    <a href="${pageContext.request.contextPath}/addprod?product_id=${product.id}" class="btn btn-primary">Add</a>
                </c:if>

            </div>
        </div>
    </c:forEach>

</div>
<div class="container row" style="margin-top: 20px; margin-left: 15px;">
<c:if test="${sessionScope.receipt != null}">
    <h2>Basket</h2>
    <p>
        <c:forEach var="product" items="${sessionScope.receipt.products}">
            ${product.name}
        </c:forEach>
    </p>
    <a href="${pageContext.request.contextPath}/basket" class="btn btn-primary col-2">To basket</a>
</c:if>
</div>
</body>
</html>
