package lk.jiat.techmart.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class PersistenceManager {

    @PersistenceContext(unitName = "TechMartPU")
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

}