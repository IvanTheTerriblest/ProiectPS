package com.projectPS.Service;

import com.projectPS.Model.Ingredients;
import com.projectPS.Model.Recipes;
import com.projectPS.Repository.RecipesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
