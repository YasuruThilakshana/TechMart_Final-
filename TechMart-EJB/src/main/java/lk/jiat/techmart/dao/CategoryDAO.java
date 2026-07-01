package lk.jiat.techmart.dao;

import lk.jiat.techmart.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryDAO extends GenericDAO<Category, Long> {

    Optional<Category> findByName(String name);

    List<Category> findActiveCategories();

}