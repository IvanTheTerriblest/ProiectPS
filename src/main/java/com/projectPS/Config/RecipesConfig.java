package com.projectPS.Config;

import com.projectPS.Model.Ingredients;
import com.projectPS.Model.Recipes;
import com.projectPS.Observer.Person;
import com.projectPS.Observer.Subscriber;
import com.projectPS.Repository.IngredientsRepository;
import com.projectPS.Repository.RecipesRepository;
import com.projectPS.Service.IngredientsService;
import com.projectPS.Service.RecipesService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Time;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class RecipesConfig {
    /**
     * Configures a CommandLineRunner bean to initialize and save initial data for Recipes and Ingredients.
     *
     * @param recipesService the service for managing Recipes
     * @param service the service for managing Ingredients
     * @return a CommandLineRunner bean to execute the initialization logic
     */
    @Bean
    CommandLineRunner commandLineRunnerRecipes(RecipesService recipesService, IngredientsService service){
        return args -> {
            Ingredients egg = new Ingredients("Egg", LocalDate.of(2024, Month.APRIL,6),2);
            Ingredients bacon =new Ingredients(
                    "Bacon",
                    LocalDate.of(2024, Month.APRIL,6),
                    10);
            Ingredients tomato = new Ingredients("Tomato", LocalDate.of(2024, Month.APRIL,6),2);
            Ingredients cucumber = new Ingredients("Cucumber", LocalDate.of(2024, Month.APRIL,19),2);
            Ingredients cabbage = new Ingredients("Cabbage", LocalDate.of(2024, Month.APRIL,7),2);
            egg = service.saveIngredient(egg);
            tomato = service.saveIngredient(tomato);
            cabbage = service.saveIngredient(cabbage);
            cucumber = service.saveIngredient(cucumber);
            bacon = service.saveIngredient(bacon);
            Recipes omlett = new Recipes("Omlett", List.of(egg, tomato, bacon),
                new Time(0,10,0));
            Recipes salad = new Recipes("Salad", List.of(
                    tomato,cucumber,cabbage), new Time(0,10,0));
            omlett = recipesService.saveRecipes(omlett);
            salad = recipesService.saveRecipes(salad);
            Person ivan = new Person("Chef Ivan");
            omlett.subscribe(ivan);
            salad.subscribe(ivan);

        };
    }

}
