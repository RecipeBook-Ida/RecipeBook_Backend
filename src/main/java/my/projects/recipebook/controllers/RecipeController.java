package my.projects.recipebook.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import my.projects.recipebook.exceptions.RecipeNotFoundException;
import my.projects.recipebook.mappers.RecipeMapper;
import my.projects.recipebook.models.Recipe;
import my.projects.recipebook.models.dto.recipe.*;
import my.projects.recipebook.services.recipe.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping(path = "recipes")
public class RecipeController {
    private final RecipeService recipeService;
    private final RecipeMapper recipeMapper;

    public RecipeController(RecipeService recipeService, RecipeMapper recipeMapper) {
        this.recipeService = recipeService;
        this.recipeMapper = recipeMapper;
    }

    @GetMapping()
    @Operation(summary = "Gets all recipes", tags = {"Recipes"})
    @ApiResponse(responseCode = "200", description = "Found the recipes",
            content = {@Content(mediaType = "application/json", schema = @Schema(oneOf = {RecipeDTO.class})) })
    public ResponseEntity<Collection<?>> findAll(){
        return ResponseEntity.ok(recipeMapper.recipeToRecipeDTO(recipeService.findAll()));
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get a recipe by id", description = "Recipe must exist", tags = {"Recipes", "Get"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the recipe",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(oneOf = { RecipeDTO.class })) }),
            @ApiResponse(responseCode = "400", description = "Invalid Id supplied"),
            @ApiResponse(responseCode = "404", description = "Recipe not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))}),

    })
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        try {
                return ResponseEntity.ok(recipeMapper.recipeToRecipeDTO(recipeService.findById(id)));
        }catch (RecipeNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recipe not found with id: "+id);
        }

    }


    @PostMapping()
    @Operation(summary = "Create new recipe", tags = {"Recipes"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cretated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = RecipeDTO.class))}),
    })
    public ResponseEntity<RecipeDTO> add(@RequestBody RecipeDTO recipeDTO){
        Recipe recipe = recipeMapper.recipeDTOToRecipe(recipeDTO);
        Recipe newRecipe = recipeService.add(recipe);
        RecipeDTO newRecipeDTO = recipeMapper.recipeToRecipeDTO(newRecipe);
        URI uri = URI.create(String.format("api/v1/recipe/%s", recipeDTO.getId()));
        return ResponseEntity.created(uri).body(newRecipeDTO);

    }
}
