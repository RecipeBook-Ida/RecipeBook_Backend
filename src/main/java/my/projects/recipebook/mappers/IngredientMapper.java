package my.projects.recipebook.mappers;

import my.projects.recipebook.models.Ingredient;
import my.projects.recipebook.models.dto.ingredient.IngredientDTO;
import my.projects.recipebook.models.dto.ingredient.IngredientPostDTO;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface IngredientMapper {

    /**
     * Mapper method to convert a collection of Ingredient objects to a collection
     * of IngredientDTO objects using MapStruct
     * @param ingredients The collection of Ingredient objects to be mapped
     * @return The resulting collection of IngredientDTO objects mapped to
     */
    Collection<IngredientDTO> ingredientToIngredientDTO(Collection<Ingredient> ingredients);

    /**
     * Mapper method to convert an Ingredient object to an IngredientDTO object
     * using MapStruct
     * @param ingredient The Ingredient object to be mapped
     * @return The resulting IngredientDTO object mapped to
     */
    //@Mapping(target = "movies",source = "movies")
    IngredientDTO ingredientToIngredientDTO(Ingredient ingredient);

    /**
     * Mapper method to convert an IngredientDTO object to an Ingredient object
     * using MapStruct
     * @param ingredientDTO The IngredientDTO object to be mapped
     * @return The resulting Ingredient mapped to
     */
    //@Mapping(target = "movies",source = "movies")
    Ingredient ingredientDTOToIngredient(IngredientDTO ingredientDTO);

    /**
     * Mapper method to convert an IngredientPostDTO object to an Ingredient object
     * using MapStruct
     * @param ingredientPostDTO The IngredientPostDTO object to be mapped
     * @return The resulting Ingredient mapped to
     */
    Ingredient ingredientPostDTOToIngredient(IngredientPostDTO ingredientPostDTO);
}
