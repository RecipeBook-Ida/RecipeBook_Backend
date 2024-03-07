package my.projects.recipebook.models.dto.ingredient;

import lombok.Getter;
import lombok.Setter;
import my.projects.recipebook.models.Ingredient;

@Getter
@Setter
public class IngredientQuantityDTO {
    private int id;
    private Integer quantity;
    private String unit;
    private IngredientDTO ingredient;
}
