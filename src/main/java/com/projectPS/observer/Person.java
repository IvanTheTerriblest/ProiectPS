package com.projectPS.observer;

import com.projectPS.model.Ingredients;
import com.projectPS.model.Recipes;

public class Person implements RecipeObserver {
    public Person(String name) {
        this.name = name;
    }

    public void readyToCook(Recipes rec){
        for (Ingredients ing : rec.getIngredientsList()){
            if (ing.getQuantity()==0){
                return;
            }
        }
        System.out.println("You have all the ingredients for the recipe:"+ rec.getName());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;


    @Override
    public void update(Object o) {
        if(o instanceof Recipes){
            Recipes recipes = (Recipes) o;
            readyToCook(recipes);
        }
    }
}
