package com.projectPS.Service;

import com.projectPS.Model.Ingredients;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
@Service

public class IngredientsService {
    public List<Ingredients> getIngredients(){
        return List.of(new Ingredients(1L,"Egg", LocalDate.of(2024, Month.APRIL,6),10));
    }
}
