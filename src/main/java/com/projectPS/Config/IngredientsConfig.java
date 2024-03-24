package com.projectPS.Config;

import com.projectPS.Model.Ingredients;
import com.projectPS.Repository.IngredientsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration

public class IngredientsConfig {
    @Bean

    CommandLineRunner commandLineRunnerIngredients(IngredientsRepository ingredientsRepository){
        return args -> {//Ingredients egg =new Ingredients(1L,
//                "Egg",
//                LocalDate.of(2024, Month.APRIL,6),
//                10);
//
//            Ingredients bacon =new Ingredients(
//                    "Bacon",
//                    LocalDate.of(2024, Month.APRIL,6),
//                    10);
//
//            ingredientsRepository.saveAll(List.of(egg,bacon));

        };
    }
}
