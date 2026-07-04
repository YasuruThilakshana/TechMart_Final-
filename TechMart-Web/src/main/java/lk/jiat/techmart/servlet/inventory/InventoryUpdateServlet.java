package lk.jiat.techmart.servlet.inventory;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lk.jiat.techmart.entity.Inventory;
import lk.jiat.techmart.service.InventoryService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;


@WebServlet("/admin/inventory/update")
public class InventoryUpdateServlet extends HttpServlet {

    @EJB
    private InventoryService inventoryService;


    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        Long inventoryId =
                Long.parseLong(
                        request.getParameter("id")
                );


        Optional<Inventory> optionalInventory =
                inventoryService.findById(inventoryId);


        if (optionalInventory.isPresent()) {

            Inventory inventory =
                    optionalInventory.get();


            inventory.setQuantity(
                    Integer.parseInt(
                            request.getParameter("quantity")
                    )
            );


            inventory.setReorderLevel(
                    Integer.parseInt(
                            request.getParameter("reorderLevel")
                    )
            );


            inventory.setLastUpdated(
                    LocalDateTime.now()
            );


            inventoryService.update(inventory);
        }


        response.sendRedirect(
                request.getContextPath()
                        + "/admin/inventory"
        );
    }
}