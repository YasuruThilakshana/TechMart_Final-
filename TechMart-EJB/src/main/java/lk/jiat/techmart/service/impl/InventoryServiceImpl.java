package lk.jiat.techmart.service.impl;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import lk.jiat.techmart.dao.InventoryDAO;
import lk.jiat.techmart.entity.Inventory;
import lk.jiat.techmart.performance.PerformanceMonitor;
import lk.jiat.techmart.service.InventoryService;

import java.util.List;
import java.util.Optional;

@Stateless
@PerformanceMonitor
public class InventoryServiceImpl implements InventoryService {

    @Inject
    private InventoryDAO inventoryDAO;

    @Override
    public Inventory save(Inventory inventory) {
        return inventoryDAO.save(inventory);
    }

    @Override
    public Inventory update(Inventory inventory) {
        return inventoryDAO.update(inventory);
    }

    @Override
    public void delete(Long id) {

        inventoryDAO.findById(id)
                .ifPresent(inventoryDAO::delete);

    }

    @Override
    public Optional<Inventory> findById(Long id) {
        return inventoryDAO.findById(id);
    }

    @Override
    public Optional<Inventory> findByProductId(Long productId) {
        return inventoryDAO.findByProductId(productId);
    }

    @Override
    public List<Inventory> findAll() {
        return inventoryDAO.findAll();
    }

    @Override
    public List<Inventory> lowStockProducts() {
        return inventoryDAO.lowStockProducts();
    }
}