package my.projects.recipebook.repositories;

import my.projects.recipebook.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository (DAO) for the User domain class.
 * Uses @Query for business logic that is difficult to achieve functionality.
 * Is using hibernate for generic methods.
 */
@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {

}

