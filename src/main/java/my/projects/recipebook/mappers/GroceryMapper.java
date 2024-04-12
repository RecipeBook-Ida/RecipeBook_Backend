package my.projects.recipebook.mappers;


import my.projects.recipebook.models.Grocery;
import my.projects.recipebook.models.dto.grocery.GroceryDTO;
import my.projects.recipebook.models.dto.grocery.GroceryPostDTO;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface GroceryMapper {
    //GroceryListDTO groceryToGroceryListDTO(Grocery grocery);
    Collection<GroceryDTO> groceryToGroceryDTO(Collection<Grocery> groceries);
    GroceryDTO groceryToGroceryDTO(Grocery grocery);
    Grocery groceryDTOToGrocery(GroceryDTO groceryDTO);
    Grocery groceryPostDTOToGrocery(GroceryPostDTO groceryPostDTO);

}
