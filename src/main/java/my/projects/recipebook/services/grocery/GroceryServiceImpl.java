package my.projects.recipebook.services.grocery;

import my.projects.recipebook.exceptions.GroceryNotFoundException;
import my.projects.recipebook.models.Grocery;
import my.projects.recipebook.repositories.GroceryRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Implementation of UserService.
 * Uses the UserRepository to interact with the data store.
 */
@Service
public class GroceryServiceImpl implements GroceryService {
    
    private final GroceryRepository groceryRepository;

    public GroceryServiceImpl(GroceryRepository groceryRepository) {
        this.groceryRepository = groceryRepository;
    }

    @Override
    public Grocery findById(Integer integer) {
        return groceryRepository.findById(integer).orElseThrow(() -> new GroceryNotFoundException(integer));
    }

    @Override
    public Collection<Grocery> findAll() {
        return groceryRepository.findAll();
    }

    @Override
    public Grocery add(Grocery entity) {
        return groceryRepository.save(entity);
    }

    @Override
    public Grocery update(Grocery entity) {
        return groceryRepository.save(entity);
    }

    @Override
    public void deleteById(Integer integer) {
        groceryRepository.deleteById(integer);
    }

    @Override
    public boolean exists(Integer integer) {
        return groceryRepository.existsById(integer);
    }
}
