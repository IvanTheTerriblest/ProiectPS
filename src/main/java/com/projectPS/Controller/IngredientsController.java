package com.projectPS.Controller;

import com.projectPS.Model.Ingredients;
import com.projectPS.Service.IngredientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    @PostMapping
    public Ingredients addIngredient(@RequestBody Ingredients ingredient){
       return ingredientsService.addIngredient(ingredient);
    }

    @DeleteMapping(path = "{ingredientId}")
    public void deleteIngredient(@PathVariable("ingredientId") Long ingredientId){
        ingredientsService.deleteIngredient(ingredientId);
    }

    @PutMapping(path = "{ingredientId}")
    public void updateIngredient(@PathVariable("ingredientId") Long ingredientId,
                                @RequestBody Ingredients ingredient)
                                 {
        ingredientsService.updateIngredient(ingredientId,ingredient.getName(),
                ingredient.getExpirationDate(),ingredient.getQuantity());
    }
}
