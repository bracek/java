package com.att.uchannels.model.dao;

import org.hibernate.criterion.DetachedCriteria;

import java.io.Serializable;
import java.util.List;

/**
 * Created by miroslav.katrak on 4.9.2014.
 */
public interface GenericDAO<T> {

    /**
     * Saves given persistant object of a given type.
     * @param t object to be saved.
     * @return true if saved successfully, otherwise false.
     */
    boolean create(T t);

    /**
     * Saves (creates if id is not given) given persistant object of a given type .
     * @param t object to be saved.
     * @return true if saved successfully, otherwise false.
     */
    boolean createOrUpdate(T t);

    /**
     * Loads a persistant object of a given type according its id.
     * @param id id of the object being loaded.
     * @return loaded object or "empty" (not null) if not found.
     */
    T read(Serializable id);

    /**
     * Finds a persistant object of a given type according its id.
     * @param id id of the object being loaded.
     * @return loaded object or null if not found.
     */
    T find(Serializable id);

    /**
     * Loads all object of the given class.
     * @return all objects of the given type or null if not found.
     */
    List<T> readAll();

    /**
     * Updates given persistant object of a given type.
     * @param t object to be updated.
     * @return true if saved successfully, otherwise false.
     */
    boolean update(T t);

    /**
     * Deletes given persistant object of a given type.
     * @param t object to be deleted.
     * @return true if saved successfully, otherwise false.
     */
    boolean delete(T t);

    /**
     * Prepares criteria from attributes which are set and perform select.
     * @param t object which is used as criteria.
     * @return loaded object or null if not found.
     */
    List<T> readByCriteria(T t);

    /**
     * Use detached criteria for preparing WHERE condition and perform select.
     * @param criteria object which is used as criteria.
     * @return loaded object or null if not found.
     */
    List<T> readByCriteria(DetachedCriteria criteria);

    /**
     * Use detached criteria for preparing WHERE condition and perform select.
     * @param criteria
     * @param <T>
     * @return
     */
    T readUniqueByCriteria(DetachedCriteria criteria);
}

