<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="lk.jiat.techmart.entity.Order" %>

<%
    List<Order> orders = (List<Order>) request.getAttribute("orders");
%>

<!DOCTYPE html>
<html>
<head>

    <title>My Orders</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet">

</head>

<body>

<div class="container mt-4">

    <h2 class="mb-4">My Orders</h2>

    <table class="table table-bordered table-hover">

        <thead class="table-dark">

        <tr>

            <th>Order ID</th>
            <th>Date</th>
            <th>Status</th>
            <th>Total</th>
            <th>Action</th>

        </tr>

        </thead>

        <tbody>

        <%

            if (orders != null && !orders.isEmpty()) {

                for (Order order : orders) {

        %>

        <tr>

            <td><%= order.getId() %></td>

            <td><%= order.getOrderDate() %></td>

            <td>

                <span class="badge bg-primary">

                    <%= order.getStatus() %>

                </span>

            </td>

            <td>Rs. <%= order.getTotalAmount() %></td>

            <td>

                <a href="<%=request.getContextPath()%>/customer/order-details?id=<%=order.getId()%>"
                   class="btn btn-sm btn-success">

                    View

                </a>

            </td>

        </tr>

        <%

            }

        } else {

        %>

        <tr>

            <td colspan="5" class="text-center">

                No Orders Found

            </td>

        </tr>

        <%

            }

        %>

        </tbody>

    </table>

</div>

</body>

</html>