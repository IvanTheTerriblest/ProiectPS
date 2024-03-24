package com.projectPS.Controller;

import com.projectPS.Model.Ingredients;
import com.projectPS.Model.Recipes;
import com.projectPS.Service.RecipesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/recipes")

public class RecipesController {

    public final RecipesService recipesService;
    @Autowired

    public RecipesController(RecipesService recipesService) {
        this.recipesService = recipesService;
    }
    @GetMapping
    public List<Recipes> getRecipes(){
        return recipesService.getRecipes();
    }

    @PostMapping
    public Recipes addRecipes(@RequestBody Recipes recipes){
        return recipesService.addRecipes(recipes);
    }

    @DeleteMapping(path = "{recipeId}")
    public void deleteRecipe(@PathVariable("recipeId") Long recipeId){
        recipesService.deleteRecipe(recipeId);
    }

    @PutMapping(path = "{recipeId}")
    public void updateRecipe(@PathVariable("recipeId") Long recipeId,
                                 @RequestBody Recipes recipe)
    {
        recipesService.updateRecipe(recipeId,recipe.getName()
                ,recipe.getIngredientsList(),recipe.getTimeForCooking());
    }

}
