package lk.jiat.techmart.dao.impl;

import jakarta.ejb.Stateless;
import lk.jiat.techmart.dao.CartItemDAO;
import lk.jiat.techmart.entity.CartItem;

import java.util.List;
import java.util.Optional;

@Stateless
public class CartItemDAOImpl extends AbstractDAO<CartItem, Long>
        implements CartItemDAO {

    @Override
    public List<CartItem> findByCartId(Long cartId) {

        return entityManager.createQuery(
                        """
                        SELECT DISTINCT ci
                        FROM CartItem ci
                        JOIN FETCH ci.product p
                        LEFT JOIN FETCH p.inventory
                        LEFT JOIN FETCH p.category
                        WHERE ci.cart.id = :cartId
                        """,
                        CartItem.class
                )
                .setParameter("cartId", cartId)
                .getResultList();
    }

    @Override
    public Optional<CartItem> findByCartAndProduct(Long cartId,
                                                   Long productId) {

        return entityManager.createQuery(
                        """
                        SELECT ci
                        FROM CartItem ci
                        WHERE ci.cart.id = :cartId
                          AND ci.product.id = :productId
                        """,
                        CartItem.class
                )
                .setParameter("cartId", cartId)
                .setParameter("productId", productId)
                .getResultStream()
                .findFirst();
    }
}