package my.projects.recipebook.mappers;

import my.projects.recipebook.models.SubRecipe;
import my.projects.recipebook.models.dto.subrecipe.SubRecipeDTO;
import my.projects.recipebook.models.dto.subrecipe.SubRecipePostDTO;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface SubRecipeMapper {
    /**
     * Mapper method to convert a SubRecipe object to a SubRecipeDTO object
     * using MapStruct
     * @param subRecipe The SubRecipe object to be mapped
     * @return The resulting SubRecipeDTO object mapped to
     */
    //@Mapping(target = "movies",source = "movies")
    SubRecipeDTO subRecipeToSubRecipeDTO(SubRecipe subRecipe);

    /**
     * Mapper method to convert a SubRecipe object to a SubRecipeDTO object
     * using MapStruct
     * @param subRecipe The SubRecipe object to be mapped
     * @return The resulting SubRecipeDTO object mapped to
     */
    //@Mapping(target = "movies",source = "movies")
    SubRecipePostDTO subRecipeToSubRecipePostDTO(SubRecipe subRecipe);

    /**
     * Mapper method to convert a collection of SubRecipe objects to a collection
     * of SubRecipeListDTO objects using MapStruct
     * @param subRecipes The collection of SubRecipe objects to be mapped
     * @return The resulting collection of SubRecipeDTO objects mapped to
     */
    Collection<SubRecipeDTO> subRecipeToSubRecipeDTO(Collection<SubRecipe> subRecipes);

    /**
     * Mapper method to convert a SubRecipeDTO object to a SubRecipe object
     * using MapStruct
     * @param subRecipeDTO The SubRecipeDTO object to be mapped
     * @return The resulting SubRecipe mapped to
     */
    //@Mapping(target = "movies",source = "movies")
    SubRecipe subRecipeDTOToSubRecipe(SubRecipeDTO subRecipeDTO);
    SubRecipe subRecipePostDTOToSubRecipe(SubRecipePostDTO subRecipePostDTO);
}
