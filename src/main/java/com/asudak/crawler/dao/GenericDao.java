package com.asudak.crawler.dao;

/**
 * crawler
 * Created by asudak on 9/28/15.
 */

import com.asudak.crawler.config.Config;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

public abstract class GenericDao<T> {
    @PersistenceContext(unitName = Config.PERSISTENCE_UNIT_NAME)
    private EntityManager em;

    private Class<T> entityClass;

    public GenericDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void save(T entity) {
        em.persist(entity);
    }

    public void update(T entity) {
        em.merge(entity);
    }

    public T find(int id) {
        return em.find(entityClass, id);
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return em.createQuery("SELECT e FROM " + entityClass.getName() + " e")
                .getResultList();
    }

    @SuppressWarnings("unchecked")
    public void executeUpdate(String namedQuery, Map<String, Object> parameters) {
        Query query = em.createNamedQuery(namedQuery);
        populateParameters(query, parameters);
        query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    public List<T> findMultipleResults(String namedQuery, Map<String, Object> parameters) {
        Query query = em.createNamedQuery(namedQuery);
        populateParameters(query, parameters);
        return (List<T>) query.getResultList();
    }

    public void delete(T entity) {
        em.remove(em.contains(entity) ? entity : em.merge(entity));
    }

    private void populateParameters(Query query, Map<String, Object> parameters) {
        if (parameters != null && !parameters.isEmpty()) {
            for (Map.Entry<String, Object> parameter : parameters.entrySet())
                query.setParameter(parameter.getKey(), parameter.getValue());
        }
    }
}
