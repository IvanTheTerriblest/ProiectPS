package com.projectPS.Util;

import com.projectPS.Model.Ingredients;

import java.util.List;

public class Fridge {
    private static List<Ingredients> fridge;

    public Fridge() {
    }

    public static List<Ingredients> getFridge() {
        return fridge;
    }

    public static void setFridge(List<Ingredients> fridge) {
        Fridge.fridge = fridge;
    }
    
    public void addIngredient(Ingredients ingredient){
        fridge.add(ingredient);
    }
    
    public void removeIngredient(Ingredients ingredient){
        fridge.remove(ingredient);
    }
    public void addQuantityForIngredient(Ingredients ingredient, int quantity){
        for (Ingredients ing:fridge) {
            if (ing.getId() == ingredient.getId()){
                ing.setQuantity(ing.getQuantity()+quantity);
            }
        }
    }
}
