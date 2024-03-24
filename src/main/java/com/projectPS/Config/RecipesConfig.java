package com.projectPS.Config;

import com.projectPS.Model.Ingredients;
import com.projectPS.Model.Recipes;
import com.projectPS.Repository.IngredientsRepository;
import com.projectPS.Repository.RecipesRepository;
import com.projectPS.Service.RecipesService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Time;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration

public class RecipesConfig {
    @Bean

    CommandLineRunner commandLineRunnerRecipes(RecipesRepository recipesRepository, IngredientsRepository ingredientsRepository){
        return args -> {
            Ingredients egg = new Ingredients(5L,"Egg", LocalDate.of(2024, Month.APRIL,6),2);
            ingredientsRepository.save(egg);
            Recipes omlett = new Recipes("Omlett", List.of(egg),
                new Time(0,10,0));
//            Recipes salad = new Recipes("Salad", List.of(
//                    new Ingredients(1L,"Tomato", LocalDate.of(2024, Month.APRIL,6),2),
//                    new Ingredients(2L,"Cucumber", LocalDate.of(2024, Month.APRIL,19),2),
//                    new Ingredients(3L,"Cabbage", LocalDate.of(2024, Month.APRIL,7),2)),
//                    new Time(0,10,0));
            recipesRepository.saveAll(List.of(omlett));
        };
    }

}
