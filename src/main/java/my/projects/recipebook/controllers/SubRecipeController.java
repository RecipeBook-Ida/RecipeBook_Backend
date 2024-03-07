package my.projects.recipebook.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import my.projects.recipebook.mappers.SubRecipeMapper;
import my.projects.recipebook.models.SubRecipe;
import my.projects.recipebook.models.dto.recipe.SubRecipeDTO;
import my.projects.recipebook.services.recipe.SubRecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping(path = "subRecipes")
public class SubRecipeController {
    private final SubRecipeService subRecipeService;
    private final SubRecipeMapper subRecipeMapper;

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
                            schema = @Schema(implementation = SubRecipeDTO.class))}),
    })
    public ResponseEntity<SubRecipeDTO> add(@RequestBody SubRecipeDTO subRecipeDTO){
        SubRecipe subRecipe = subRecipeMapper.subRecipeDTOToSubRecipe(subRecipeDTO);
        SubRecipe newSubRecipe = subRecipeService.add(subRecipe);
        SubRecipeDTO newSubRecipeDTO = subRecipeMapper.subRecipeToSubRecipeDTO(newSubRecipe);
        URI uri = URI.create(String.format("api/v1/subRecipe/%s", subRecipeDTO.getId()));
        return ResponseEntity.created(uri).body(newSubRecipeDTO);

    }
}
