package lk.jiat.techmart.service;

import jakarta.ejb.Local;
import lk.jiat.techmart.entity.Product;

import java.util.List;
import java.util.Optional;

@Local
public interface ProductService {

    Product save(Product product);

    Product update(Product product);

    void delete(Long id);

    Optional<Product> findById(Long id);

    List<Product> findAll();

    List<Product> findActiveProducts();

    List<Product> findByCategory(Long categoryId);

    List<Product> searchProducts(String keyword);

}