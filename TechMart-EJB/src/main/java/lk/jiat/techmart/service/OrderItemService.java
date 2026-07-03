package lk.jiat.techmart.service;

import jakarta.ejb.Local;
import lk.jiat.techmart.entity.OrderItem;

import java.util.List;

@Local
public interface OrderItemService {

    List<OrderItem> findByOrderId(Long orderId);

}