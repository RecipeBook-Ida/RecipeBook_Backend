package my.projects.recipebook.repositories;

import my.projects.recipebook.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository (DAO) for the Recipe domain class.
 * Uses @Query for business logic that is difficult to achieve functionality.
 * Is using hibernate for generic methods.
 */
@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

}

