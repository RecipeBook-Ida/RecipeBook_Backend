package my.projects.recipebook.exceptions;

import jakarta.persistence.EntityNotFoundException;

/**
 * Custom UserNotFoundException extends EntityNotFoundException
 * Sets custom message
 */
public class AppUserNotFoundException extends EntityNotFoundException {
    public AppUserNotFoundException(int id) { super("User with ID "+id+" does not exist");}
}
