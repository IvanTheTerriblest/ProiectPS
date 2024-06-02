package com.projectPS.contract;

import com.projectPS.model.Ingredients;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IngredientsContract {
    public List<Ingredients> getIngredients();
    public Ingredients saveIngredient(Ingredients ingredients);
    public Ingredients updateIngredient(Long ingredientId,
                                        String name,
                                        LocalDate expirationDate,
                                        Integer quantity);
    public Ingredients addIngredient(Ingredients ingredients);
    public void deleteIngredient(Long id);
    public Optional<Ingredients> findById(Long id);
    public Ingredients findByName(String name);
    public List<Ingredients> findByExpirationDate(Date date);
    public List<Ingredients> findExpiredIngredients();
}
