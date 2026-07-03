package lk.jiat.techmart.servlet.customer;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.jiat.techmart.entity.User;
import lk.jiat.techmart.service.OrderService;

import java.io.IOException;

@WebServlet("/customer/my-orders")
public class MyOrdersServlet extends HttpServlet {

    @Inject
    private OrderService orderService;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        User loggedUser =
                (User) request.getSession().getAttribute("loggedUser");

        if (loggedUser == null) {

            response.sendRedirect(
                    request.getContextPath() + "/login"
            );

            return;
        }

        request.setAttribute(
                "orders",
                orderService.findByUserId(loggedUser.getId())
        );

        request.getRequestDispatcher("/customer/my-orders.jsp")
                .forward(request, response);
    }
}