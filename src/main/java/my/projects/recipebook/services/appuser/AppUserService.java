package my.projects.recipebook.services.appuser;

import my.projects.recipebook.models.AppUser;
import my.projects.recipebook.services.CRUDService;

/**
 * Service for the User domain class.
 * Providing basic CRUD functionality through CrudService and any extended functionality.
 */
public interface AppUserService extends CRUDService<AppUser, Integer> {
}
