package lk.jiat.techmart.dao;

import lk.jiat.techmart.entity.User;

import java.util.Optional;

public interface UserDAO extends GenericDAO<User, Long> {

    Optional<User> findByEmail(String email);

}