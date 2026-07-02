package lk.jiat.techmart.dao.impl;

import jakarta.ejb.Stateless;
import lk.jiat.techmart.dao.InventoryDAO;
import lk.jiat.techmart.entity.Inventory;

import java.util.List;
import java.util.Optional;

@Stateless
public class InventoryDAOImpl extends AbstractDAO<Inventory, Long>
        implements InventoryDAO {

    @Override
    public Optional<Inventory> findByProductId(Long productId) {

        return entityManager.createQuery(
                        "SELECT i FROM Inventory i WHERE i.product.id = :productId",
                        Inventory.class)
                .setParameter("productId", productId)
                .getResultStream()
                .findFirst();
    }

    @Override
    public List<Inventory> lowStockProducts() {

        return entityManager.createQuery(
                        "SELECT i FROM Inventory i WHERE i.quantity <= i.reorderLevel",
                        Inventory.class)
                .getResultList();
    }
}