package my.projects.recipebook.exceptions;

import jakarta.persistence.EntityNotFoundException;

/**
 * Custom RecipeNotFoundException extends EntityNotFoundException
 * Sets custom message
 */
public class RecipeNotFoundException extends EntityNotFoundException {
    public RecipeNotFoundException(int id) { super("Recipe with ID "+id+" does not exist");}
}
