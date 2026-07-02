package lk.jiat.techmart.service;

import jakarta.ejb.Local;
import lk.jiat.techmart.entity.Cart;
import lk.jiat.techmart.entity.CartItem;

import java.util.List;
import java.util.Optional;

@Local
public interface CartService {

    Cart save(Cart cart);

    Cart update(Cart cart);

    void delete(Long id);

    Optional<Cart> findById(Long id);

    Optional<Cart> findByUserId(Long userId);

    List<Cart> findAll();

    List<CartItem> getCartItems(Long userId);

    void addToCart(Long userId, Long productId, Integer quantity);

    void removeCartItem(Long cartItemId);

    void clearCart(Long userId);

}