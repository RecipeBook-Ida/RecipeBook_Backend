package my.projects.recipebook.models.dto.appuser;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Domain class (entity) to carry all information from GroceryList in AppUser class for put call.
 * Is used in Mapper class where it carries between layers.
 * Includes autogenerated getters and setters from lombok.
 */
@Getter
@Setter
public class GroceryListPutDTO {
    private List<Integer> ingredientQuantityIds;
}



