package lk.jiat.techmart.dao.impl;

import jakarta.ejb.Stateless;
import lk.jiat.techmart.dao.PaymentDAO;
import lk.jiat.techmart.entity.Payment;

import java.util.Optional;

@Stateless
public class PaymentDAOImpl extends AbstractDAO<Payment, Long>
        implements PaymentDAO {

    @Override
    public Optional<Payment> findByOrderId(Long orderId) {

        return entityManager.createQuery(
                        """
                        SELECT p
                        FROM Payment p
                        WHERE p.order.id = :orderId
                        """,
                        Payment.class
                )
                .setParameter("orderId", orderId)
                .getResultStream()
                .findFirst();
    }
}