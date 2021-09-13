<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
          crossorigin="anonymous">
</head>
<body>
<p>Login: ${sessionScope.user.login}</p>
<a href="${pageContext.request.contextPath}/products" class="btn btn-outline-primary">Products</a>
<a href="${pageContext.request.contextPath}/res/entry.jsp" class="btn btn-outline-danger">Exit</a>
</body>
</html>
