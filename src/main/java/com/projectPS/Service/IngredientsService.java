package com.projectPS.Service;

import com.projectPS.Model.Ingredients;
import com.projectPS.Repository.IngredientsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * Service class for managing ingredients.
 */

@Service

public class IngredientsService {

    private final IngredientsRepository ingredientsRepository;
    @Autowired

    public IngredientsService(IngredientsRepository ingredientsRepository) {
        this.ingredientsRepository = ingredientsRepository;
    }

    /**
     * Return all ingredients.
     *
     * @return a list of all ingredients
     */


    public List<Ingredients> getIngredients(){
        return ingredientsRepository.findAll();
        //return List.of(new Ingredients(1L,"Egg", LocalDate.of(2024, Month.APRIL,6),10));
    }

    /**
     * Saves an ingredient.
     *
     * @param ingredient the ingredient to be saved
     * @return the saved ingredient
     */
    public Ingredients saveIngredient(Ingredients ingredient){
        Ingredients tmp=ingredientsRepository.findByName(ingredient.getName());
        if(tmp == null){
            return ingredientsRepository.save(ingredient);
        }
        return tmp;

    }

    /**
     * Finds an ingredient by name.
     *
     * @param name the name of the ingredient to find
     * @return the found ingredient, or null if not found
     */


    public Ingredients findByName(String name){
        return ingredientsRepository.findByName(name);
    }

    /**
     * Adds an ingredient.
     *
     * @param ingredient the ingredient to be added
     * @return the added ingredient
     */

    public Ingredients addIngredient(Ingredients ingredient) {
        return saveIngredient(ingredient);
    }

    /**
     * Deletes an ingredient by ID.
     *
     * @param ingredientId the ID of the ingredient to be deleted
     */
    public void deleteIngredient(Long ingredientId) {
       boolean exists= ingredientsRepository.existsById(ingredientId);
       if (!exists){
           throw new IllegalStateException("Ingredient with id " + ingredientId +"does not exist");

       }
       ingredientsRepository.deleteById(ingredientId);
    }

    /**
     * Updates an ingredient.
     *
     * @param ingredientId the ID of the ingredient to be updated
     * @param name the updated name of the ingredient
     * @param expirationDate the updated expiration date of the ingredient
     * @param quantity the updated quantity of the ingredient
     */

    @Transactional
    public void updateIngredient(Long ingredientId,
                                 String name,
                                 LocalDate expirationDate,
                                 Integer quantity) {
        Ingredients ingredient = ingredientsRepository.findById(ingredientId).orElseThrow();
        if (name !=null && name.length()>0 && !Objects.equals(ingredient.getName(),name)){
            ingredient.setName(name);
        }
        if (expirationDate !=null && !Objects.equals(ingredient.getExpirationDate(),expirationDate)){
            ingredient.setExpirationDate(expirationDate);
        }
        if (quantity !=null && !Objects.equals(ingredient.getQuantity(),quantity)){
            ingredient.setQuantity(quantity);
        }
    }
}
