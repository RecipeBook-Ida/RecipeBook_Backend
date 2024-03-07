package my.projects.recipebook.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tags;
import my.projects.recipebook.mappers.AppUserMapper;
import my.projects.recipebook.services.appuser.AppUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @ApiResponses(value = {})
    public ResponseEntity<Collection<?>> findAll(){
        return ResponseEntity.ok(appUserMapper.appUserToAppUserDTO(appUserService.findAll()));
    }


}
