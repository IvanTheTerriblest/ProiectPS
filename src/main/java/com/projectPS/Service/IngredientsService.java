package com.projectPS.Service;

import com.projectPS.Model.Ingredients;
import com.projectPS.Repository.IngredientsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service

public class IngredientsService {

    private final IngredientsRepository ingredientsRepository;
    @Autowired

    public IngredientsService(IngredientsRepository ingredientsRepository) {
        this.ingredientsRepository = ingredientsRepository;
    }


    public List<Ingredients> getIngredients(){
        return ingredientsRepository.findAll();
        //return List.of(new Ingredients(1L,"Egg", LocalDate.of(2024, Month.APRIL,6),10));
    }
    public Ingredients saveIngredient(Ingredients ingredient){
        Ingredients tmp=ingredientsRepository.findByName(ingredient.getName());
        if(tmp == null){
            return ingredientsRepository.save(ingredient);
        }
        return tmp;

    }
    public Ingredients findByName(String name){
        return ingredientsRepository.findByName(name);
    }

    public Ingredients addIngredient(Ingredients ingredient) {
        return saveIngredient(ingredient);
    }

    public void deleteIngredient(Long ingredientId) {
       boolean exists= ingredientsRepository.existsById(ingredientId);
       if (!exists){
           throw new IllegalStateException("Ingredient with id " + ingredientId +"does not exist");

       }
       ingredientsRepository.deleteById(ingredientId);
    }

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
