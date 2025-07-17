package spring.project.cookshare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.project.cookshare.model.Recipe;
import spring.project.cookshare.request.CreateRecipeRequest;
import spring.project.cookshare.request.UpdateRecipeRequest;
import spring.project.cookshare.service.recipe.IRecipeService;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/recipes")
public class Controller {
    private final IRecipeService recipeService;

    @PostMapping
    public ResponseEntity<Recipe> createRecipe(CreateRecipeRequest request) {
        Recipe recipe = recipeService.createRecipe(request);
        return new ResponseEntity<>(recipe, HttpStatus.OK);
    }

    @GetMapping("/{recipeId}/recipe")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable Long recipeId) {
        Recipe recipe = recipeService.getRecipeById(recipeId);
        return new ResponseEntity<>(recipe, HttpStatus.OK);
    }

    @PutMapping("/{recipeId}/update")
    public ResponseEntity<Recipe> updateRecipe(UpdateRecipeRequest request, @PathVariable Long recipeId) {
        Recipe recipe = recipeService.updateRecipe(request, recipeId);
        return new ResponseEntity<>(recipe, HttpStatus.OK);
    }

    @DeleteMapping("/{recipeId}/delete")
    public ResponseEntity<Recipe> deleteRecipe(@PathVariable Long recipeId) {
        recipeService.deleteRecipe(recipeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
