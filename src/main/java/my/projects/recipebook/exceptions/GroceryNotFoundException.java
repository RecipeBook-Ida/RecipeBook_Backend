package my.projects.recipebook.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class GroceryNotFoundException extends EntityNotFoundException {
    public GroceryNotFoundException(int id) { super("Grocery with ID "+id+" does not exist");}
}
