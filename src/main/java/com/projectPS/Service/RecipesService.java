package com.projectPS.Service;

import com.projectPS.Model.Ingredients;
import com.projectPS.Model.Recipes;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
public class RecipesService {

    public List<Recipes> getRecipes(){
        return List.of(new Recipes(1L,"Omlett",List.of(
                new Ingredients(1L,"Egg", LocalDate.of(2024, Month.APRIL,6),2)),
                new Time(0,10,0)));
    }
}
