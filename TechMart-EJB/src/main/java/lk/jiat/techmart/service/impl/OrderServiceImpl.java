package lk.jiat.techmart.service.impl;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import lk.jiat.techmart.dao.OrderDAO;
import lk.jiat.techmart.entity.Order;
import lk.jiat.techmart.service.OrderService;

import java.util.List;
import java.util.Optional;

@Stateless
public class OrderServiceImpl implements OrderService {

    @Inject
    private OrderDAO orderDAO;

    @Override
    public List<Order> findByUserId(Long userId) {
        return orderDAO.findByUserId(userId);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderDAO.findById(id);
    }

    @Override
    public List<Order> findAll() {
        return orderDAO.findAll();
    }
}