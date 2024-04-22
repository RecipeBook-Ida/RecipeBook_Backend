package my.projects.recipebook.models.dto.appuser;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FavoritesPutDTO {
    private List<Integer> recipeIds;
}



