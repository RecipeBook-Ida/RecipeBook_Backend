package my.projects.recipebook.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import my.projects.recipebook.mappers.IngredientQuantityMapper;
import my.projects.recipebook.models.dto.ingredient.IngredientDTO;
import my.projects.recipebook.models.dto.ingredient.IngredientQuantityDTO;
import my.projects.recipebook.services.ingredient.IngredientQuantityService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
@RequestMapping(path = "ingredientQuantity")
public class IngredientQuantityController {

    private final IngredientQuantityMapper ingredientQuantityMapper;
    private final IngredientQuantityService ingredientQuantityService;

    public IngredientQuantityController(IngredientQuantityMapper ingredientQuantityMapper, IngredientQuantityService ingredientQuantityService) {
        this.ingredientQuantityMapper = ingredientQuantityMapper;
        this.ingredientQuantityService = ingredientQuantityService;
    }

    @GetMapping()
    @Operation(summary = "Gets all ingredientQuantities", tags = {"IngredientQuantity"})
    @ApiResponse(responseCode = "200", description = "Found the ingredient",
            content = {@Content(mediaType = "application/json", schema = @Schema(oneOf = {IngredientQuantityDTO.class})) })
    public ResponseEntity<Collection<?>> findAll(){
        return ResponseEntity.ok(ingredientQuantityMapper.ingredientQuantityToIngredientQuantityDTO(ingredientQuantityService.findAll()));
    }
}
