package com.projectPS.Controller;

import com.projectPS.Model.Recipes;
import com.projectPS.Service.RecipesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
