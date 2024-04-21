package com.projectPS.Contract;

import com.projectPS.Model.Ingredients;
import com.projectPS.Model.Recipes;
import jakarta.transaction.Transactional;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
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

    public List<Recipes> findFastRecipes();

    public List<Recipes> findSlowCookedRecipes();

    public Optional<Recipes> findById(Long id);
}
