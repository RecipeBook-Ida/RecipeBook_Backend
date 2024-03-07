package my.projects.recipebook.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class IngredientNotFoundException extends EntityNotFoundException {
    public IngredientNotFoundException(int id) { super("Ingredient with ID "+id+" does not exist");}
}
