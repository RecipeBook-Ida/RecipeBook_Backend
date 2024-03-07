package my.projects.recipebook.repositories;

import my.projects.recipebook.models.SubRecipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository (DAO) for the SubRecipe domain class.
 * Uses @Query for business logic that is difficult to achieve functionality.
 * Is using hibernate for generic methods.
 */
@Repository
public interface SubRecipeRepository extends JpaRepository<SubRecipe, Integer> {

}


