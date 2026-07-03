<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>

<html>

<head>

    <title>Order Success</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet">

</head>

<body>

<div class="container mt-5">

    <div class="card shadow text-center">

        <div class="card-body">

            <h2 class="text-success">

                ✅ Order Placed Successfully!

            </h2>

            <p class="mt-3">

                Your Order ID :

                <strong>#${orderId}</strong>

            </p>

            <a href="${pageContext.request.contextPath}/customer/products"
               class="btn btn-primary">

                Continue Shopping

            </a>

            <a href="${pageContext.request.contextPath}/customer/dashboard.jsp"
               class="btn btn-secondary">

                Customer Dashboard

            </a>

        </div>

    </div>

</div>

</body>

</html>