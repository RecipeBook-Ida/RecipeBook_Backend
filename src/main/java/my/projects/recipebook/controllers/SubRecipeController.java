package my.projects.recipebook.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import my.projects.recipebook.mappers.SubRecipeMapper;
import my.projects.recipebook.models.Ingredient;
import my.projects.recipebook.models.IngredientQuantity;
import my.projects.recipebook.models.SubRecipe;
import my.projects.recipebook.models.dto.ingredient.IngredientQuantityPostDTO;
import my.projects.recipebook.models.dto.subrecipe.SubRecipeDTO;
import my.projects.recipebook.models.dto.subrecipe.SubRecipePostDTO;
import my.projects.recipebook.repositories.IngredientQuantityRepository;
import my.projects.recipebook.repositories.IngredientRepository;
import my.projects.recipebook.repositories.SubRecipeRepository;
import my.projects.recipebook.services.recipe.SubRecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(path = "subRecipes")
public class SubRecipeController {
    private final SubRecipeService subRecipeService;
    private final SubRecipeMapper subRecipeMapper;

    @Autowired
    private SubRecipeRepository subRecipeRepository;

    @Autowired
    private IngredientQuantityRepository ingredientQuantityRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    public SubRecipeController(SubRecipeService subRecipeService, SubRecipeMapper subRecipeMapper) {
        this.subRecipeService = subRecipeService;
        this.subRecipeMapper = subRecipeMapper;
    }

    @GetMapping()
    @Operation(summary = "Gets all subRecipes", tags = {"SubRecipes"})
    @ApiResponse(responseCode = "200", description = "Found the subRecipes",
            content = {@Content(mediaType = "application/json", schema = @Schema(oneOf = {SubRecipeDTO.class})) })
    public ResponseEntity<Collection<?>> findAll(){
        return ResponseEntity.ok(subRecipeMapper.subRecipeToSubRecipeDTO(subRecipeService.findAll()));
    }

    @PostMapping()
    @Operation(summary = "Create new subRecipe", tags = {"SubRecipes"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cretated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = SubRecipePostDTO.class))}),
    })
    public ResponseEntity<SubRecipeDTO> add(@RequestBody SubRecipePostDTO subRecipePostDTO){
        SubRecipe subRecipe = new SubRecipe();
        subRecipe.setTitle(subRecipePostDTO.getTitle());
        subRecipe.setInstructions(subRecipePostDTO.getInstructions());

        List<IngredientQuantity> ingredients = new ArrayList<>();
        for(IngredientQuantityPostDTO ingredientQuantityPostDTO : subRecipePostDTO.getIngredients()){
            // Retrieve existing ingredient by ID
            Ingredient ingredient = ingredientRepository.
                    findById(ingredientQuantityPostDTO.getIngredientId()).
                    orElseThrow(() -> new IllegalArgumentException("Invalid ingredientId"));

            // Check if IngredientQuantity exists
            IngredientQuantity ingredientQuantity = ingredientQuantityRepository
                    .findByQuantityAndUnitAndIngredient(ingredientQuantityPostDTO.getQuantity(),
                            ingredientQuantityPostDTO.getUnit(), ingredient).orElse(null);

            // If IngredientQuantity doesn't exist, create a new one
            if(ingredientQuantity == null){
                ingredientQuantity = new IngredientQuantity();
                ingredientQuantity.setIngredient(ingredient);
                ingredientQuantity.setUnit(ingredientQuantityPostDTO.getUnit());
                ingredientQuantity.setQuantity(ingredientQuantityPostDTO.getQuantity());
                ingredientQuantity = ingredientQuantityRepository.save(ingredientQuantity);
            }

            // Add the ingredient to the list
            ingredients.add(ingredientQuantity);
        }

        subRecipe.setIngredients(ingredients);


        SubRecipe newSubRecipe = subRecipeService.add(subRecipe);
        SubRecipeDTO newSubRecipeDTO = subRecipeMapper.subRecipeToSubRecipeDTO(newSubRecipe);
        URI uri = URI.create(String.format("api/v1/subRecipe/%s", newSubRecipeDTO.getId()));
        return ResponseEntity.created(uri).body(newSubRecipeDTO);

    }
}
