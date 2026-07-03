package lk.jiat.techmart.service.impl;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import lk.jiat.techmart.dao.*;
import lk.jiat.techmart.entity.*;
import lk.jiat.techmart.service.CheckoutService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import lk.jiat.techmart.service.CartService;
@Stateless
public class CheckoutServiceImpl implements CheckoutService {

    @Inject
    private CartDAO cartDAO;

    @Inject
    private CartItemDAO cartItemDAO;

    @Inject
    private OrderDAO orderDAO;

    @Inject
    private OrderItemDAO orderItemDAO;

    @Inject
    private PaymentDAO paymentDAO;

    @Inject
    private InventoryDAO inventoryDAO;

    @Inject
    private UserDAO userDAO;

    @Inject
    private CartService cartService;

    @Override
    public Order placeOrder(Long userId) {

        User user = userDAO.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Cart cart = cartDAO.findByUserId(userId)
                .orElseThrow(() ->
                        new RuntimeException("Shopping cart not found"));

        List<CartItem> cartItems =
                cartItemDAO.findByCartId(cart.getId());

        if (cartItems.isEmpty()) {
            throw new RuntimeException("Shopping cart is empty");
        }

        Order order = new Order();

        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("PENDING");

        BigDecimal totalAmount = BigDecimal.ZERO;

        for (CartItem item : cartItems) {

            totalAmount =
                    totalAmount.add(item.getSubtotal());

        }

        order.setTotalAmount(totalAmount);

        order = orderDAO.save(order);

        for (CartItem cartItem : cartItems) {

            OrderItem orderItem = new OrderItem();

            orderItem.setOrder(order);
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setUnitPrice(cartItem.getUnitPrice());
            orderItem.setSubtotal(cartItem.getSubtotal());

            orderItemDAO.save(orderItem);

            Inventory inventory = cartItem.getProduct().getInventory();

            if (inventory == null) {
                throw new RuntimeException(
                        "Inventory record not found for product : "
                                + cartItem.getProduct().getName()
                );
            }

            if (inventory.getQuantity() < cartItem.getQuantity()) {
                throw new RuntimeException(
                        "Insufficient stock for product : "
                                + cartItem.getProduct().getName()
                );
            }

            inventory.setQuantity(
                    inventory.getQuantity() - cartItem.getQuantity()
            );

            inventory.setLastUpdated(LocalDateTime.now());

            inventoryDAO.update(inventory);
        }

        Payment payment = new Payment();

        payment.setOrder(order);
        payment.setAmount(order.getTotalAmount());
        payment.setPaymentDate(LocalDateTime.now());
        payment.setStatus("PENDING");

        paymentDAO.save(payment);

        cartService.clearCart(userId);

        return order;
    }

}