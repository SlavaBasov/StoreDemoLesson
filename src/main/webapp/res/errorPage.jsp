<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign in</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
          crossorigin="anonymous">
</head>
<body>
    <div class="card mx-auto mt-5" style="width: 40rem;">
        <div class="card-body">
            <p>${sessionScope.error}</p>
            <br>
            <a href="${pageContext.request.contextPath}/entry" class="btn btn-outline-danger float-none" role="button">Back</a>
        </div>
    </div>
</body>
</html>