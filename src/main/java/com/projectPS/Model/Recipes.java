package com.projectPS.Model;

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

public class Recipes {
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
    private List<Subscriber> subs = new ArrayList<>();


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

    public void subscribe(Subscriber sub)
    {
        subs.add(sub);

    }

    /**
     * Unsubscribe a subscriber from receiving updates about this recipe.
     *
     * @param sub The subscriber to unsubscribe
     */

    public void unsubscribe(Subscriber sub)
    {
        subs.remove(sub);
    }

    /**
     * Notify all subscribers about the recipe.
     *
     * @param rec The recipe to notify subscribers about
     */

    public void notifySubscribers(String rec)
    {
        for(Subscriber sub: subs){
            sub.update(rec);
        }
    }

    /**
     * Check if the recipe is ready to cook and notify subscribers if it is.
     *
     * @param rec The recipe to check and notify subscribers about
     */

    public void readyToCook(Recipes rec){
        for (Ingredients ing : rec.getIngredientsList()){
            if (ing.getQuantity()==0){
                return;
            }
        }
        notifySubscribers(rec.getName());
    }


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
