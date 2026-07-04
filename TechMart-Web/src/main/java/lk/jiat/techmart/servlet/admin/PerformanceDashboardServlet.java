package lk.jiat.techmart.servlet.admin;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lk.jiat.techmart.performance.PerformanceMonitorBean;

import java.io.IOException;


@WebServlet("/admin/performance")
public class PerformanceDashboardServlet extends HttpServlet {

    @EJB
    private PerformanceMonitorBean performanceMonitorBean;


    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute(
                "metrics",
                performanceMonitorBean.getAllMetrics()
        );


        request.getRequestDispatcher(
                "/admin/performance.jsp"
        ).forward(
                request,
                response
        );
    }
}