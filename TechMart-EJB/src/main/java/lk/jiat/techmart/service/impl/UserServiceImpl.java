package lk.jiat.techmart.service.impl;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import lk.jiat.techmart.dao.UserDAO;
import lk.jiat.techmart.entity.User;
import lk.jiat.techmart.service.UserService;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;
import lk.jiat.techmart.performance.PerformanceMonitor;


@Stateless
@PerformanceMonitor
public class UserServiceImpl implements UserService {



    @Inject
    private UserDAO userDAO;

    @Override
    public User register(User user) {

        Optional<User> existingUser = userDAO.findByEmail(user.getEmail());

        if (existingUser.isPresent()) {
            throw new RuntimeException("Email already exists.");
        }

        // Hash Password
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

        user.setPassword(hashedPassword);
        user.setCreatedAt(LocalDateTime.now());

        return userDAO.save(user);
    }

    @Override
    public User update(User user) {
        return userDAO.update(user);
    }

    @Override
    public void delete(Long id) {

        Optional<User> user = userDAO.findById(id);

        user.ifPresent(userDAO::delete);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userDAO.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    @Override
    public Optional<User> authenticate(String email, String password) {

        Optional<User> userOptional = userDAO.findByEmail(email);

        if (userOptional.isPresent()) {

            User user = userOptional.get();

            if (BCrypt.checkpw(password, user.getPassword())) {
                return Optional.of(user);
            }
        }



        return Optional.empty();
    }
}