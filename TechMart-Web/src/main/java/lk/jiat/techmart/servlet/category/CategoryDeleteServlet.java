package lk.jiat.techmart.servlet.category;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lk.jiat.techmart.service.CategoryService;

import java.io.IOException;


@WebServlet("/admin/categories/delete")
public class CategoryDeleteServlet extends HttpServlet {

    @EJB
    private CategoryService categoryService;


    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        Long id =
                Long.parseLong(
                        request.getParameter("id")
                );


        categoryService.delete(id);


        response.sendRedirect(
                request.getContextPath()
                        + "/admin/categories"
        );
    }
}