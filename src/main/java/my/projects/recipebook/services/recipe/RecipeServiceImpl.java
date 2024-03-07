package my.projects.recipebook.services.recipe;

import my.projects.recipebook.exceptions.RecipeNotFoundException;
import my.projects.recipebook.models.Recipe;
import my.projects.recipebook.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Implementation of RecipeService.
 * Uses the RecipeRepository to interact with the data store.
 */
@Service
public class RecipeServiceImpl implements RecipeService {

    //private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Recipe findById(Integer integer) {
        return recipeRepository.findById(integer).orElseThrow(() -> new RecipeNotFoundException(integer));
    }

    @Override
    public Collection<Recipe> findAll() {
        return recipeRepository.findAll();
    }

    @Override
    public Recipe add(Recipe entity) {
        return recipeRepository.save(entity);
    }

    @Override
    public Recipe update(Recipe entity) {
        return recipeRepository.save(entity);
    }

    @Override
    public void deleteById(Integer integer) {
        recipeRepository.deleteById(integer);
    }

    @Override
    public boolean exists(Integer integer) {
        return recipeRepository.existsById(integer);
    }
}
