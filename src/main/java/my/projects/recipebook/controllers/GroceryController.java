package my.projects.recipebook.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import my.projects.recipebook.exceptions.RecipeNotFoundException;
import my.projects.recipebook.mappers.GroceryMapper;
import my.projects.recipebook.models.Grocery;
import my.projects.recipebook.models.Ingredient;
import my.projects.recipebook.models.IngredientQuantity;
import my.projects.recipebook.models.SubRecipe;
import my.projects.recipebook.models.dto.grocery.GroceryDTO;
import my.projects.recipebook.models.dto.grocery.GroceryPostDTO;
import my.projects.recipebook.models.dto.ingredient.IngredientQuantityPostDTO;
import my.projects.recipebook.repositories.IngredientQuantityRepository;
import my.projects.recipebook.repositories.IngredientRepository;
import my.projects.recipebook.services.grocery.GroceryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(path = "grocery")
public class GroceryController {
    private final GroceryService groceryService;
    private final GroceryMapper groceryMapper;

    @Autowired
    private IngredientQuantityRepository ingredientQuantityRepository;
    @Autowired
    private IngredientRepository ingredientRepository;

    public GroceryController(GroceryService groceryService, GroceryMapper groceryMapper) {
        this.groceryService = groceryService;
        this.groceryMapper = groceryMapper;
    }

    @GetMapping()
    @Operation(summary = "Gets all groceries", tags = {"Grocery"})
    @ApiResponse(responseCode = "200", description = "Found the groceries",
            content = {@Content(mediaType = "application/json", schema = @Schema(oneOf = {GroceryDTO.class})) })
    public ResponseEntity<Collection<?>> findAll(){
        return ResponseEntity.ok(groceryMapper.groceryToGroceryDTO(groceryService.findAll()));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get a grocery by id", description = "User must exist", tags = {"Grocery"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the grocery",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(oneOf = { GroceryDTO.class })) }),
            @ApiResponse(responseCode = "400", description = "Invalid Id supplied"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))}),

    })
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(groceryMapper.groceryToGroceryDTO(groceryService.findById(id)));
        }catch (RecipeNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with id: "+id);
        }

    }

    @PostMapping()
    @Operation(summary = "Create new grocery", tags = {"Grocery"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cretated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GroceryDTO.class))}),
    })
    public ResponseEntity<GroceryDTO> add(@RequestBody GroceryPostDTO groceryPostDTO){
        Grocery grocery = new Grocery();

        Ingredient ingredient = ingredientRepository.
                findById(groceryPostDTO.getIngredientId()).
                orElseThrow(() -> new IllegalArgumentException("Invalid ingredientId"));
        grocery.setIngredient(ingredient);


        List<IngredientQuantity> quantities = new ArrayList<>();
        for(Integer ingredientQuantityId : groceryPostDTO.getIngredientQuantityIds()) {
            // Retrieve existing ingredient by ID
            IngredientQuantity quantity = ingredientQuantityRepository
                    .findById(ingredientQuantityId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid ingredientQuantityId"));

            quantities.add(quantity);
        }

        grocery.setQuantities(quantities);

        Grocery newGrocery = groceryService.add(grocery);
        GroceryDTO newGroceryDTO = groceryMapper.groceryToGroceryDTO(newGrocery);
        URI uri = URI.create(String.format("api/v1/grocery/%s", newGroceryDTO.getId()));
        return ResponseEntity.created(uri).body(newGroceryDTO);

    }


    @PutMapping("/update/{id}")
    @Operation(summary = "Update grocery", tags = {"Grocery"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GroceryDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    public ResponseEntity<GroceryDTO> update(@PathVariable Integer id, @RequestBody GroceryDTO groceryDTO){
        Grocery grocery = groceryMapper.groceryDTOToGrocery((groceryDTO));
        grocery.setId(id);
        groceryService.update(grocery);
        return ResponseEntity.ok(groceryMapper.groceryToGroceryDTO(grocery));

    }


}
