package com.projectPS.Service;

import com.projectPS.Model.Ingredients;
import com.projectPS.Repository.IngredientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
@Service

public class IngredientsService {

    private final IngredientsRepository ingredientsRepository;
    @Autowired

    public IngredientsService(IngredientsRepository ingredientsRepository) {
        this.ingredientsRepository = ingredientsRepository;
    }


    public List<Ingredients> getIngredients(){
        return ingredientsRepository.findAll();
        //return List.of(new Ingredients(1L,"Egg", LocalDate.of(2024, Month.APRIL,6),10));
    }
}
