package lk.jiat.techmart.dao;

import lk.jiat.techmart.entity.Payment;

import java.util.Optional;

public interface PaymentDAO extends GenericDAO<Payment, Long> {

    Optional<Payment> findByOrderId(Long orderId);

}