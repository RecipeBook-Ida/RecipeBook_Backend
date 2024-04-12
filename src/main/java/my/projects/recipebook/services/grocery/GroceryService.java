package my.projects.recipebook.services.grocery;

import my.projects.recipebook.models.Grocery;
import my.projects.recipebook.services.CRUDService;

/**
 * Service for the Grocery domain class.
 * Providing basic CRUD functionality through CrudService and any extended functionality.
 */
public interface GroceryService extends CRUDService<Grocery, Integer> {
}
