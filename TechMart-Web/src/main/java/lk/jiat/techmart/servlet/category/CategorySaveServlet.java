package lk.jiat.techmart.servlet.category;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.jiat.techmart.entity.Category;
import lk.jiat.techmart.service.CategoryService;

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/admin/categories/save")
public class CategorySaveServlet extends HttpServlet {

    @Inject
    private CategoryService categoryService;

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        Category category = new Category();

        category.setName(request.getParameter("name"));
        category.setDescription(request.getParameter("description"));
        category.setActive(true);

        category.setCreatedAt(LocalDateTime.now());
        category.setUpdatedAt(LocalDateTime.now());

        categoryService.save(category);

        response.sendRedirect(
                request.getContextPath() + "/admin/categories"
        );
    }
}