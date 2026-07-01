package lk.jiat.techmart.servlet.product;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.jiat.techmart.entity.Product;
import lk.jiat.techmart.service.ProductService;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/products")
public class ProductListServlet extends HttpServlet {

    @Inject
    private ProductService productService;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        List<Product> products = productService.findAll();

        request.setAttribute("products", products);

        request.getRequestDispatcher("/admin/products.jsp")
                .forward(request, response);
    }
}