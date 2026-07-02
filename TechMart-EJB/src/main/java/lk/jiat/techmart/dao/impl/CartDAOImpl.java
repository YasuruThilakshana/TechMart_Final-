package lk.jiat.techmart.dao.impl;

import jakarta.ejb.Stateless;
import lk.jiat.techmart.dao.CartDAO;
import lk.jiat.techmart.entity.Cart;

import java.util.Optional;

@Stateless
public class CartDAOImpl extends AbstractDAO<Cart, Long> implements CartDAO {

    @Override
    public Optional<Cart> findByUserId(Long userId) {

        return entityManager.createQuery(
                        """
                        SELECT c
                        FROM Cart c
                        WHERE c.user.id = :userId
                        """,
                        Cart.class
                )
                .setParameter("userId", userId)
                .getResultStream()
                .findFirst();
    }
}