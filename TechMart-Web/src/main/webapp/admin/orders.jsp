<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>

    <title>Admin - Orders</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet">

</head>

<body>

<div class="container mt-4">

    <a class="nav-link"
       href="${pageContext.request.contextPath}/admin/dashboard.jsp">
        Dashboard
    </a>


    <h2 class="mb-4">

        Order Management

    </h2>

    <table class="table table-bordered table-hover">

        <thead class="table-dark">

        <tr>

            <th>ID</th>
            <th>Customer</th>
            <th>Date</th>
            <th>Status</th>
            <th>Total</th>
            <th>Action</th>

        </tr>

        </thead>

        <tbody>


<c:choose>

    <c:when test="${not empty orders}">

        <c:forEach items="${orders}" var="order">

            <tr>

                <td>${order.id}</td>

                <td>${order.user.firstName} ${order.user.lastName}</td>

                <td>${order.orderDate}</td>

                <td>

                    <span class="badge bg-primary">

                            ${order.status}

                    </span>

                </td>

                <td>

                    Rs. ${order.totalAmount}

                </td>

                <td>

                    <a href="${pageContext.request.contextPath}/admin/order-details?id=${order.id}"
                       class="btn btn-success btn-sm">

                        View

                    </a>

                </td>

            </tr>

        </c:forEach>

    </c:when>

    <c:otherwise>

        <tr>

            <td colspan="6"
                class="text-center">

                No Orders Found

            </td>

        </tr>

    </c:otherwise>

</c:choose>

        </tbody>

    </table>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>

</html>