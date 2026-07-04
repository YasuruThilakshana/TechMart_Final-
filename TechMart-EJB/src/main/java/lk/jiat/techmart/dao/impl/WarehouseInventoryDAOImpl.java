package lk.jiat.techmart.dao.impl;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lk.jiat.techmart.dao.WarehouseInventoryDAO;
import lk.jiat.techmart.entity.WarehouseInventory;

import java.util.List;
import java.util.Optional;

@Stateless
public class WarehouseInventoryDAOImpl
        implements WarehouseInventoryDAO {

    @PersistenceContext(unitName = "TechMartPU")
    private EntityManager entityManager;

    @Override
    public WarehouseInventory save(WarehouseInventory inventory) {

        entityManager.persist(inventory);
        entityManager.flush();

        return inventory;
    }

    @Override
    public WarehouseInventory update(WarehouseInventory inventory) {

        return entityManager.merge(inventory);
    }

    @Override
    public Optional<WarehouseInventory> findById(Long id) {

        WarehouseInventory inventory =
                entityManager.find(WarehouseInventory.class, id);

        return Optional.ofNullable(inventory);
    }

    @Override
    public List<WarehouseInventory> findByProductId(Long productId) {

        return entityManager.createQuery(
                        "SELECT wi FROM WarehouseInventory wi " +
                                "WHERE wi.product.id = :productId " +
                                "AND wi.warehouse.active = true " +
                                "ORDER BY wi.quantity DESC",
                        WarehouseInventory.class
                )
                .setParameter("productId", productId)
                .getResultList();
    }

    @Override
    public Optional<WarehouseInventory> findByWarehouseAndProduct(
            Long warehouseId,
            Long productId) {

        return entityManager.createQuery(
                        "SELECT wi FROM WarehouseInventory wi " +
                                "WHERE wi.warehouse.id = :warehouseId " +
                                "AND wi.product.id = :productId",
                        WarehouseInventory.class
                )
                .setParameter("warehouseId", warehouseId)
                .setParameter("productId", productId)
                .getResultStream()
                .findFirst();
    }

    @Override
    public int getTotalAvailableStock(Long productId) {

        Number result = (Number) entityManager
                .createQuery(
                        "SELECT COALESCE(SUM(w.quantity - w.reservedQuantity), 0) " +
                                "FROM WarehouseInventory w " +
                                "WHERE w.product.id = :productId"
                )
                .setParameter("productId", productId)
                .getSingleResult();

        return result != null ? result.intValue() : 0;
    }
}