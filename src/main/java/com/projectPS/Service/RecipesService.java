package com.projectPS.Service;

import com.projectPS.Contract.RecipesContract;
import com.projectPS.Model.Ingredients;
import com.projectPS.Model.Recipes;
import com.projectPS.Repository.RecipesRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class RecipesService implements RecipesContract {
    private final RecipesRepository recipesRepository;
    @Autowired

    public RecipesService(RecipesRepository recipesRepository) {
        this.recipesRepository = recipesRepository;
    }

    /**
     * Retrieves fast recipes.
     *
     * @return a list of fast recipes
     */
    public List<Recipes> findFastRecipes(){
        return recipesRepository.findFastRecipes();
    }

    /**
     * Retrieves slow-cooked recipes.
     *
     * @return a list of slow-cooked recipes
     */

    public List<Recipes> findSlowCookedRecipes(){
        return recipesRepository.findSlowCookedRecipes();
    }

    /**
     * Return all recipes.
     *
     * @return a list of all recipes
     */

    public List<Recipes> getRecipes(){
        return recipesRepository.findAll();
    }

    public Optional<Recipes> findById(Long id){
        return recipesRepository.findById(id);
    }

    /**
     * Saves a recipe.
     *
     * @param recipes the recipe to be saved
     * @return the saved recipe
     */
    public Recipes saveRecipes(Recipes recipes){
        Recipes tmp=recipesRepository.findByName(recipes.getName());
        if(tmp == null){
           return recipesRepository.save(recipes);
        }
        return tmp;

    }

    /**
     * Adds a recipe.
     *
     * @param recipes the recipe to be added
     * @return the added recipe
     */

    public Recipes addRecipes(Recipes recipes) {
        return saveRecipes(recipes);
    }

    /**
     * Deletes a recipe by ID.
     *
     * @param recipeId the ID of the recipe to be deleted
     */

    public void deleteRecipe(Long recipeId) {
        boolean exists= recipesRepository.existsById(recipeId);
        if (!exists){
            throw new IllegalStateException("Recipe with id " + recipeId +"does not exist");

        }
        recipesRepository.deleteById(recipeId);
    }

    /**
     * Updates a recipe.
     *
     * @param recipeId the ID of the recipe to be updated
     * @param name the updated name of the recipe
     * @param ingredientsList the updated list of ingredients of the recipe
     * @param timeForCooking the updated time for cooking of the recipe
     */

    @Transactional
    public Recipes updateRecipe(Long recipeId,
                                 String name,
                                 List<Ingredients> ingredientsList ,
                                LocalTime timeForCooking) {
        Recipes recipe = recipesRepository.findById(recipeId).orElseThrow();
        if (name !=null && name.length()>0 && !Objects.equals(recipe.getName(),name)){
            recipe.setName(name);
        }
        if (ingredientsList !=null && !Objects.equals(recipe.getIngredientsList(),ingredientsList)){
            recipe.setIngredientsList(ingredientsList);
        }
        if (timeForCooking !=null && !Objects.equals(recipe.getTimeForCooking(),timeForCooking)){
            recipe.setTimeForCooking(timeForCooking);
        }
        Recipes value = new Recipes(recipeId,name,ingredientsList,timeForCooking);
        return value;
    }
}
