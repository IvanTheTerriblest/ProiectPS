package com.projectPS;

import com.projectPS.model.Ingredients;
import com.projectPS.repository.IngredientsRepository;
import com.projectPS.service.IngredientsService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.Month;

public class Tests1 {
    private IngredientsService ingredientsService;
    @Mock
    private IngredientsRepository ingredientsRepository;


    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.ingredientsService = new IngredientsService(ingredientsRepository);
    }


    @Test
    public void testSaveIngredients(){
        Ingredients potato = new Ingredients("potato", LocalDate.of(2024, Month.APRIL,6), 10);
        ingredientsService.saveIngredient(potato);
        Mockito.verify(ingredientsRepository).save(potato);
    }

    @Test
    public void testAddIngredients(){
        Ingredients potato = new Ingredients("potato", LocalDate.of(2024, Month.APRIL,6), 10);
        ingredientsService.addIngredient(potato);
        Mockito.verify(ingredientsRepository).save(potato);
    }

    @Test
    public void testGetIngredients(){
        ingredientsService.getIngredients();
        Mockito.verify(ingredientsRepository).findAll();
    }



}
