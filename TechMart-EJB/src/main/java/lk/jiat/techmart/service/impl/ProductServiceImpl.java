package lk.jiat.techmart.service.impl;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import lk.jiat.techmart.dao.ProductDAO;
import lk.jiat.techmart.entity.Product;
import lk.jiat.techmart.service.ProductService;

import java.util.List;
import java.util.Optional;

@Stateless
public class ProductServiceImpl implements ProductService {

    @Inject
    private ProductDAO productDAO;

    @Override
    public Product save(Product product) {
        return productDAO.save(product);
    }

    @Override
    public Product update(Product product) {
        return productDAO.update(product);
    }

    @Override
    public void delete(Long id) {

        Optional<Product> product = productDAO.findById(id);

        product.ifPresent(productDAO::delete);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productDAO.findById(id);
    }

    @Override
    public List<Product> findAll() {
        return productDAO.findAll();
    }

    @Override
    public List<Product> findActiveProducts() {
        return productDAO.findActiveProducts();
    }

    @Override
    public List<Product> findByCategory(Long categoryId) {
        return productDAO.findByCategory(categoryId);
    }

    @Override
    public List<Product> searchProducts(String keyword) {
        return productDAO.searchProducts(keyword);
    }
}