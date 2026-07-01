<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>

    <title>TechMart Login</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet">

</head>

<body class="bg-light">

<div class="container">

    <div class="row justify-content-center mt-5">

        <div class="col-md-5">

            <div class="card shadow">

                <div class="card-header text-center">

                    <h3>TechMart Login</h3>

                </div>

                <div class="card-body">

                    <% if(request.getAttribute("error") != null){ %>

                    <div class="alert alert-danger">

                        <%=request.getAttribute("error")%>

                    </div>

                    <% } %>

                    <% if(request.getParameter("success") != null){ %>

                    <div class="alert alert-success">

                        Registration Successful.
                        Please Login.

                    </div>

                    <% } %>

                    <form action="login" method="post">

                        <div class="mb-3">

                            <label>Email</label>

                            <input
                                    type="email"
                                    name="email"
                                    class="form-control"
                                    required>

                        </div>

                        <div class="mb-3">

                            <label>Password</label>

                            <input
                                    type="password"
                                    name="password"
                                    class="form-control"
                                    required>

                        </div>

                        <button
                                class="btn btn-primary w-100">

                            Login

                        </button>

                    </form>

                    <hr>

                    <a href="register.jsp">

                        Create New Account

                    </a>

                </div>

            </div>

        </div>

    </div>

</div>

</body>
</html>