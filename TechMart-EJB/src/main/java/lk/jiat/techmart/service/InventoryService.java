package lk.jiat.techmart.service;

import jakarta.ejb.Local;
import lk.jiat.techmart.entity.Inventory;

import java.util.List;
import java.util.Optional;

@Local
public interface InventoryService {

    Inventory save(Inventory inventory);

    Inventory update(Inventory inventory);

    void delete(Long id);

    Optional<Inventory> findById(Long id);

    Optional<Inventory> findByProductId(Long productId);

    List<Inventory> findAll();

    List<Inventory> lowStockProducts();

}