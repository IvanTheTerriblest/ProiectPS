package com.projectPS.Model;

import com.projectPS.Observer.RecipeObserver;
import com.projectPS.Observer.Subject;
import com.projectPS.Observer.Subscriber;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table

public class Recipes implements Subject {
    @Id
    @SequenceGenerator(name="recipes_sequence",
            sequenceName = "recipes_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "recipes_sequence"
    )
    private Long id;
    private String name;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Ingredients> ingredientsList;
    private Time timeForCooking;
    @Transient
    private List<RecipeObserver> observers = new ArrayList<>();


    public Recipes(String name, List<Ingredients> ingredientsList, Time timeForCooking) {
        this.name = name;
        this.ingredientsList = ingredientsList;
        this.timeForCooking = timeForCooking;

    }

    /**
     * Subscribe a subscriber to receive updates about this recipe.
     *
     * @param sub The subscriber to subscribe
     */

    public void subscribe(RecipeObserver sub)
    {
        observers.add(sub);
        notifySubscribers();
    }

    /**
     * Unsubscribe a subscriber from receiving updates about this recipe.
     *
     * @param sub The subscriber to unsubscribe
     */

    public void unsubscribe(RecipeObserver sub)
    {
        observers.remove(sub);
    }

    /**
     * Notify all subscribers about the recipe.
     *
     * @param rec The recipe to notify subscribers about
     */

    public void notifySubscribers()
    {
        for(RecipeObserver sub: observers){
            sub.update(this);
        }
    }

    /**
     * Check if the recipe is ready to cook and notify subscribers if it is.
     *
     * @param rec The recipe to check and notify subscribers about
     */



    public List<Ingredients> getIngredientsList() {
        return ingredientsList;
    }

    public void setIngredientsList(List<Ingredients> ingredientsList) {
        this.ingredientsList = ingredientsList;
    }

    public Time getTimeForCooking() {
        return timeForCooking;
    }

    public void setTimeForCooking(Time timeForCooking) {
        this.timeForCooking = timeForCooking;
    }

}
