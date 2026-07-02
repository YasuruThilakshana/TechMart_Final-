package lk.jiat.techmart.servlet.customer;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.jiat.techmart.entity.User;
import lk.jiat.techmart.service.CartService;

import java.io.IOException;

@WebServlet("/customer/cart/add")
public class AddToCartServlet extends HttpServlet {

    @Inject
    private CartService cartService;

    @Override
    protected void doPost(HttpServletRequest request,
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

        Long productId =
                Long.parseLong(request.getParameter("productId"));

        Integer quantity =
                Integer.parseInt(request.getParameter("quantity"));

        cartService.addToCart(
                loggedUser.getId(),
                productId,
                quantity
        );

        response.sendRedirect(
                request.getContextPath() + "/customer/products"
        );
    }
}