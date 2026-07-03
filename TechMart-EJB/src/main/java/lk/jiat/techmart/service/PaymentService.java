package lk.jiat.techmart.service;

import jakarta.ejb.Local;
import lk.jiat.techmart.entity.Payment;

import java.util.Optional;

@Local
public interface PaymentService {

    Optional<Payment> findByOrderId(Long orderId);

    Payment update(Payment payment);

}