package my.projects.recipebook.models.dto.appuser;

import lombok.Getter;
import lombok.Setter;

/**
 * Domain class (entity) to carry some information from User class.
 * Is used in lists.
 * Is used in Mapper class where it carries between layers
 * Includes autogenerated getters and setters from lombok
 */
@Getter
@Setter
public class AppUserListDTO {
    private int id;
    private String username;
    private String image;
}
