package lk.jiat.techmart.service;

import jakarta.ejb.Local;
import lk.jiat.techmart.entity.User;

import java.util.List;
import java.util.Optional;

@Local
public interface UserService {

    User register(User user);

    User update(User user);

    void delete(Long id);

    Optional<User> findById(Long id);

    List<User> findAll();

    Optional<User> findByEmail(String email);

    Optional<User> authenticate(String email, String password);

}