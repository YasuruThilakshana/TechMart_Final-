<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>

<head>

    <title>Register</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet">

</head>

<body class="bg-light">

<div class="container">

    <div class="row justify-content-center mt-5">

        <div class="col-md-6">

            <div class="card shadow">

                <div class="card-header">

                    <h3 class="text-center">

                        Customer Registration

                    </h3>

                </div>

                <div class="card-body">

                    <% if(request.getAttribute("error") != null){ %>

                    <div class="alert alert-danger">

                        <%=request.getAttribute("error")%>

                    </div>

                    <% } %>

                    <form action="register" method="post">

                        <div class="mb-3">

                            <label>First Name</label>

                            <input
                                    class="form-control"
                                    type="text"
                                    name="firstName"
                                    required>

                        </div>

                        <div class="mb-3">

                            <label>Last Name</label>

                            <input
                                    class="form-control"
                                    type="text"
                                    name="lastName"
                                    required>

                        </div>

                        <div class="mb-3">

                            <label>Email</label>

                            <input
                                    class="form-control"
                                    type="email"
                                    name="email"
                                    required>

                        </div>

                        <div class="mb-3">

                            <label>Password</label>

                            <input
                                    class="form-control"
                                    type="password"
                                    name="password"
                                    required>

                        </div>

                        <div class="mb-3">

                            <label>Phone</label>

                            <input
                                    class="form-control"
                                    type="text"
                                    name="phone"
                                    required>

                        </div>

                        <button
                                class="btn btn-success w-100">

                            Register

                        </button>

                    </form>

                    <hr>

                    <a href="login.jsp">

                        Back to Login

                    </a>

                </div>

            </div>

        </div>

    </div>

</div>

</body>

</html>