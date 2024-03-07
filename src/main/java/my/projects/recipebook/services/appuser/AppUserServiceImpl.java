package my.projects.recipebook.services.appuser;

import my.projects.recipebook.exceptions.AppUserNotFoundException;
import my.projects.recipebook.models.AppUser;
import my.projects.recipebook.repositories.AppUserRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Implementation of UserService.
 * Uses the UserRepository to interact with the data store.
 */
@Service
public class AppUserServiceImpl implements AppUserService {

    //private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final AppUserRepository appUserRepository;

    public AppUserServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public AppUser findById(Integer integer) {
        return appUserRepository.findById(integer).orElseThrow(() -> new AppUserNotFoundException(integer));
    }

    @Override
    public Collection<AppUser> findAll() {
        return appUserRepository.findAll();
    }

    @Override
    public AppUser add(AppUser entity) {
        return appUserRepository.save(entity);
    }

    @Override
    public AppUser update(AppUser entity) {
        return appUserRepository.save(entity);
    }

    @Override
    public void deleteById(Integer integer) {
        appUserRepository.deleteById(integer);
    }

    @Override
    public boolean exists(Integer integer) {
        return appUserRepository.existsById(integer);
    }
}
