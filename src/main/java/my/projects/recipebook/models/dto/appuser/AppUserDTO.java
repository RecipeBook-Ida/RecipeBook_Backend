package my.projects.recipebook.models.dto.appuser;

import lombok.Getter;
import lombok.Setter;
import my.projects.recipebook.models.dto.ingredient.IngredientQuantityDTO;
import my.projects.recipebook.models.dto.recipe.RecipeDTO;
import my.projects.recipebook.models.dto.recipe.RecipeListDTO;

import java.util.List;
import java.util.Set;

/**
 * Domain class (entity) to carry all information from User class.
 * Is used in Mapper class where it carries between layers.
 * Includes autogenerated getters and setters from lombok.
 */
@Getter
@Setter
public class AppUserDTO {
    private int id;
    private String image;
    private String username;
    private String firstname;
    private String lastname;
    private Set<IngredientQuantityDTO> groceryList;
    private Set<RecipeListDTO> recipes;
    private List<RecipeListDTO> favorites;
}
