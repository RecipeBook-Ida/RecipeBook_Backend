package my.projects.recipebook.services;

import java.util.Collection;

/**
 * Generic service to act as a parent to all domain-specific services.
 * It encapsulates basic CRUD functionality to be reused.
 * It follows the same structure as JPA Repositories to ease integration.
 * @param <T> Type of domain class of the service
 * @param <ID> Primary key type for entity
 */
public interface CRUDService <T, ID> {
    /**
     * Standard method for finding object by the id by matching the param id to the object id.
     * Is using hibernate to access database.
     * @param id integer id representative of an object.
     * @return Object found by matching param id to object id.
     */
    T findById(ID id);

    /**
     * Standard method for finding all objects in the database.
     * Is using hibernate to access database
     * @return Collection of objects.
     */
    Collection<T> findAll();

    /**
     * Standard method for adding an object to the database.
     * Is using hibernate to access database
     * @param entity Is an object
     * @return Object that gets added to the database
     */
    T add(T entity);

    /**
     * Standard method for updating an object in the database using an object as param.
     * Is using hibernate to access database
     * @param entity object to update the corresponding object in the database
     * @return Object that gets updated in the database
     */
    T update(T entity);

    /**
     * Standard method for deleting an object in the database using an id to reference to the object.
     * Is using hibernate to access database
     * @param id integer id to represent an object
     */
    void deleteById(ID id);

    /**
     * Standard method for checking if an object exists in the database using an id to reference to the object.
     * Is using hibernate to access database
     * @param id integer id to represent an object
     * @return boolean representative if an object with the id exists = true, or if an object does not exist = false
     */
    boolean exists(ID id);
}
