package lk.jiat.techmart.service;

import jakarta.ejb.Local;
import lk.jiat.techmart.entity.Category;

import java.util.List;
import java.util.Optional;

@Local
public interface CategoryService {

    Category save(Category category);

    Category update(Category category);

    void delete(Long id);

    Optional<Category> findById(Long id);

    List<Category> findAll();

    Optional<Category> findByName(String name);

    List<Category> findActiveCategories();

}