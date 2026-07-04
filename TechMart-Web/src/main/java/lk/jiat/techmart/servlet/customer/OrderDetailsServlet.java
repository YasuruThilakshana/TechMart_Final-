package lk.jiat.techmart.servlet.customer;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lk.jiat.techmart.entity.User;
import lk.jiat.techmart.service.OrderItemService;
import lk.jiat.techmart.service.OrderService;

import java.io.IOException;


@WebServlet("/customer/order-details")
public class OrderDetailsServlet extends HttpServlet {

    @EJB
    private OrderService orderService;

    @EJB
    private OrderItemService orderItemService;


    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        User loggedUser =
                (User) request.getSession()
                        .getAttribute("loggedUser");


        if (loggedUser == null) {

            response.sendRedirect(
                    request.getContextPath() + "/login"
            );

            return;
        }


        Long orderId =
                Long.parseLong(
                        request.getParameter("id")
                );


        orderService.findById(orderId)
                .ifPresent(order -> {

                    request.setAttribute(
                            "order",
                            order
                    );

                    request.setAttribute(
                            "items",
                            orderItemService.findByOrderId(orderId)
                    );
                });


        request.getRequestDispatcher(
                "/customer/order-details.jsp"
        ).forward(
                request,
                response
        );
    }
}