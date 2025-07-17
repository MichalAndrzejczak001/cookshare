package spring.project.cookshare.service.recipe;

import spring.project.cookshare.model.Recipe;
import spring.project.cookshare.model.User;
import spring.project.cookshare.request.CreateRecipeRequest;
import spring.project.cookshare.request.UpdateRecipeRequest;

import java.util.List;
import java.util.Set;

public interface IRecipeService {

    List<Recipe> getAllRecipes();
    Recipe getRecipeById(Long id);
    Set<String> getAllRecipeCategories();
    Set<String> getAllRecipeCuisine();

    Recipe createRecipe(CreateRecipeRequest request);
    Recipe updateRecipe(UpdateRecipeRequest request, Long id);
    void deleteRecipe(Long id);

    static Recipe createRecipe(CreateRecipeRequest request, User user) {
        Recipe recipe = new Recipe();

        Recipe recipeRequest = request.getRecipe();
        recipe.setTitle(recipeRequest.getTitle());
        recipe.setCuisine(recipeRequest.getCuisine());
        recipe.setCategory(recipeRequest.getCategory());
        recipe.setInstruction(recipeRequest.getInstruction());
        recipe.setDescription(recipeRequest.getDescription());
        recipe.setPrepTime(recipeRequest.getPrepTime());
        recipe.setCookTime(recipeRequest.getCookTime());
        recipe.setIngredients(recipeRequest.getIngredients());
        recipe.setUser(user);
        return recipe;
    }


    static Recipe updateRecipe(Recipe existingRecipe, UpdateRecipeRequest request){
        existingRecipe.setTitle(request.getTitle());
        existingRecipe.setDescription(request.getDescription());
        existingRecipe.setInstruction(request.getInstruction());
        existingRecipe.setCuisine(request.getCuisine());
        existingRecipe.setPrepTime(request.getPrepTime());
        existingRecipe.setCookTime(request.getCookTime());
        existingRecipe.setCategory(request.getCategory());
        existingRecipe.setIngredients(request.getIngredients());
        return existingRecipe;
    }


}
