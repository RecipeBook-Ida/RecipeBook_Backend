package my.projects.recipebook.mappers;

import my.projects.recipebook.models.Recipe;
import my.projects.recipebook.models.dto.recipe.RecipeDTO;
import my.projects.recipebook.models.dto.recipe.RecipeListDTO;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface RecipeMapper {

    /**
     * Mapper method to convert a Recipe object to a RecipeListDTO object
     * using MapStruct
     * @param recipe TheRecipe object to be mapped
     * @return The resulting RecipeListDTO object mapped to
     */
    RecipeListDTO recipeToRecipeListDTO(Recipe recipe);

    /**
     * Mapper method to convert a collection of Recipe objects to a collection
     * of RecipeListDTO objects using MapStruct
     * @param recipes The collection of Recipe objects to be mapped
     * @return The resulting collection of RecipeDTO objects mapped to
     */
    Collection<RecipeDTO> recipeToRecipeDTO(Collection<Recipe> recipes);

    /**
     * Mapper method to convert a Recipe object to a RecipeDTO object
     * using MapStruct
     * @param recipe The Recipe object to be mapped
     * @return The resulting RecipeDTO object mapped to
     */
    //@Mapping(target = "movies",source = "movies")
    RecipeDTO recipeToRecipeDTO(Recipe recipe);

    /**
     * Mapper method to convert a RecipeDTO object to a Recipe object
     * using MapStruct
     * @param recipeDTO The RecipeDTO object to be mapped
     * @return The resulting Recipe mapped to
     */
    //@Mapping(target = "movies",source = "movies")
    Recipe recipeDTOToRecipe(RecipeDTO recipeDTO);
}
