package my.projects.recipebook.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import my.projects.recipebook.exceptions.RecipeNotFoundException;
import my.projects.recipebook.mappers.IngredientMapper;
import my.projects.recipebook.models.Ingredient;
import my.projects.recipebook.models.dto.ingredient.IngredientDTO;
import my.projects.recipebook.models.dto.ingredient.IngredientPostDTO;
import my.projects.recipebook.services.ingredient.IngredientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping(path = "ingredient")
public class IngredientController {
    private final IngredientService ingredientService;
    private final IngredientMapper ingredientMapper;

    public IngredientController(IngredientService ingredientService, IngredientMapper ingredientMapper) {
        this.ingredientService = ingredientService;
        this.ingredientMapper = ingredientMapper;
    }

    @GetMapping()
    @Operation(summary = "Gets all ingredients", tags = {"Ingredient"})
    @ApiResponse(responseCode = "200", description = "Found the ingredient",
            content = {@Content(mediaType = "application/json", schema = @Schema(oneOf = {IngredientDTO.class})) })
    public ResponseEntity<Collection<?>> findAll(){
        return ResponseEntity.ok(ingredientMapper.ingredientToIngredientDTO(ingredientService.findAll()));
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get a ingredient by id", description = "Ingredient must exist", tags = {"Ingredient"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the ingredient",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(oneOf = { IngredientDTO.class })) }),
            @ApiResponse(responseCode = "400", description = "Invalid Id supplied"),
            @ApiResponse(responseCode = "404", description = "Ingredient not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))}),

    })
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(ingredientMapper.ingredientToIngredientDTO(ingredientService.findById(id)));
        }catch (RecipeNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ingredient not found with id: "+id);
        }

    }

    @PostMapping()
    @Operation(summary = "Create new ingredient", tags = {"Ingredient"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cretated",
            content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = IngredientPostDTO.class))}),
    })
    public ResponseEntity<IngredientDTO> add(@RequestBody IngredientPostDTO ingredientPostDTO){
        Ingredient ingredient = ingredientMapper.ingredientPostDTOToIngredient(ingredientPostDTO);
        Ingredient newIngredient = ingredientService.add(ingredient);
        IngredientDTO newIngredientDTO = ingredientMapper.ingredientToIngredientDTO(newIngredient);
        URI uri = URI.create(String.format("api/v1/ingredient/%s", newIngredientDTO.getId()));
        return ResponseEntity.created(uri).body(newIngredientDTO);

    }
}
