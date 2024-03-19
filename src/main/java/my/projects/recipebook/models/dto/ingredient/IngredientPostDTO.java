package my.projects.recipebook.models.dto.ingredient;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IngredientPostDTO {
    private String name;
    private String image;
    private String category;
}
