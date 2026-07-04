package lk.jiat.techmart.dao;

import lk.jiat.techmart.entity.WarehouseInventory;

import java.util.List;
import java.util.Optional;

public interface WarehouseInventoryDAO {

    WarehouseInventory save(WarehouseInventory inventory);

    WarehouseInventory update(WarehouseInventory inventory);

    Optional<WarehouseInventory> findById(Long id);

    List<WarehouseInventory> findByProductId(Long productId);

    Optional<WarehouseInventory> findByWarehouseAndProduct(
            Long warehouseId,
            Long productId
    );

    int getTotalAvailableStock(Long productId);
}