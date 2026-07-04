package lk.jiat.techmart.servlet.customer;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lk.jiat.techmart.entity.Order;
import lk.jiat.techmart.entity.User;
import lk.jiat.techmart.service.CheckoutService;

import java.io.IOException;


@WebServlet("/customer/checkout")
public class CheckoutServlet extends HttpServlet {

    @EJB
    private CheckoutService checkoutService;


    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher(
                "/customer/checkout.jsp"
        ).forward(
                request,
                response
        );
    }


    @Override
    protected void doPost(
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


        Order order =
                checkoutService.placeOrder(
                        loggedUser.getId()
                );


        response.sendRedirect(
                request.getContextPath()
                        + "/customer/order-success?id="
                        + order.getId()
        );
    }
}