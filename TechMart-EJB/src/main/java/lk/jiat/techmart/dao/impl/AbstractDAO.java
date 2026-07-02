package lk.jiat.techmart.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lk.jiat.techmart.dao.GenericDAO;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDAO<T, ID> implements GenericDAO<T, ID> {

    @PersistenceContext(unitName = "TechMartPU")
    protected EntityManager entityManager;

    private final Class<T> entityClass;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.entityClass = (Class<T>) ((ParameterizedType)
                getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    @Override
    public T save(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public T update(T entity) {
        return entityManager.merge(entity);
    }

    @Override
    public void delete(T entity) {
        entityManager.remove(entityManager.contains(entity)
                ? entity
                : entityManager.merge(entity));
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(entityManager.find(entityClass, id));
    }

    @Override
    public List<T> findAll() {

        return entityManager
                .createQuery(
                        "SELECT e FROM " + entityClass.getSimpleName() + " e",
                        entityClass
                )
                .getResultList();

    }
}