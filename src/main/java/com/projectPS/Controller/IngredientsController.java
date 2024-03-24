package com.projectPS.Controller;

import com.projectPS.Model.Ingredients;
import com.projectPS.Service.IngredientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping(path = "/ingredients")

public class IngredientsController {
    private final IngredientsService ingredientsService;
    @Autowired

    public IngredientsController(IngredientsService ingredientsService) {
        this.ingredientsService = ingredientsService;
    }

    @GetMapping
    public List<Ingredients> getIngredients(){
        return ingredientsService.getIngredients();
    }
}
