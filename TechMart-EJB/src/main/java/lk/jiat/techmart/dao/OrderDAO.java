package lk.jiat.techmart.dao;

import lk.jiat.techmart.entity.Order;

import java.util.List;

public interface OrderDAO extends GenericDAO<Order, Long> {

    List<Order> findByUserId(Long userId);



}