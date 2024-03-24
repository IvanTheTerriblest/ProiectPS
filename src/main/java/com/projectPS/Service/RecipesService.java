package com.projectPS.Service;

import com.projectPS.Model.Ingredients;
import com.projectPS.Model.Recipes;
import com.projectPS.Repository.RecipesRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class RecipesService {
    private final RecipesRepository recipesRepository;
    @Autowired

    public RecipesService(RecipesRepository recipesRepository) {
        this.recipesRepository = recipesRepository;
    }

    public List<Recipes> getRecipes(){
        return recipesRepository.findAll();
    }
    public Recipes saveRecipes(Recipes recipes){
        Recipes tmp=recipesRepository.findByName(recipes.getName());
        if(tmp == null){
           return recipesRepository.save(recipes);
        }
        return tmp;

    }

    public Recipes addRecipes(Recipes recipes) {
        return saveRecipes(recipes);
    }

    public void deleteRecipe(Long recipeId) {
        boolean exists= recipesRepository.existsById(recipeId);
        if (!exists){
            throw new IllegalStateException("Recipe with id " + recipeId +"does not exist");

        }
        recipesRepository.deleteById(recipeId);
    }

    @Transactional
    public void updateRecipe(Long recipeId,
                                 String name,
                                 List<Ingredients> ingredientsList ,
                                 Time timeForCooking) {
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
    }
}
