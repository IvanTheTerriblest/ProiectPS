package com.projectPS.Controller;

import com.projectPS.Contract.RecipesContract;
import com.projectPS.Model.Ingredients;
import com.projectPS.Model.Recipes;
import com.projectPS.Service.RecipesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/recipes")

public class RecipesController {

    public final RecipesContract recipesService;
    @Autowired

    public RecipesController(RecipesService recipesService) {
        this.recipesService = recipesService;
    }

    /**
     * Return all recipes.
     *
     * @return a list of all recipes
     */
    @GetMapping
    public List<Recipes> getRecipes(){
        return recipesService.getRecipes();
    }

    /**
     * Find fast recipes.
     *
     * @return A list of fast recipes
     */

    @GetMapping(path="/fastFood")
    public List<Recipes> findFastRecipes(){
        return recipesService.findFastRecipes();
    }
    /**
     * Get a recipe by ID.
     *
     * @param recipesId The ID of the recipe
     * @return The recipe with the specified ID, if it exists
     */
    @GetMapping(path="{id}")
    public Optional<Recipes> getRecipes(@PathVariable("id") Long recipesId){
        return recipesService.findById(recipesId);
    }

    /**
     * Adds a new recipe.
     *
     * @param recipes the recipe to be added
     * @return the added recipe
     */

    @PostMapping
    public Recipes addRecipes(@RequestBody Recipes recipes){
        return recipesService.addRecipes(recipes);
    }

    /**
     * Deletes a recipe by ID.
     *
     * @param recipeId the ID of the recipe to be deleted
     */

    @DeleteMapping(path = "{recipeId}")
    public void deleteRecipe(@PathVariable("recipeId") Long recipeId){
        recipesService.deleteRecipe(recipeId);
    }



    /**
     * Updates an existing recipe.
     *
     * @param recipeId the ID of the recipe to be updated
     * @param recipe   the updated recipe data
     */

    @PutMapping(path = "{recipeId}")
    public void updateRecipe(@PathVariable("recipeId") Long recipeId,
                                 @RequestBody Recipes recipe)
    {
        recipesService.updateRecipe(recipeId,recipe.getName()
                ,recipe.getIngredientsList(),recipe.getTimeForCooking());
    }

}
