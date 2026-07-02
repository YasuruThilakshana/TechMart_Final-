package lk.jiat.techmart.dao;

import lk.jiat.techmart.entity.OrderItem;

import java.util.List;

public interface OrderItemDAO extends GenericDAO<OrderItem, Long> {

    List<OrderItem> findByOrderId(Long orderId);

}