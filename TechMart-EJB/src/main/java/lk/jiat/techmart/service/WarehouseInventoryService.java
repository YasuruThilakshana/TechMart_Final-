package lk.jiat.techmart.service;

import lk.jiat.techmart.entity.WarehouseInventory;

import java.util.List;

public interface WarehouseInventoryService {

    int getTotalAvailableStock(Long productId);

    List<WarehouseInventory> getInventoryByProduct(Long productId);

    void deductStock(Long productId, int requiredQuantity);
}