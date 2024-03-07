package my.projects.recipebook.services.ingredient;

import my.projects.recipebook.exceptions.IngredientNotFoundException;
import my.projects.recipebook.models.Ingredient;
import my.projects.recipebook.models.IngredientQuantity;
import my.projects.recipebook.repositories.IngredientQuantityRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Implementation of IngredientQuantityService.
 * Uses the IngredientQuantityRepository to interact with the data store.
 */
@Service
public class IngredientQuantityServiceImpl implements IngredientQuantityService{
    private final IngredientQuantityRepository ingredientQuantityRepository;

    public IngredientQuantityServiceImpl(IngredientQuantityRepository ingredientQuantityRepository) {
        this.ingredientQuantityRepository = ingredientQuantityRepository;
    }

    @Override
    public IngredientQuantity findById(Integer integer) {
        return ingredientQuantityRepository.findById(integer).orElseThrow(() -> new IngredientNotFoundException(integer));
    }

    @Override
    public Collection<IngredientQuantity> findAll() {
        return ingredientQuantityRepository.findAll();
    }

    @Override
    public IngredientQuantity add(IngredientQuantity entity) {
        return ingredientQuantityRepository.save(entity);
    }

    @Override
    public IngredientQuantity update(IngredientQuantity entity) {
        return ingredientQuantityRepository.save(entity);
    }

    @Override
    public void deleteById(Integer integer) {
        ingredientQuantityRepository.deleteById(integer);
    }

    @Override
    public boolean exists(Integer integer) {
        return ingredientQuantityRepository.existsById(integer);
    }
}
