package lk.jiat.techmart.service.impl;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import lk.jiat.techmart.dao.OrderItemDAO;
import lk.jiat.techmart.entity.OrderItem;
import lk.jiat.techmart.service.OrderItemService;

import java.util.List;

@Stateless
public class OrderItemServiceImpl implements OrderItemService {

    @Inject
    private OrderItemDAO orderItemDAO;

    @Override
    public List<OrderItem> findByOrderId(Long orderId) {
        return orderItemDAO.findByOrderId(orderId);
    }
}