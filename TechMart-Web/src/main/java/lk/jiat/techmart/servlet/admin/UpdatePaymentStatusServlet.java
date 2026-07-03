package lk.jiat.techmart.servlet.admin;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.jiat.techmart.entity.User;
import lk.jiat.techmart.enums.UserRole;
import lk.jiat.techmart.service.PaymentService;

import java.io.IOException;

@WebServlet("/admin/payment/update-status")
public class UpdatePaymentStatusServlet extends HttpServlet {

    @Inject
    private PaymentService paymentService;

    @Override
    protected void doPost(HttpServletRequest request,
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

        Long orderId = Long.parseLong(request.getParameter("orderId"));
        String status = request.getParameter("status");

        paymentService.findByOrderId(orderId).ifPresent(payment -> {

            payment.setStatus(status);

            paymentService.update(payment);

        });

        response.sendRedirect(
                request.getContextPath()
                        + "/admin/order-details?id="
                        + orderId
        );
    }
}