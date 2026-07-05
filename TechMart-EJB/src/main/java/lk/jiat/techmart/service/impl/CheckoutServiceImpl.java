package lk.jiat.techmart.service.impl;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import lk.jiat.techmart.dao.CartDAO;
import lk.jiat.techmart.dao.CartItemDAO;
import lk.jiat.techmart.dao.OrderDAO;
import lk.jiat.techmart.dao.OrderItemDAO;
import lk.jiat.techmart.dao.PaymentDAO;
import lk.jiat.techmart.dao.UserDAO;

import lk.jiat.techmart.dto.OrderMessageDTO;

import lk.jiat.techmart.entity.Cart;
import lk.jiat.techmart.entity.CartItem;
import lk.jiat.techmart.entity.Order;
import lk.jiat.techmart.entity.OrderItem;
import lk.jiat.techmart.entity.Payment;
import lk.jiat.techmart.entity.User;

import lk.jiat.techmart.performance.PerformanceMonitor;

import lk.jiat.techmart.service.CartService;
import lk.jiat.techmart.service.CheckoutService;
import lk.jiat.techmart.service.JMSProducerService;
import lk.jiat.techmart.service.WarehouseInventoryService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Stateless
@PerformanceMonitor
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
    private UserDAO userDAO;

    @Inject
    private CartService cartService;

    @Inject
    private JMSProducerService jmsProducerService;

    @Inject
    private WarehouseInventoryService warehouseInventoryService;


    @Override
    public Order placeOrder(Long userId) {



        User user = userDAO.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found")
                );




        Cart cart = cartDAO.findByUserId(userId)
                .orElseThrow(() ->
                        new RuntimeException("Shopping cart not found")
                );




        List<CartItem> cartItems =
                cartItemDAO.findByCartId(cart.getId());

        if (cartItems.isEmpty()) {

            throw new RuntimeException(
                    "Shopping cart is empty"
            );
        }




        for (CartItem cartItem : cartItems) {

            int availableStock =
                    warehouseInventoryService
                            .getTotalAvailableStock(
                                    cartItem.getProduct().getId()
                            );

            if (availableStock < cartItem.getQuantity()) {

                throw new RuntimeException(
                        "Insufficient warehouse stock for product : "
                                + cartItem.getProduct().getName()
                );
            }
        }




        Order order = new Order();

        order.setUser(user);

        order.setOrderDate(
                LocalDateTime.now()
        );

        order.setStatus("PENDING");




        BigDecimal totalAmount =
                BigDecimal.ZERO;

        for (CartItem item : cartItems) {

            totalAmount =
                    totalAmount.add(
                            item.getSubtotal()
                    );
        }

        order.setTotalAmount(totalAmount);




        order = orderDAO.save(order);




        for (CartItem cartItem : cartItems) {

            OrderItem orderItem =
                    new OrderItem();

            orderItem.setOrder(order);

            orderItem.setProduct(
                    cartItem.getProduct()
            );

            orderItem.setQuantity(
                    cartItem.getQuantity()
            );

            orderItem.setUnitPrice(
                    cartItem.getUnitPrice()
            );

            orderItem.setSubtotal(
                    cartItem.getSubtotal()
            );




            orderItemDAO.save(orderItem);




            warehouseInventoryService.deductStock(
                    cartItem.getProduct().getId(),
                    cartItem.getQuantity()
            );
        }



        Payment payment =
                new Payment();

        payment.setOrder(order);

        payment.setAmount(
                order.getTotalAmount()
        );

        payment.setPaymentDate(
                LocalDateTime.now()
        );

        payment.setStatus("PENDING");


        paymentDAO.save(payment);




        cartService.clearCart(userId);




        OrderMessageDTO message =
                new OrderMessageDTO();

        message.setOrderId(
                order.getId()
        );

        message.setUserId(
                user.getId()
        );

        message.setCustomerName(
                user.getFirstName()
                        + " "
                        + user.getLastName()
        );

        message.setTotalAmount(
                order.getTotalAmount()
        );

        message.setOrderDate(
                order.getOrderDate()
        );




        jmsProducerService.sendOrderMessage(
                message
        );




        return order;
    }
}