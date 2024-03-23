package com.projectPS.Model;

import java.sql.Time;
import java.util.List;

public class Recipes {
    private Long id;
    private String name;
    private List<Ingredients> ingredientsList;
    private Time timeForCooking;

    public Recipes(String name, List<Ingredients> ingredientsList, Time timeForCooking) {
        this.name = name;
        this.ingredientsList = ingredientsList;
        this.timeForCooking = timeForCooking;
    }

    public Recipes() {
    }

    public Recipes(Long id, String name, List<Ingredients> ingredientsList, Time timeForCooking) {
        this.id = id;
        this.name = name;
        this.ingredientsList = ingredientsList;
        this.timeForCooking = timeForCooking;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
