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
import my.projects.recipebook.models.IngredientQuantity;
import my.projects.recipebook.models.Recipe;
import my.projects.recipebook.models.dto.appuser.AppUserDTO;
import my.projects.recipebook.models.dto.appuser.AppUserPostDTO;
import my.projects.recipebook.models.dto.appuser.FavoritesPutDTO;
import my.projects.recipebook.models.dto.appuser.GroceryListPutDTO;
import my.projects.recipebook.repositories.AppUserRepository;
import my.projects.recipebook.repositories.IngredientQuantityRepository;
import my.projects.recipebook.repositories.RecipeRepository;
import my.projects.recipebook.services.appuser.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.*;

@RestController
@RequestMapping(path = "appuser")
public class AppUserController {
    private final AppUserService appUserService;
    private final AppUserMapper appUserMapper;

    @Autowired
    private IngredientQuantityRepository ingredientQuantityRepository;
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private RecipeRepository recipeRepository;


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

    /*
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
*/
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
        List<IngredientQuantity> groceries = existingAppUser.getGroceryList();


        for (Integer groceryId : groceryListPutDTO.getIngredientQuantityIds()){

            IngredientQuantity newGrocery = ingredientQuantityRepository
                    .findById(groceryId)
                    .orElseThrow(()-> new IllegalArgumentException("Invalid grocery"));

            // Check if there's already an ingredient quantity with the same ingredient and unit
            Optional<IngredientQuantity> existingQuantityOptional = groceries.stream()
                    .filter(q -> q.getIngredient().equals(newGrocery.getIngredient()) && q.getUnit().equals(newGrocery.getUnit()))
                    .findFirst();

            if (existingQuantityOptional.isPresent()) {
                IngredientQuantity existingQuantity = existingQuantityOptional.get();
                groceries.remove(existingQuantity);

                IngredientQuantity doubleIngredientQuantity = ingredientQuantityRepository
                        .findByQuantityAndUnitAndIngredient(existingQuantity.getQuantity()+newGrocery.getQuantity(),
                                existingQuantity.getUnit(), existingQuantity.getIngredient()).orElse(null);


                // If IngredientQuantity doesn't exist, create a new one
                if (doubleIngredientQuantity == null) {
                    doubleIngredientQuantity = new IngredientQuantity();
                    doubleIngredientQuantity.setIngredient(newGrocery.getIngredient());
                    doubleIngredientQuantity.setUnit(newGrocery.getUnit());
                    doubleIngredientQuantity.setQuantity(newGrocery.getQuantity()+existingQuantity.getQuantity());
                    ingredientQuantityRepository.save(doubleIngredientQuantity);
                }
                groceries.add(doubleIngredientQuantity);

            }else groceries.add(newGrocery);

        }
        existingAppUser.setGroceryList(groceries);
        appUserRepository.save(existingAppUser);
        return ResponseEntity.ok(appUserMapper.appUserToAppUserDTO(existingAppUser));
    }

    /*
    @PatchMapping(path = "update/patch/{id}")
    @Operation(summary = "Patch update user", tags = {"AppUser"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AppUserDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    public ResponseEntity<AppUser> patchUpdateCustomer(@PathVariable String id, @RequestBody AppUserDTO appUserDTO) {
        AppUser user = appUserService.findById(appUserDTO.getId());


        try {
            Customer customer = customerService.findCustomer(id).orElseThrow(CustomerNotFoundException::new);
            Customer customerPatched = applyPatchToCustomer(patch, customer);
            customerService.updateCustomer(customerPatched);
            return ResponseEntity.ok(customerPatched);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
*/



    @PutMapping("/update/favorites/{id}")
    @Operation(summary = "Update user's favorite recipes", tags = {"AppUser"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Favorites successfully updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FavoritesPutDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    public ResponseEntity<AppUserDTO> favoritesUpdate(@PathVariable Integer id, @RequestBody FavoritesPutDTO favoritesPutDTO){
        AppUser user = appUserRepository.findById(id)
                .orElseThrow(() -> new AppUserNotFoundException(id));
        List<Recipe> favorites = recipeRepository.findAllById(favoritesPutDTO.getRecipeIds());
        user.setFavorites(favorites);

        appUserRepository.save(user);
        return ResponseEntity.ok(appUserMapper.appUserToAppUserDTO(user));
    }

}
