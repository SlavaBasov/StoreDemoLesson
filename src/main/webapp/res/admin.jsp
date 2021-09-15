<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Profile</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
          crossorigin="anonymous">
</head>
<body>
<c:import url="headers/headerUser.jsp"></c:import>
<div class="container"  style="margin-top: 20px;">
    <p>Login: ${sessionScope.user.login}</p>
    <p>Password: ${sessionScope.user.password}</p>
    <p>Id: ${sessionScope.user.id}</p>
</div>

</body>
</html>
