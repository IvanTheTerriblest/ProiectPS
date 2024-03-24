package com.projectPS.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Time;
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
    @OneToMany
    private List<Ingredients> ingredientsList;
    private Time timeForCooking;


    public Recipes(String name, List<Ingredients> ingredientsList, Time timeForCooking) {
        this.name = name;
        this.ingredientsList = ingredientsList;
        this.timeForCooking = timeForCooking;
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
