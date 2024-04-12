package my.projects.recipebook.models.dto.grocery;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
public class GroceryPostDTO {
    private Integer ingredientId;
    private Collection<Integer> ingredientQuantityIds;
}
