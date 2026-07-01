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
import java.util.List;

@WebServlet("/admin/categories")
public class CategoryListServlet extends HttpServlet {

    @Inject
    private CategoryService categoryService;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        List<Category> categories = categoryService.findAll();

        request.setAttribute("categories", categories);

        request.getRequestDispatcher("/admin/categories.jsp")
                .forward(request, response);
    }
}