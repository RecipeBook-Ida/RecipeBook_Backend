package my.projects.recipebook.mappers;

import my.projects.recipebook.models.Ingredient;
import my.projects.recipebook.models.IngredientQuantity;
import my.projects.recipebook.models.dto.ingredient.IngredientDTO;
import my.projects.recipebook.models.dto.ingredient.IngredientQuantityDTO;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface IngredientQuantityMapper {
    /**
     * Mapper method to convert a collection of IngredientQuantity objects to a collection
     * of IngredientQuantityDTO objects using MapStruct
     * @param ingredientQuantities The collection of IngredientQuantity objects to be mapped
     * @return The resulting collection of IngredientQuantityDTO objects mapped to
     */
    Collection<IngredientQuantityDTO> ingredientQuantityToIngredientQuantityDTO(Collection<IngredientQuantity> ingredientQuantities);

    /**
     * Mapper method to convert an IngredientQuantity object to an IngredientQuantityDTO object
     * using MapStruct
     * @param ingredientQuantity The IngredientQuantity object to be mapped
     * @return The resulting IngredientQuantityDTO object mapped to
     */
    //@Mapping(target = "movies",source = "movies")
    IngredientQuantityDTO ingredientQuantityToIngredientQuantityDTO(IngredientQuantity ingredientQuantity);

    /**
     * Mapper method to convert an IngredientQuantityDTO object to an IngredientQuantity object
     * using MapStruct
     * @param ingredientQuantityDTO The IngredientQuantityDTO object to be mapped
     * @return The resulting IngredientQuantity mapped to
     */
    //@Mapping(target = "movies",source = "movies")
    IngredientQuantity ingredientQuantityDTOToIngredientQuantity(IngredientQuantityDTO ingredientQuantityDTO);
}
