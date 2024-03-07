package my.projects.recipebook.services.ingredient;

import my.projects.recipebook.exceptions.IngredientNotFoundException;
import my.projects.recipebook.models.Ingredient;
import my.projects.recipebook.repositories.IngredientRepository;
import my.projects.recipebook.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Implementation of RIngredientService.
 * Uses the IngredientRepository to interact with the data store.
 */
@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient findById(Integer integer) {
        return ingredientRepository.findById(integer).orElseThrow(() -> new IngredientNotFoundException(integer));
    }

    @Override
    public Collection<Ingredient> findAll() {
        return ingredientRepository.findAll();
    }

    @Override
    public Ingredient add(Ingredient entity) {
        return ingredientRepository.save(entity);
    }

    @Override
    public Ingredient update(Ingredient entity) {
        return ingredientRepository.save(entity);
    }

    @Override
    public void deleteById(Integer integer) {
        ingredientRepository.deleteById(integer);
    }

    @Override
    public boolean exists(Integer integer) {
        return ingredientRepository.existsById(integer);
    }
}
