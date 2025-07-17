package spring.project.cookshare.service.recipe;

import org.springframework.stereotype.Service;
import spring.project.cookshare.exception.RecipeNotFoundException;
import spring.project.cookshare.model.Recipe;
import spring.project.cookshare.model.User;
import spring.project.cookshare.repository.RecipeRepository;
import spring.project.cookshare.repository.UserRepository;
import spring.project.cookshare.request.CreateRecipeRequest;
import spring.project.cookshare.request.UpdateRecipeRequest;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RecipeService implements IRecipeService{
    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;

    public RecipeService(RecipeRepository recipeRepository, UserRepository userRepository) {
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    @Override
    public Recipe getRecipeById(Long id) {
        return recipeRepository.findById(id)
                .orElseThrow(() -> new RecipeNotFoundException("Recipe with given id " + id + " doesn't exist."));
    }

    @Override
    public Set<String> getAllRecipeCategories() {
        return recipeRepository.findAll()
                .stream()
                .map(Recipe::getCategory)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getAllRecipeCuisine() {
        return recipeRepository.findAll()
                .stream()
                .map(Recipe::getCuisine)
                .collect(Collectors.toSet());
    }

    //tODO Tu jest chyba błąd bo 1)do użytkownika dodajesz inny request niż ten co zapisujesz w bazie
    // 2) po ElseGet nie zpisujesz w nowym użytkowniku recipe
    @Override
    public Recipe createRecipe(CreateRecipeRequest request) {
        if (request == null || request.getUser() == null) {
            throw new IllegalArgumentException("Invalid request.");
        }
        User user = Optional.ofNullable(userRepository.findByUsername(request.getUser().getUsername()))
                .map(existingUser -> {
                    existingUser.getRecipe().add(request.getRecipe());
                    return existingUser;
                }).orElseGet(() -> {
                    User newUser = new User(request.getUser().getUsername());
                    userRepository.save(newUser);
                    return newUser;
                });
        Recipe recipe = IRecipeService.createRecipe(request, user);
        return recipeRepository.save(recipe);
    }

    @Override
    public Recipe updateRecipe(UpdateRecipeRequest request, Long id) {
        Recipe recipe = getRecipeById(id);
        Recipe theRecipe = IRecipeService.updateRecipe(recipe, request);
        return recipeRepository.save(theRecipe);
    }

    @Override
    public void deleteRecipe(Long id) {
        recipeRepository.deleteById(id);
    }
}
