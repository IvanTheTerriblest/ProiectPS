package com.projectPS.Service;

import com.projectPS.Model.Ingredients;
import com.projectPS.Model.Recipes;
import com.projectPS.Repository.RecipesRepository;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
public class RecipesService {
    private final RecipesRepository recipesRepository;

    public RecipesService(RecipesRepository recipesRepository) {
        this.recipesRepository = recipesRepository;
    }

    public List<Recipes> getRecipes(){
        return recipesRepository.findAll();
//        return List.of(new Recipes(1L,"Omlett",List.of(
//                new Ingredients(1L,"Egg", LocalDate.of(2024, Month.APRIL,6),2)),
//                new Time(0,10,0)));
    }
}
