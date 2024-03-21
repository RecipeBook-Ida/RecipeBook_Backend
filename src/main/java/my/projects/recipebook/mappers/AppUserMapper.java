package my.projects.recipebook.mappers;

import my.projects.recipebook.models.AppUser;
import my.projects.recipebook.models.dto.appuser.AppUserDTO;
import my.projects.recipebook.models.dto.appuser.AppUserListDTO;
import my.projects.recipebook.models.dto.appuser.AppUserPostDTO;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface AppUserMapper {


    AppUserListDTO appUserToAppUserListDTO(AppUser appUser);
    Collection<AppUserDTO> appUserToAppUserDTO(Collection<AppUser> appUsers);
    AppUserDTO appUserToAppUserDTO(AppUser appUser);
    AppUser appUserDTOToAppUser(AppUserDTO appUserDTO);
    AppUser appUserPostDTOToAppUser(AppUserPostDTO appUserPostDTO);

}
