package lk.jiat.techmart.servlet.product;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lk.jiat.techmart.service.ProductService;

import java.io.IOException;


@WebServlet("/admin/products/delete")
public class ProductDeleteServlet extends HttpServlet {

    @EJB
    private ProductService productService;


    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        Long id =
                Long.parseLong(
                        request.getParameter("id")
                );


        productService.delete(id);


        response.sendRedirect(
                request.getContextPath()
                        + "/admin/products"
        );
    }
}