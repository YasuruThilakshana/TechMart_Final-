package lk.jiat.techmart.dao;

import lk.jiat.techmart.entity.Product;

import java.util.List;

public interface ProductDAO extends GenericDAO<Product, Long> {

    List<Product> findActiveProducts();

    List<Product> findByCategory(Long categoryId);

    List<Product> searchProducts(String keyword);

}