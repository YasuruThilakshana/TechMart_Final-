package lk.jiat.techmart.servlet.admin;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.jiat.techmart.entity.Order;
import lk.jiat.techmart.entity.User;
import lk.jiat.techmart.enums.UserRole;
import lk.jiat.techmart.service.OrderService;

import java.io.IOException;

@WebServlet("/admin/orders/update-status")
public class UpdateOrderStatusServlet extends HttpServlet {

    @Inject
    private OrderService orderService;

    @Override
    protected void doPost(HttpServletRequest request,
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

        Long orderId =
                Long.parseLong(request.getParameter("orderId"));

        String status =
                request.getParameter("status");

        orderService.findById(orderId).ifPresent(order -> {

            order.setStatus(status);

            orderService.update(order);

        });

        response.sendRedirect(
                request.getContextPath()
                        + "/admin/order-details?id="
                        + orderId
        );
    }
}