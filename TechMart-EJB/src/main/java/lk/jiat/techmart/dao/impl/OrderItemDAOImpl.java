package lk.jiat.techmart.dao.impl;

import jakarta.ejb.Stateless;
import lk.jiat.techmart.dao.OrderItemDAO;
import lk.jiat.techmart.entity.OrderItem;

import java.util.List;

@Stateless
public class OrderItemDAOImpl extends AbstractDAO<OrderItem, Long>
        implements OrderItemDAO {

    @Override
    public List<OrderItem> findByOrderId(Long orderId) {

        return entityManager.createQuery(
                        """
                        SELECT oi
                        FROM OrderItem oi
                        JOIN FETCH oi.product
                        WHERE oi.order.id = :orderId
                        """,
                        OrderItem.class
                )
                .setParameter("orderId", orderId)
                .getResultList();
    }
}