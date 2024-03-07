package my.projects.recipebook.mappers;

import my.projects.recipebook.models.AppUser;
import my.projects.recipebook.models.dto.appuser.AppUserDTO;
import my.projects.recipebook.models.dto.appuser.AppUserListDTO;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface AppUserMapper {

    /**
     * Mapper method to convert a User object to a UserListDTO object
     * using MapStruct
     * @param appUser The User object to be mapped
     * @return The resulting UserListDTO object mapped to
     */
    AppUserListDTO appUserToAppUserListDTO(AppUser appUser);

    /**
     * Mapper method to convert a collection of User objects to a collection
     * of UserListDTO objects using MapStruct
     * @param appUsers The collection of User objects to be mapped
     * @return The resulting collection of UserDTO objects mapped to
     */
    Collection<AppUserDTO> appUserToAppUserDTO(Collection<AppUser> appUsers);

    /**
     * Mapper method to convert a User object to a UserDTO object
     * using MapStruct
     * @param appUser The User object to be mapped
     * @return The resulting UserDTO object mapped to
     */
    //@Mapping(target = "movies",source = "movies")
    AppUserDTO appUserToAppUserDTO(AppUser appUser);

    /**
     * Mapper method to convert a UserDTO object to a User object
     * using MapStruct
     * @param appUserDTO The UserDTO object to be mapped
     * @return The resulting User mapped to
     */
    //@Mapping(target = "movies",source = "movies")
    AppUser appUserDTOToAppUser(AppUserDTO appUserDTO);
}
