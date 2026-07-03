package lk.jiat.techmart.service;

import jakarta.ejb.Local;
import lk.jiat.techmart.entity.Order;

import java.util.List;
import java.util.Optional;

@Local
public interface OrderService {

    List<Order> findByUserId(Long userId);

    Optional<Order> findById(Long id);

    List<Order> findAll();

    Order update(Order order);

}