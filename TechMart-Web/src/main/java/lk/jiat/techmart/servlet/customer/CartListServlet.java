package lk.jiat.techmart.servlet.customer;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lk.jiat.techmart.entity.User;
import lk.jiat.techmart.service.CartService;

import java.io.IOException;


@WebServlet("/customer/cart")
public class CartListServlet extends HttpServlet {

    @EJB
    private CartService cartService;


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


        request.setAttribute(
                "cartItems",
                cartService.getCartItems(loggedUser.getId())
        );


        request.getRequestDispatcher(
                "/customer/cart.jsp"
        ).forward(
                request,
                response
        );
    }
}