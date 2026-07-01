package lk.jiat.techmart.service.impl;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import lk.jiat.techmart.dao.CategoryDAO;
import lk.jiat.techmart.entity.Category;
import lk.jiat.techmart.service.CategoryService;

import java.util.List;
import java.util.Optional;

@Stateless
public class CategoryServiceImpl implements CategoryService {

    @Inject
    private CategoryDAO categoryDAO;

    @Override
    public Category save(Category category) {

        Optional<Category> existing = categoryDAO.findByName(category.getName());

        if (existing.isPresent()) {
            throw new RuntimeException("Category already exists.");
        }

        return categoryDAO.save(category);
    }

    @Override
    public Category update(Category category) {
        return categoryDAO.update(category);
    }

    @Override
    public void delete(Long id) {

        Optional<Category> category = categoryDAO.findById(id);

        category.ifPresent(categoryDAO::delete);
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryDAO.findById(id);
    }

    @Override
    public List<Category> findAll() {
        return categoryDAO.findAll();
    }

    @Override
    public Optional<Category> findByName(String name) {
        return categoryDAO.findByName(name);
    }

    @Override
    public List<Category> findActiveCategories() {
        return categoryDAO.findActiveCategories();
    }
}