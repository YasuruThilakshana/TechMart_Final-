package lk.jiat.techmart.servlet.inventory;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.jiat.techmart.service.InventoryService;

import java.io.IOException;

@WebServlet("/admin/inventory")
public class InventoryListServlet extends HttpServlet {

    @Inject
    private InventoryService inventoryService;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute(
                "inventories",
                inventoryService.findAll()
        );

        request.setAttribute(
                "lowStockItems",
                inventoryService.lowStockProducts()
        );

        request.getRequestDispatcher("/admin/inventory.jsp")
                .forward(request, response);
    }


}