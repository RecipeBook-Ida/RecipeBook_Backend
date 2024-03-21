package my.projects.recipebook.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import my.projects.recipebook.exceptions.RecipeNotFoundException;
import my.projects.recipebook.mappers.AppUserMapper;
import my.projects.recipebook.models.AppUser;
import my.projects.recipebook.models.dto.appuser.AppUserDTO;
import my.projects.recipebook.models.dto.appuser.AppUserPostDTO;
import my.projects.recipebook.services.appuser.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping(path = "appusers")
public class AppUserController {
    private final AppUserService appUserService;
    private final AppUserMapper appUserMapper;

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

}
