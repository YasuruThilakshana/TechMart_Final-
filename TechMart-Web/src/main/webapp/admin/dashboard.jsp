<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="lk.jiat.techmart.entity.User" %>
<%@ page import="lk.jiat.techmart.enums.UserRole" %>

<%
    User user = (User) session.getAttribute("loggedUser");

    if(user == null){
        response.sendRedirect(request.getContextPath()+"/login.jsp");
        return;
    }

    if(user.getRole() != UserRole.ADMIN){
        response.sendRedirect(request.getContextPath()+"/login.jsp");
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

<body>

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

    <div class="alert alert-success">

        Welcome

        <strong>

            <%=user.getFirstName()%>

        </strong>

    </div>

    <div class="row">

        <div class="col-md-3">

            <div class="card">

                <div class="card-body text-center">

                    <h5>Products</h5>

                </div>

            </div>

        </div>

        <div class="col-md-3">

            <div class="card">

                <div class="card-body text-center">

                    <h5>Categories</h5>

                </div>

            </div>

        </div>

        <div class="col-md-3">

            <div class="card">

                <div class="card-body text-center">

                    <h5>Orders</h5>

                </div>

            </div>

        </div>

        <div class="col-md-3">

            <div class="card">

                <div class="card-body text-center">

                    <h5>Customers</h5>

                </div>

            </div>

        </div>

    </div>

</div>

</body>
</html>