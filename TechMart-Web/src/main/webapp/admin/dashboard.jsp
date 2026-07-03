<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="lk.jiat.techmart.entity.User" %>
<%@ page import="lk.jiat.techmart.enums.UserRole" %>

<%
    User user = (User) session.getAttribute("loggedUser");

    if (user == null) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }

    if (user.getRole() != UserRole.ADMIN) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>

<head>

    <title>Admin Dashboard</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet">

</head>

<body class="bg-light">

<nav class="navbar navbar-dark bg-dark">

    <div class="container-fluid">

        <span class="navbar-brand">

            TechMart Admin Dashboard

        </span>

        <a href="<%=request.getContextPath()%>/logout"
           class="btn btn-danger">

            Logout

        </a>

    </div>

</nav>

<div class="container mt-5">

    <div class="alert alert-primary">

        Welcome

        <strong>

            <%=user.getFirstName()%>

        </strong>

        (Administrator)

    </div>

    <div class="row g-4">

        <!-- Products -->

        <div class="col-md-3">

            <div class="card shadow-sm h-100">

                <div class="card-body text-center">

                    <h4>📦</h4>

                    <h5>Products</h5>

                    <p class="text-muted">
                        Add, Update and Delete Products
                    </p>

                    <a href="<%=request.getContextPath()%>/admin/products"
                       class="btn btn-success">

                        Manage Products

                    </a>

                </div>

            </div>

        </div>

        <!-- Categories -->

        <div class="col-md-3">

            <div class="card shadow-sm h-100">

                <div class="card-body text-center">

                    <h4>🗂️</h4>

                    <h5>Categories</h5>

                    <p class="text-muted">
                        Manage Product Categories
                    </p>

                    <a href="<%=request.getContextPath()%>/admin/categories"
                       class="btn btn-primary">

                        Manage Categories

                    </a>

                </div>

            </div>

        </div>

        <!-- Inventory -->

        <div class="col-md-3">

            <div class="card shadow-sm h-100">

                <div class="card-body text-center">

                    <h4>📊</h4>

                    <h5>Inventory</h5>

                    <p class="text-muted">
                        Update Product Stock
                    </p>

                    <a href="<%=request.getContextPath()%>/admin/inventory"
                       class="btn btn-warning">

                        Manage Inventory

                    </a>

                </div>

            </div>

        </div>

        <!-- Orders -->

        <div class="col-md-3">

            <div class="card shadow-sm h-100">

                <div class="card-body text-center">

                    <h4>🛒</h4>

                    <h5>Orders</h5>

                    <p class="text-muted">
                        View and Update Orders
                    </p>

                    <a href="<%=request.getContextPath()%>/admin/orders"
                       class="btn btn-dark">

                        Manage Orders

                    </a>

                </div>

            </div>

        </div>



        <div class="col-md-3">

            <div class="card">

                <div class="card-body text-center">

                    <h5>Performance</h5>

                    <a href="<%=request.getContextPath()%>/admin/performance"
                       class="btn btn-warning mt-2">

                        View Metrics

                    </a>

                </div>

            </div>

        </div>

    </div>

</div>

</body>

</html>