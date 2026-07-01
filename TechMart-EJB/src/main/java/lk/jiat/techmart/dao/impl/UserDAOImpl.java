package lk.jiat.techmart.dao.impl;

import jakarta.ejb.Stateless;
import lk.jiat.techmart.dao.UserDAO;
import lk.jiat.techmart.entity.User;

import java.util.Optional;

@Stateless
public class UserDAOImpl extends AbstractDAO<User, Long> implements UserDAO {

    @Override
    public Optional<User> findByEmail(String email) {

        return entityManager.createQuery(
                        "SELECT u FROM lk.jiat.techmart.entity.User u WHERE u.email=:email",
                        User.class)
                .setParameter("email", email)
                .getResultStream()
                .findFirst();
    }

    @Override
    public Optional<User> authenticate(String email, String password) {

        return entityManager.createQuery(
                        "SELECT u FROM User u WHERE u.email=:email AND u.password=:password",
                        User.class)
                .setParameter("email", email)
                .setParameter("password", password)
                .getResultStream()
                .findFirst();
    }
}