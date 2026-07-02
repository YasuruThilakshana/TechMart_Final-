package lk.jiat.techmart.servlet.product;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.jiat.techmart.entity.Category;
import lk.jiat.techmart.entity.Inventory;
import lk.jiat.techmart.entity.Product;
import lk.jiat.techmart.service.ProductService;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import lk.jiat.techmart.service.CategoryService;

@WebServlet("/admin/products/save")
public class ProductSaveServlet extends HttpServlet {

    @Inject
    private ProductService productService;


    @Inject
    private CategoryService categoryService;

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        Product product = new Product();

        product.setName(request.getParameter("name"));
        product.setDescription(request.getParameter("description"));
        product.setPrice(new BigDecimal(request.getParameter("price")));
        product.setActive(true);

        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());


        Long categoryId =
                Long.parseLong(request.getParameter("categoryId"));

        categoryService.findById(categoryId)
                .ifPresent(product::setCategory);

        Inventory inventory = new Inventory();
        inventory.setProduct(product);

        inventory.setQuantity(
                Integer.parseInt(request.getParameter("quantity"))
        );

        inventory.setReorderLevel(
                Integer.parseInt(request.getParameter("reorderLevel"))
        );

        inventory.setLastUpdated(LocalDateTime.now());

        product.setInventory(inventory);

        productService.save(product);

        response.sendRedirect(
                request.getContextPath() + "/admin/products"
        );
    }
}