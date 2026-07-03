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

            <form action="${pageContext.request.contextPath}/admin/orders/update-status"
                  method="post"
                  class="mb-3">

                <input type="hidden"
                       name="orderId"
                       value="${order.id}">

                <label class="form-label">

                    <strong>Order Status</strong>

                </label>

                <select name="status"
                        class="form-select mb-3">

                    <option value="PENDING"
                    ${order.status == 'PENDING' ? 'selected' : ''}>
                        PENDING
                    </option>

                    <option value="PROCESSING"
                    ${order.status == 'PROCESSING' ? 'selected' : ''}>
                        PROCESSING
                    </option>

                    <option value="SHIPPED"
                    ${order.status == 'SHIPPED' ? 'selected' : ''}>
                        SHIPPED
                    </option>

                    <option value="DELIVERED"
                    ${order.status == 'DELIVERED' ? 'selected' : ''}>
                        DELIVERED
                    </option>

                    <option value="CANCELLED"
                    ${order.status == 'CANCELLED' ? 'selected' : ''}>
                        CANCELLED
                    </option>

                </select>

                <button type="submit"
                        class="btn btn-primary">

                    Update Status

                </button>

            </form>


            <hr>

            <form action="${pageContext.request.contextPath}/admin/payment/update-status"
                  method="post"
                  class="mb-3">

                <input type="hidden"
                       name="orderId"
                       value="${order.id}">

                <label class="form-label">

                    <strong>Payment Status</strong>

                </label>

                <select name="status"
                        class="form-select mb-3">

                    <option value="PENDING"
                    ${payment.status == 'PENDING' ? 'selected' : ''}>
                        PENDING
                    </option>

                    <option value="PAID"
                    ${payment.status == 'PAID' ? 'selected' : ''}>
                        PAID
                    </option>

                    <option value="FAILED"
                    ${payment.status == 'FAILED' ? 'selected' : ''}>
                        FAILED
                    </option>

                    <option value="REFUNDED"
                    ${payment.status == 'REFUNDED' ? 'selected' : ''}>
                        REFUNDED
                    </option>

                </select>

                <button type="submit"
                        class="btn btn-success">

                    Update Payment

                </button>

            </form>



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