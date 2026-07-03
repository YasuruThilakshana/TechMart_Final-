<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="lk.jiat.techmart.entity.User" %>
<%@ page import="lk.jiat.techmart.enums.UserRole" %>

<%
    User user = (User) session.getAttribute("loggedUser");

    if(user == null){
        response.sendRedirect(request.getContextPath()+"/login.jsp");
        return;
    }

    if(user.getRole() != UserRole.CUSTOMER){
        response.sendRedirect(request.getContextPath()+"/login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>

<head>

    <title>Customer Dashboard</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet">

</head>

<body>

<nav class="navbar navbar-primary bg-primary">

    <div class="container-fluid">

        <span class="navbar-brand text-white">

            TechMart Customer Dashboard

        </span>

        <a href="<%=request.getContextPath()%>/logout"
           class="btn btn-warning">

            Logout

        </a>

    </div>

</nav>

<div class="container mt-5">

    <div class="alert alert-info">

        Welcome

        <strong>

            <%=user.getFirstName()%>

        </strong>

    </div>

    <div class="row">

        <div class="col-md-4">

            <div class="card">

                <div class="card-body text-center">

                    <a href="${pageContext.request.contextPath}/customer/products"
                       class="btn btn-success">

                        Browse Products

                    </a>

                </div>

            </div>

        </div>

        <div class="col-md-4">

            <div class="card">

                <div class="card-body text-center">

                    <div class="col-md-4">



                                <a href="${pageContext.request.contextPath}/customer/cart"
                                   class="btn btn-primary">

                                    My Cart

                                </a>


                    </div>

                </div>

            </div>

        </div>

        <div class="col-md-4">

            <div class="card">

                <div class="card-body text-center">

                    <div class="col-md-4">



                        <a href="${pageContext.request.contextPath}/customer/my-orders"
                           class="btn btn-dark">

                            My Orders

                        </a>


                    </div>

                </div>

            </div>

        </div>

    </div>

</div>

</body>

</html>