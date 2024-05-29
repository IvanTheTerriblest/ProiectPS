package com.projectPS.contract;

import com.projectPS.model.Ingredients;
import com.projectPS.model.Recipes;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface RecipesContract {
    public List<Recipes> getRecipes();
    public Recipes saveRecipes(Recipes recipes);

    public Recipes addRecipes(Recipes recipes);

    public void deleteRecipe(Long recipeId);


    public Recipes updateRecipe(Long recipeId,
                             String name,
                             List<Ingredients> ingredientsList ,
                                LocalTime timeForCooking);

    public List<String> getRecipeIngredients(Long id);

    public List<Recipes> findFastRecipes();

    public List<Recipes> findSlowCookedRecipes();

    public Optional<Recipes> findById(Long id);
}
