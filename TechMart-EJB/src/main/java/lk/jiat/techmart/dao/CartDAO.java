package lk.jiat.techmart.dao;

import lk.jiat.techmart.entity.Cart;

import java.util.Optional;

public interface CartDAO extends GenericDAO<Cart, Long> {

    Optional<Cart> findByUserId(Long userId);

}