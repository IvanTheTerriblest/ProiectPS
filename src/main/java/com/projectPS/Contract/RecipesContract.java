package com.projectPS.Contract;

import com.projectPS.Model.Ingredients;
import com.projectPS.Model.Recipes;
import jakarta.transaction.Transactional;

import java.sql.Time;
import java.util.List;
import java.util.Objects;

public interface RecipesContract {
    public List<Recipes> getRecipes();
    public Recipes saveRecipes(Recipes recipes);

    public Recipes addRecipes(Recipes recipes);

    public void deleteRecipe(Long recipeId);


    public void updateRecipe(Long recipeId,
                             String name,
                             List<Ingredients> ingredientsList ,
                             Time timeForCooking);
}
