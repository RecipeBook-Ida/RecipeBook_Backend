package my.projects.recipebook.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import my.projects.recipebook.exceptions.AppUserNotFoundException;
import my.projects.recipebook.exceptions.RecipeNotFoundException;
import my.projects.recipebook.mappers.AppUserMapper;
import my.projects.recipebook.models.AppUser;
import my.projects.recipebook.models.Grocery;
import my.projects.recipebook.models.Ingredient;
import my.projects.recipebook.models.IngredientQuantity;
import my.projects.recipebook.models.dto.appuser.AppUserDTO;
import my.projects.recipebook.models.dto.appuser.AppUserPostDTO;
import my.projects.recipebook.models.dto.appuser.GroceryListPutDTO;
import my.projects.recipebook.repositories.AppUserRepository;
import my.projects.recipebook.repositories.GroceryRepository;
import my.projects.recipebook.repositories.IngredientQuantityRepository;
import my.projects.recipebook.repositories.SubRecipeRepository;
import my.projects.recipebook.services.appuser.AppUserService;
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
@RequestMapping(path = "appuser")
public class AppUserController {
    private final AppUserService appUserService;
    private final AppUserMapper appUserMapper;

    @Autowired
    private GroceryRepository groceryRepository;
    @Autowired
    private IngredientQuantityRepository ingredientQuantityRepository;
    @Autowired
    private AppUserRepository appUserRepository;


    public AppUserController(AppUserService appUserService, AppUserMapper appUserMapper) {
        this.appUserService = appUserService;
        this.appUserMapper = appUserMapper;
    }

    @GetMapping()
    @Operation(summary = "Gets all users", tags = {"AppUser"})
    @ApiResponse(responseCode = "200", description = "Found the users",
            content = {@Content(mediaType = "application/json", schema = @Schema(oneOf = {AppUserDTO.class})) })
    public ResponseEntity<Collection<?>> findAll(){
        return ResponseEntity.ok(appUserMapper.appUserToAppUserDTO(appUserService.findAll()));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get a user by id", description = "User must exist", tags = {"AppUser"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the user",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(oneOf = { AppUserDTO.class })) }),
            @ApiResponse(responseCode = "400", description = "Invalid Id supplied"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))}),

    })
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(appUserMapper.appUserToAppUserDTO(appUserService.findById(id)));
        }catch (RecipeNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with id: "+id);
        }

    }

    @PostMapping()
    @Operation(summary = "Create new user", tags = {"AppUser"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cretated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AppUserDTO.class))}),
    })
    public ResponseEntity<AppUserDTO> add(@RequestBody AppUserPostDTO appUserPostDTO){
        AppUser user = appUserMapper.appUserPostDTOToAppUser(appUserPostDTO);


        AppUser newUser = appUserService.add(user);
        AppUserDTO newAppUserDTO = appUserMapper.appUserToAppUserDTO(newUser);
        URI uri = URI.create(String.format("api/v1/ingredient/%s", newAppUserDTO.getId()));
        return ResponseEntity.created(uri).body(newAppUserDTO);

    }

    /**
    @PutMapping("/{id}")
    @Operation(summary = "Update user", tags = {"AppUser"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AppUserDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    public ResponseEntity<AppUserDTO> patchUpdate(@PathVariable Integer id, @RequestBody AppUserPostDTO appUserPostDTO){
        AppUser updatedUser = appUserService.patchUdate(id, appUserPostDTO);
        return ResponseEntity.ok(appUserMapper.appUserToAppUserDTO(updatedUser));

    }
**/
    @PutMapping("/update/{id}")
    @Operation(summary = "Update user", tags = {"AppUser"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AppUserDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    public ResponseEntity<AppUserDTO> update(@PathVariable Long id, @RequestBody AppUserPostDTO appUserPostDTO){
        AppUser user = appUserMapper.appUserPostDTOToAppUser((appUserPostDTO));
        user.setId(id);
        appUserService.update(user);
        return ResponseEntity.ok(appUserMapper.appUserToAppUserDTO(user));

    }


    @PutMapping("/update/groceryList/{id}")
    @Operation(summary = "Update user's grocery list", tags = {"AppUser"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "GroceryList successfully updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GroceryListPutDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    public ResponseEntity<AppUserDTO> groceryListUpdate(@PathVariable Integer id, @RequestBody GroceryListPutDTO groceryListPutDTO){
        AppUser existingAppUser = appUserRepository.findById(id)
                .orElseThrow(() -> new AppUserNotFoundException(id));
       Collection<Grocery> groceries = existingAppUser.getGroceryList();

        for (Integer groceryId : groceryListPutDTO.getGroceryIds()){
            Grocery grocery = groceryRepository
                    .findById(groceryId)
                    .orElseThrow(()-> new IllegalArgumentException("Invalid grocery"));

            if(groceries.contains(grocery)){
                //groceries.remove(ingredientQuantity);

                // Check if IngredientQuantity exists
                IngredientQuantity doubleIngredientQuantity = ingredientQuantityRepository
                        .findByQuantityAndUnitAndIngredient(ingredientQuantity.getQuantity()+ingredientQuantity.getQuantity(),
                                ingredientQuantity.getUnit(), ingredientQuantity.getIngredient()).orElse(null);

                // If IngredientQuantity doesn't exist, create a new one
                if(doubleIngredientQuantity == null){
                    doubleIngredientQuantity = new IngredientQuantity();
                    doubleIngredientQuantity.setIngredient(ingredientQuantity.getIngredient());
                    doubleIngredientQuantity.setUnit(ingredientQuantity.getUnit());
                    doubleIngredientQuantity.setQuantity(ingredientQuantity.getQuantity()+ingredientQuantity.getQuantity());
                    doubleIngredientQuantity = ingredientQuantityRepository.save(ingredientQuantity);
                }
                groceries.add(doubleIngredientQuantity);
            }else {groceries.add(ingredientQuantity);}


        }


        existingAppUser.setGroceryList(groceries);
        appUserRepository.save(existingAppUser);
        return ResponseEntity.ok(appUserMapper.appUserToAppUserDTO(existingAppUser));

    }

}
