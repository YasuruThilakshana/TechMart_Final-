package lk.jiat.techmart.service.impl;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import lk.jiat.techmart.dao.PaymentDAO;
import lk.jiat.techmart.entity.Payment;
import lk.jiat.techmart.service.PaymentService;

import java.util.Optional;

@Stateless
public class PaymentServiceImpl implements PaymentService {

    @Inject
    private PaymentDAO paymentDAO;

    @Override
    public Optional<Payment> findByOrderId(Long orderId) {
        return paymentDAO.findByOrderId(orderId);
    }

    @Override
    public Payment update(Payment payment) {
        return paymentDAO.update(payment);
    }
}