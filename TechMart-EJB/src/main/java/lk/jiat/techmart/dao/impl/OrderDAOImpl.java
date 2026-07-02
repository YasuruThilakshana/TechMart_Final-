package lk.jiat.techmart.dao.impl;

import jakarta.ejb.Stateless;
import lk.jiat.techmart.dao.OrderDAO;
import lk.jiat.techmart.entity.Order;

import java.util.List;

@Stateless
public class OrderDAOImpl extends AbstractDAO<Order, Long>
        implements OrderDAO {

    @Override
    public List<Order> findByUserId(Long userId) {

        return entityManager.createQuery(
                        """
                        SELECT o
                        FROM Order o
                        WHERE o.user.id = :userId
                        ORDER BY o.orderDate DESC
                        """,
                        Order.class
                )
                .setParameter("userId", userId)
                .getResultList();
    }
}