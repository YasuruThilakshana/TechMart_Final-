package lk.jiat.techmart.dao;

import lk.jiat.techmart.entity.CartItem;

import java.util.List;
import java.util.Optional;

public interface CartItemDAO extends GenericDAO<CartItem, Long> {

    List<CartItem> findByCartId(Long cartId);

    Optional<CartItem> findByCartAndProduct(Long cartId, Long productId);

}