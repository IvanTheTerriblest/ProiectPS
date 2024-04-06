package com.projectPS.Contract;

import com.projectPS.Model.Ingredients;

import java.time.LocalDate;
import java.util.List;

public interface IngredientsContract {
    public List<Ingredients> getIngredients();
    public Ingredients saveIngredient(Ingredients ingredients);
    public void updateIngredient(Long ingredientId,
                                        String name,
                                        LocalDate expirationDate,
                                        Integer quantity);
    public Ingredients addIngredient(Ingredients ingredients);
    public void deleteIngredient(Long id);
    public Ingredients findByName(String name);
}
