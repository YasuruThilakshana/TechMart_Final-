package lk.jiat.techmart.service.impl;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import lk.jiat.techmart.dao.WarehouseInventoryDAO;
import lk.jiat.techmart.entity.WarehouseInventory;
import lk.jiat.techmart.service.WarehouseInventoryService;

import java.util.List;

@Stateless
public class WarehouseInventoryServiceImpl
        implements WarehouseInventoryService {

    @Inject
    private WarehouseInventoryDAO warehouseInventoryDAO;

    @Override
    public int getTotalAvailableStock(Long productId) {

        return warehouseInventoryDAO
                .getTotalAvailableStock(productId);
    }

    @Override
    public List<WarehouseInventory> getInventoryByProduct(
            Long productId) {

        return warehouseInventoryDAO
                .findByProductId(productId);
    }

    @Override
    public void deductStock(
            Long productId,
            int requiredQuantity) {

        if (requiredQuantity <= 0) {
            throw new IllegalArgumentException(
                    "Required quantity must be greater than zero"
            );
        }

        int totalAvailable =
                warehouseInventoryDAO
                        .getTotalAvailableStock(productId);

        if (totalAvailable < requiredQuantity) {

            throw new RuntimeException(
                    "Insufficient total warehouse stock for product ID: "
                            + productId
            );
        }

        List<WarehouseInventory> inventories =
                warehouseInventoryDAO
                        .findByProductId(productId);

        int remainingQuantity = requiredQuantity;

        for (WarehouseInventory inventory : inventories) {

            if (remainingQuantity == 0) {
                break;
            }

            int available =
                    inventory.getQuantity()
                            - inventory.getReservedQuantity();

            if (available <= 0) {
                continue;
            }

            int deduction =
                    Math.min(available, remainingQuantity);

            inventory.setQuantity(
                    inventory.getQuantity() - deduction
            );

            warehouseInventoryDAO.update(inventory);

            remainingQuantity -= deduction;
        }

        if (remainingQuantity > 0) {
            throw new RuntimeException(
                    "Warehouse stock allocation failed for product ID: "
                            + productId
            );
        }
    }
}