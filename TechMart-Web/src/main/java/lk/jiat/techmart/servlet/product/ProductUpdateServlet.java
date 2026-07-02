package lk.jiat.techmart.servlet.product;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.jiat.techmart.entity.Inventory;
import lk.jiat.techmart.entity.Product;
import lk.jiat.techmart.service.CategoryService;
import lk.jiat.techmart.service.ProductService;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@WebServlet("/admin/products/update")
public class ProductUpdateServlet extends HttpServlet {

    @Inject
    private ProductService productService;

    @Inject
    private CategoryService categoryService;

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        Long productId = Long.parseLong(request.getParameter("id"));

        Optional<Product> optionalProduct = productService.findById(productId);

        if (optionalProduct.isPresent()) {

            Product product = optionalProduct.get();

            product.setName(request.getParameter("name"));
            product.setDescription(request.getParameter("description"));
            product.setPrice(new BigDecimal(request.getParameter("price")));

            Long categoryId = Long.parseLong(request.getParameter("categoryId"));

            categoryService.findById(categoryId)
                    .ifPresent(product::setCategory);

            product.setUpdatedAt(LocalDateTime.now());

            Inventory inventory = product.getInventory();

            if (inventory != null) {

                inventory.setQuantity(
                        Integer.parseInt(request.getParameter("quantity"))
                );

                inventory.setReorderLevel(
                        Integer.parseInt(request.getParameter("reorderLevel"))
                );

                inventory.setLastUpdated(LocalDateTime.now());
            }

            productService.update(product);
        }

        response.sendRedirect(
                request.getContextPath() + "/admin/products"
        );
    }
}