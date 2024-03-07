package my.projects.recipebook.services.recipe;

import my.projects.recipebook.models.Recipe;
import my.projects.recipebook.services.CRUDService;

/**
 * Service for the Recipe domain class.
 * Providing basic CRUD functionality through CrudService and any extended functionality.
 */
public interface RecipeService extends CRUDService<Recipe, Integer> {
}
