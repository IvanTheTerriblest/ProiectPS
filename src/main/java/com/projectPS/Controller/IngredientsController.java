package com.projectPS.Controller;

import com.projectPS.Contract.IngredientsContract;
import com.projectPS.Model.Ingredients;
import com.projectPS.Service.IngredientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/ingredients")

public class IngredientsController {
    private final IngredientsContract ingredientsService;
    @Autowired

    public IngredientsController(IngredientsService ingredientsService) {
        this.ingredientsService = ingredientsService;
    }

    /**
     * Return all ingredients.
     *
     * @return a list of all ingredients
     */
    @GetMapping
    public List<Ingredients> getIngredients(){
        return ingredientsService.getIngredients();
    }

    @GetMapping(path="{id}")
    public Optional<Ingredients> getIngredient(@PathVariable("id") Long ingredientId){
       return ingredientsService.findById(ingredientId);
    }

    @GetMapping(path="/nume/{name}")
    public Ingredients getIngredientByName(@PathVariable("name") String name){
        return ingredientsService.findByName(name);
    }

    @GetMapping(path="/data/{date}")
    public List<Ingredients> getIngredientByName(@PathVariable("date") Date date){
        return ingredientsService.findByExpirationDate(date);
    }

    @GetMapping(path="/expired")
    public List<Ingredients> getExpiredIngredients(){
        return ingredientsService.findExpiredIngredients();
    }




    /**
     * Adds a new ingredient.
     *
     * @param ingredient the ingredient to be added
     * @return the added ingredient
     */
    @PostMapping
    public Ingredients addIngredient(@RequestBody Ingredients ingredient){
       return ingredientsService.addIngredient(ingredient);
    }
    /**
     * Deletes an ingredient by ID.
     *
     * @param ingredientId the ID of the ingredient to be deleted
     */

    @DeleteMapping(path = "{ingredientId}")
    public void deleteIngredient(@PathVariable("ingredientId") Long ingredientId){
        ingredientsService.deleteIngredient(ingredientId);
    }

    /**
     * Updates an existing ingredient.
     *
     * @param ingredientId the ID of the ingredient to be updated
     * @param ingredient   the updated ingredient data
     */

    @PutMapping(path = "{ingredientId}")
    public void updateIngredient(@PathVariable("ingredientId") Long ingredientId,
                                @RequestBody Ingredients ingredient)
                                 {
        ingredientsService.updateIngredient(ingredientId,ingredient.getName(),
                ingredient.getExpirationDate(),ingredient.getQuantity());
    }
}
