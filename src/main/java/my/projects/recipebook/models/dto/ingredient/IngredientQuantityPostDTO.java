package my.projects.recipebook.models.dto.ingredient;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IngredientQuantityPostDTO {
    private Integer quantity;
    private String unit;
    private Integer ingredientId;
}
