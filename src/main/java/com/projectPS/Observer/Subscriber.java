package com.projectPS.Observer;

import com.projectPS.Model.Recipes;

/**
 * Represents a subscriber who receives updates about recipes.
 */

public class Subscriber {
    private String name;
    private Recipes recipes = new Recipes();

    public Subscriber(String name){
        this.name= name;
    }

    /**
     * Updates the subscriber with the given recipe.
     *
     * @param recipe The recipe to update the subscriber with
     */
    public void update(String recipe){
        System.out.println("You have all the ingredients for the recipe:"+ recipe);
    }

    /**
     * Subscribes the subscriber to receive updates about recipes.
     *
     * @param rec The recipes to subscribe to
     */

    public void subscribeRecipies(Recipes rec){
        recipes = rec;
    }

}
