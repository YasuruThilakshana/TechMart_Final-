package lk.jiat.techmart.servlet.product;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lk.jiat.techmart.service.CategoryService;
import lk.jiat.techmart.service.ProductService;

import java.io.IOException;


@WebServlet("/admin/products")
public class ProductListServlet extends HttpServlet {

    @EJB
    private ProductService productService;

    @EJB
    private CategoryService categoryService;


    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute(
                "products",
                productService.findAll()
        );


        request.setAttribute(
                "categories",
                categoryService.findActiveCategories()
        );


        request.getRequestDispatcher(
                "/admin/products.jsp"
        ).forward(
                request,
                response
        );
    }
}