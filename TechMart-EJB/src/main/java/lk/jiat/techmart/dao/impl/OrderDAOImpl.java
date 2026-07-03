package lk.jiat.techmart.dao.impl;

import jakarta.ejb.Stateless;
import lk.jiat.techmart.dao.OrderDAO;
import lk.jiat.techmart.entity.Order;

import java.util.List;
import java.util.Optional;
import java.util.Optional;
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


    @Override
    public List<Order> findAll() {

        return entityManager.createQuery(
                """
                SELECT DISTINCT o
                FROM Order o
                JOIN FETCH o.user
                ORDER BY o.orderDate DESC
                """,
                Order.class
        ).getResultList();
    }

    @Override
    public Optional<Order> findById(Long id) {

        return entityManager.createQuery(
                        """
                        SELECT o
                        FROM Order o
                        JOIN FETCH o.user
                        WHERE o.id = :id
                        """,
                        Order.class
                )
                .setParameter("id", id)
                .getResultStream()
                .findFirst();

    }
}