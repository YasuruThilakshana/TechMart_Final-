package lk.jiat.techmart.dao.impl;

import jakarta.ejb.Stateless;
import lk.jiat.techmart.dao.ProductDAO;
import lk.jiat.techmart.entity.Product;

import java.util.List;

@Stateless
public class ProductDAOImpl extends AbstractDAO<Product, Long> implements ProductDAO {

    @Override
    public List<Product> findActiveProducts() {

        return entityManager.createQuery(
                "SELECT p FROM Product p WHERE p.active = true",
                Product.class
        ).getResultList();
    }

    @Override
    public List<Product> findByCategory(Long categoryId) {

        return entityManager.createQuery(
                        "SELECT p FROM Product p WHERE p.category.id = :categoryId",
                        Product.class
                )
                .setParameter("categoryId", categoryId)
                .getResultList();
    }

    @Override
    public List<Product> searchProducts(String keyword) {

        return entityManager.createQuery(
                        """
                        SELECT p
                        FROM Product p
                        WHERE LOWER(p.name) LIKE LOWER(:keyword)
                           OR LOWER(p.description) LIKE LOWER(:keyword)
                        """,
                        Product.class
                )
                .setParameter("keyword", "%" + keyword + "%")
                .getResultList();
    }
}