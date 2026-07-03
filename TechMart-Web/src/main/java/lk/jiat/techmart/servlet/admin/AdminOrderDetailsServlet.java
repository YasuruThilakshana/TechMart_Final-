package lk.jiat.techmart.servlet.admin;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.jiat.techmart.entity.User;
import lk.jiat.techmart.enums.UserRole;
import lk.jiat.techmart.service.OrderItemService;
import lk.jiat.techmart.service.OrderService;
import lk.jiat.techmart.service.PaymentService;

import java.io.IOException;

@WebServlet("/admin/order-details")
public class AdminOrderDetailsServlet extends HttpServlet {

    @Inject
    private OrderService orderService;

    @Inject
    private OrderItemService orderItemService;

    @Inject
    private PaymentService paymentService;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        User loggedUser =
                (User) request.getSession().getAttribute("loggedUser");

        if (loggedUser == null ||
                loggedUser.getRole() != UserRole.ADMIN) {

            response.sendRedirect(
                    request.getContextPath() + "/login");

            return;
        }

        Long orderId = Long.parseLong(request.getParameter("id"));

        orderService.findById(orderId).ifPresent(order -> {

            request.setAttribute("order", order);

            request.setAttribute(
                    "items",
                    orderItemService.findByOrderId(orderId)
            );

            paymentService.findByOrderId(orderId)
                    .ifPresent(payment ->
                            request.setAttribute("payment", payment));

        });

        request.getRequestDispatcher("/admin/order-details.jsp")
                .forward(request, response);
    }
}