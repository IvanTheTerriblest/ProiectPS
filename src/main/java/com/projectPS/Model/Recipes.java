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

    public void subscribe(Subscriber sub)
    {
        subs.add(sub);

    }

    public void unsubscribe(Subscriber sub)
    {
        subs.remove(sub);
    }

    public void notifySubscribers(String rec)
    {
        for(Subscriber sub: subs){
            sub.update(rec);
        }
    }

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
