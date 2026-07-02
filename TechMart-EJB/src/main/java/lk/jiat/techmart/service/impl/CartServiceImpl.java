package lk.jiat.techmart.service.impl;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import lk.jiat.techmart.dao.CartDAO;
import lk.jiat.techmart.dao.CartItemDAO;
import lk.jiat.techmart.dao.ProductDAO;
import lk.jiat.techmart.dao.UserDAO;
import lk.jiat.techmart.entity.Cart;
import lk.jiat.techmart.entity.CartItem;
import lk.jiat.techmart.entity.Product;
import lk.jiat.techmart.entity.User;
import lk.jiat.techmart.service.CartService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.math.BigDecimal;

@Stateless
public class CartServiceImpl implements CartService {

    @Inject
    private CartDAO cartDAO;

    @Inject
    private CartItemDAO cartItemDAO;

    @Inject
    private ProductDAO productDAO;

    @Inject
    private UserDAO userDAO;

    @Override
    public Cart save(Cart cart) {
        return cartDAO.save(cart);
    }

    @Override
    public Cart update(Cart cart) {
        return cartDAO.update(cart);
    }

    @Override
    public void delete(Long id) {

        cartDAO.findById(id)
                .ifPresent(cartDAO::delete);

    }

    @Override
    public Optional<Cart> findById(Long id) {
        return cartDAO.findById(id);
    }

    @Override
    public Optional<Cart> findByUserId(Long userId) {
        return cartDAO.findByUserId(userId);
    }

    @Override
    public List<Cart> findAll() {
        return cartDAO.findAll();
    }

    @Override
    public List<CartItem> getCartItems(Long userId) {

        Optional<Cart> cart = cartDAO.findByUserId(userId);

        if (cart.isPresent()) {
            return cartItemDAO.findByCartId(cart.get().getId());
        }

        return List.of();
    }

    @Override
    public void addToCart(Long userId,
                          Long productId,
                          Integer quantity) {

        User user = userDAO.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Product product = productDAO.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Cart cart = cartDAO.findByUserId(userId)
                .orElseGet(() -> {

                    Cart newCart = new Cart();

                    newCart.setUser(user);
                    newCart.setCreatedAt(LocalDateTime.now());
                    newCart.setUpdatedAt(LocalDateTime.now());

                    return cartDAO.save(newCart);

                });

        Optional<CartItem> existingItem =
                cartItemDAO.findByCartAndProduct(
                        cart.getId(),
                        productId
                );

        if (existingItem.isPresent()) {

            CartItem item = existingItem.get();

            item.setQuantity(item.getQuantity() + quantity);

            item.setSubtotal(
                    item.getUnitPrice()
                            .multiply(
                                    BigDecimal.valueOf(item.getQuantity())
                            )
            );

            cartItemDAO.update(item);

        } else {

            CartItem item = new CartItem();

            item.setCart(cart);
            item.setProduct(product);

            item.setQuantity(quantity);

            item.setUnitPrice(product.getPrice());

            item.setSubtotal(
                    product.getPrice()
                            .multiply(BigDecimal.valueOf(quantity))
            );

            cartItemDAO.save(item);
        }

        cart.setUpdatedAt(LocalDateTime.now());

        cartDAO.update(cart);
    }

    @Override
    public void removeCartItem(Long cartItemId) {

        cartItemDAO.findById(cartItemId)
                .ifPresent(cartItemDAO::delete);

    }

    @Override
    public void clearCart(Long userId) {

        Optional<Cart> cart = cartDAO.findByUserId(userId);

        if (cart.isPresent()) {

            List<CartItem> items =
                    cartItemDAO.findByCartId(cart.get().getId());

            items.forEach(cartItemDAO::delete);
        }
    }


    @Override
    public void updateQuantity(Long cartItemId, Integer quantity) {

        CartItem item = cartItemDAO.findById(cartItemId)
                .orElseThrow(() ->
                        new RuntimeException("Cart item not found"));

        if (quantity <= 0) {

            cartItemDAO.delete(item);
            return;

        }

        item.setQuantity(quantity);

        item.setSubtotal(
                item.getUnitPrice()
                        .multiply(BigDecimal.valueOf(quantity))
        );

        cartItemDAO.update(item);
    }
}