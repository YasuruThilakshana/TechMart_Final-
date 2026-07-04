package lk.jiat.techmart.servlet.category;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lk.jiat.techmart.entity.Category;
import lk.jiat.techmart.service.CategoryService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;


@WebServlet("/admin/categories/update")
public class CategoryUpdateServlet extends HttpServlet {

    @EJB
    private CategoryService categoryService;


    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        Long id =
                Long.parseLong(
                        request.getParameter("id")
                );


        Optional<Category> optionalCategory =
                categoryService.findById(id);


        if (optionalCategory.isPresent()) {

            Category category =
                    optionalCategory.get();


            category.setName(
                    request.getParameter("name")
            );

            category.setDescription(
                    request.getParameter("description")
            );


            String active =
                    request.getParameter("active");

            category.setActive(
                    active != null
            );


            category.setUpdatedAt(
                    LocalDateTime.now()
            );


            categoryService.update(category);
        }


        response.sendRedirect(
                request.getContextPath()
                        + "/admin/categories"
        );
    }
}