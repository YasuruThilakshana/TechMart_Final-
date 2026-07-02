package lk.jiat.techmart.dao;

import lk.jiat.techmart.entity.Inventory;

import java.util.List;
import java.util.Optional;

public interface InventoryDAO extends GenericDAO<Inventory, Long> {

    Optional<Inventory> findByProductId(Long productId);

    List<Inventory> lowStockProducts();

}