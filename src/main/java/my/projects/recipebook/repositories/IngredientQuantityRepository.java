package my.projects.recipebook.repositories;

import my.projects.recipebook.models.Ingredient;
import my.projects.recipebook.models.IngredientQuantity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository (DAO) for the IngredientQuantity domain class.
 * Uses @Query for business logic that is difficult to achieve functionality.
 * Is using hibernate for generic methods.
 */
@Repository
public interface IngredientQuantityRepository extends JpaRepository<IngredientQuantity, Integer> {
    Optional<IngredientQuantity> findByQuantityAndUnitAndIngredient(int quantity, String unit, Ingredient ingredient);
}
