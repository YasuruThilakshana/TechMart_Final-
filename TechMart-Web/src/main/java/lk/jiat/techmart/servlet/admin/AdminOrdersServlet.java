package lk.jiat.techmart.servlet.admin;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.jiat.techmart.entity.User;
import lk.jiat.techmart.enums.UserRole;
import lk.jiat.techmart.service.OrderService;

import java.io.IOException;

@WebServlet("/admin/orders")
public class AdminOrdersServlet extends HttpServlet {

    @Inject
    private OrderService orderService;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        User loggedUser =
                (User) request.getSession().getAttribute("loggedUser");

        if (loggedUser == null ||
                loggedUser.getRole() != UserRole.ADMIN) {

            response.sendRedirect(
                    request.getContextPath() + "/login"
            );

            return;
        }

        request.setAttribute(
                "orders",
                orderService.findAll()
        );

        request.getRequestDispatcher("/admin/orders.jsp")
                .forward(request, response);
    }
}