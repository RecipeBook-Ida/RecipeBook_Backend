package my.projects.recipebook.models.dto.ingredient;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IngredientQuantityListDTO {
    private Integer quantity;
    private String unit;
}
