package lk.jiat.techmart.service;

import lk.jiat.techmart.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User register(User user);

    User update(User user);

    void delete(Long id);

    Optional<User> findById(Long id);

    List<User> findAll();

    Optional<User> findByEmail(String email);

}