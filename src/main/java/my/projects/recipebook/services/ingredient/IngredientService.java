package my.projects.recipebook.services.ingredient;

import my.projects.recipebook.models.Ingredient;
import my.projects.recipebook.services.CRUDService;

/**
 * Service for the Ingredient domain class.
 * Providing basic CRUD functionality through CrudService and any extended functionality.
 */
public interface IngredientService extends CRUDService<Ingredient, Integer> {
}
