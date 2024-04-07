package com.projectPS.Observer;

import com.projectPS.Model.Recipes;

public class Subscriber {
    private String name;
    private Recipes recipes = new Recipes();

    public Subscriber(String name){
        this.name= name;
    }
    public void update(String recipe){
        System.out.println("You have all the ingredients for the recipe:"+ recipe);
    }

    public void subscribeRecipies(Recipes rec){
        recipes = rec;
    }

}
