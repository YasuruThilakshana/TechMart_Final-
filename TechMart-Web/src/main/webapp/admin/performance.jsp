<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Collection" %>
<%@ page import="lk.jiat.techmart.performance.PerformanceMetric" %>
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

    Collection<PerformanceMetric> metrics =
            (Collection<PerformanceMetric>) request.getAttribute("metrics");
%>

<!DOCTYPE html>
<html>

<head>

    <title>Performance Dashboard</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet">

</head>

<body>

<nav class="navbar navbar-dark bg-dark">

    <div class="container-fluid">

        <span class="navbar-brand">
            Performance Dashboard
        </span>

        <a href="<%=request.getContextPath()%>/admin/dashboard.jsp"
           class="btn btn-primary">

            Back

        </a>

    </div>

</nav>

<div class="container mt-4">

    <div class="card shadow">

        <div class="card-header bg-primary text-white">

            Performance Metrics

        </div>

        <div class="card-body">

            <table class="table table-bordered table-striped">

                <thead class="table-dark">

                <tr>

                    <th>Method</th>
                    <th>Executions</th>
                    <th>Average (ms)</th>
                    <th>Minimum (ms)</th>
                    <th>Maximum (ms)</th>

                </tr>

                </thead>

                <tbody>

                <%

                    if (metrics != null) {

                        for (PerformanceMetric metric : metrics) {

                %>

                <tr>

                    <td><%=metric.getMethodName()%></td>

                    <td><%=metric.getExecutionCount()%></td>

                    <td><%=String.format("%.2f",
                            metric.getAverageExecutionTime())%></td>

                    <td><%=metric.getMinExecutionTime()%></td>

                    <td><%=metric.getMaxExecutionTime()%></td>

                </tr>

                <%

                        }

                    }

                %>

                </tbody>

            </table>

        </div>

    </div>

</div>

</body>

</html>