<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>

<head>

    <title>Admin - Order Details</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet">

</head>

<body>

<div class="container mt-4">

    <h2 class="mb-4">

        Order #${order.id}

    </h2>

    <div class="card mb-4">

        <div class="card-header">

            Customer Information

        </div>

        <div class="card-body">

            <p>

                <strong>Name :</strong>

                ${order.user.firstName}
                ${order.user.lastName}

            </p>

            <p>

                <strong>Email :</strong>

                ${order.user.email}

            </p>

            <p>

                <strong>Phone :</strong>

                ${order.user.phone}

            </p>

            <p>

                <strong>Order Date :</strong>

                ${order.orderDate}

            </p>

            <p>

                <strong>Status :</strong>

                <span class="badge bg-primary">

                    ${order.status}

                </span>

            </p>

            <p>

                <strong>Total :</strong>

                Rs. ${order.totalAmount}

            </p>

        </div>

    </div>

    <table class="table table-bordered">

        <thead class="table-dark">

        <tr>

            <th>Product</th>
            <th>Quantity</th>
            <th>Unit Price</th>
            <th>Subtotal</th>

        </tr>

        </thead>

        <tbody>

        <c:forEach items="${items}" var="item">

            <tr>

                <td>${item.product.name}</td>

                <td>${item.quantity}</td>

                <td>Rs. ${item.unitPrice}</td>

                <td>Rs. ${item.subtotal}</td>

            </tr>

        </c:forEach>

        </tbody>

    </table>

    <a href="${pageContext.request.contextPath}/admin/orders"
       class="btn btn-secondary">

        Back

    </a>

</div>

</body>

</html>