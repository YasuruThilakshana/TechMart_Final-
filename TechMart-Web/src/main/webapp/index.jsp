<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>

<head>

    <title>TechMart - Home</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet">

</head>

<body class="bg-light">

<nav class="navbar navbar-dark bg-primary">

    <div class="container">

        <span class="navbar-brand fw-bold">

            TechMart Enterprise System

        </span>

    </div>

</nav>

<div class="container">

    <div class="row justify-content-center mt-5">

        <div class="col-lg-8">

            <div class="card shadow">

                <div class="card-body text-center p-5">

                    <h1 class="display-5 mb-3">

                        Welcome to TechMart

                    </h1>

                    <p class="lead">

                        Enterprise E-Commerce Management System

                    </p>

                    <hr>

                    <p>

                        This system provides complete product management,
                        inventory management, shopping cart, checkout,
                        order management and customer management using
                        Jakarta EE Enterprise technologies.

                    </p>

                    <div class="mt-4">

                        <a href="${pageContext.request.contextPath}/login.jsp"
                           class="btn btn-primary btn-lg me-2">

                            Login

                        </a>

                        <a href="${pageContext.request.contextPath}/register.jsp"
                           class="btn btn-success btn-lg">

                            Register

                        </a>

                    </div>

                </div>

            </div>

        </div>

    </div>

</div>

</body>

</html>