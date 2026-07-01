package lk.jiat.techmart.dao.impl;

import jakarta.ejb.Stateless;
import lk.jiat.techmart.dao.CategoryDAO;
import lk.jiat.techmart.entity.Category;

import java.util.List;
import java.util.Optional;

@Stateless
public class CategoryDAOImpl extends AbstractDAO<Category, Long> implements CategoryDAO {

    @Override
    public Optional<Category> findByName(String name) {

        return entityManager.createQuery(
                        "SELECT c FROM Category c WHERE c.name = :name",
                        Category.class)
                .setParameter("name", name)
                .getResultStream()
                .findFirst();
    }

    @Override
    public List<Category> findActiveCategories() {

        return entityManager.createQuery(
                        "SELECT c FROM Category c WHERE c.active = true ORDER BY c.name",
                        Category.class)
                .getResultList();
    }
}