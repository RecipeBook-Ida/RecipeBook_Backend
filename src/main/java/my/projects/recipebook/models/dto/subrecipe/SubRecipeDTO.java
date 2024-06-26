package my.projects.recipebook.models.dto.subrecipe;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import my.projects.recipebook.models.IngredientQuantity;
import my.projects.recipebook.models.dto.ingredient.IngredientQuantityDTO;

import java.util.Collection;
import java.util.List;

/**
 * Domain class (entity) to carry all information from sub Recipe class.
 * Is used in Mapper class where it carries between layers.
 * Includes autogenerated getters and setters from lombok.
 */
@Getter
@Setter
public class SubRecipeDTO {
    private int id;
    private String title;
    private String instructions;
    private Collection<IngredientQuantityDTO> ingredients;
}
