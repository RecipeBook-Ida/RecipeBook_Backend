package my.projects.recipebook.services.recipe;

import my.projects.recipebook.exceptions.RecipeNotFoundException;
import my.projects.recipebook.models.SubRecipe;
import my.projects.recipebook.repositories.SubRecipeRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Implementation of SubRecipeService.
 * Uses the SubRecipeRepository to interact with the data store.
 */
@Service
public class SubRecipeServiceImpl implements SubRecipeService{
    //private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final SubRecipeRepository subRecipeRepository;

    public SubRecipeServiceImpl(SubRecipeRepository subRecipeRepository) {
        this.subRecipeRepository = subRecipeRepository;
    }

    @Override
    public SubRecipe findById(Integer integer) {
        return subRecipeRepository.findById(integer).orElseThrow(() -> new RecipeNotFoundException(integer));
    }

    @Override
    public Collection<SubRecipe> findAll() { return subRecipeRepository.findAll();
    }

    @Override
    public SubRecipe add(SubRecipe entity) {
        return subRecipeRepository.save(entity);
    }

    @Override
    public SubRecipe update(SubRecipe entity) {
        return subRecipeRepository.save(entity);
    }

    @Override
    public void deleteById(Integer integer) {
        subRecipeRepository.deleteById(integer);
    }

    @Override
    public boolean exists(Integer integer) {
        return subRecipeRepository.existsById(integer);
    }
}
