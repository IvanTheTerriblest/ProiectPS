package com.projectPS.testService;

import com.projectPS.Contract.IngredientsContract;
import com.projectPS.Model.Ingredients;
import com.projectPS.Repository.IngredientsRepository;
import com.projectPS.Service.IngredientsService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class IngredientsServiceTest {
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
    public void testFindById(){
        Ingredients potato = new Ingredients(15L,"potato", LocalDate.of(2024, Month.APRIL,6), 10);
        when(ingredientsService.findById(15L)).thenReturn(java.util.Optional.of(potato));
        ingredientsService.findById(15L);
        Mockito.verify(ingredientsRepository).findById(15L);
    }

    @Test
    public void testFindByName(){
        Ingredients milk = new Ingredients(16L,"milk", LocalDate.of(2024, Month.APRIL,6), 10);
        when(ingredientsService.findByName("milk")).thenReturn(milk);
        ingredientsService.findByName("milk");
        Mockito.verify(ingredientsRepository).findByName("milk");;
    }

    @Test
    public void testFindByExpirationDate(){
        Ingredients milk = new Ingredients(16L,"milk", LocalDate.of(2024, Month.APRIL,6), 10);
        Ingredients mushrooms = new Ingredients("mushrooms",LocalDate.of(2024, Month.APRIL,6),20);
        Ingredients sausage = new Ingredients("sausage",LocalDate.of(2024, Month.MAY,6),20);
        Date data = new Date(2024, Calendar.APRIL,6);
        List<Ingredients> ingredients = new ArrayList<>();
        ingredients.add(mushrooms);
        ingredients.add(milk);
        ingredients.add(sausage);
        List<Ingredients> result = new ArrayList<>();
        result.add(milk);
        result.add(mushrooms);
        when(ingredientsService.findByExpirationDate(data)).thenReturn(result);
        ingredientsService.findByExpirationDate(data);
        Mockito.verify(ingredientsRepository).findByExpirationDate(data);
        assertEquals(result,ingredientsService.findByExpirationDate(data));
    }

    @Test
    public void testFindExpiredIngredients(){
        Ingredients milk = new Ingredients(16L,"milk", LocalDate.of(2024, Month.MAY,6), 10);
        Ingredients mushrooms = new Ingredients("mushrooms",LocalDate.of(2023, Month.MARCH,6),20);
        List<Ingredients> ingredients = new ArrayList<>();
        ingredients.add(milk);
        ingredients.add(mushrooms);
        List<Ingredients> result = new ArrayList<>();
        result.add(mushrooms);
        when(ingredientsService.findExpiredIngredients()).thenReturn(result);
        ingredientsService.findExpiredIngredients();
        Mockito.verify(ingredientsRepository).findExpiredIngredients();
        assertEquals(result,ingredientsService.findExpiredIngredients());
    }

    @Test
    public void testDeleteIngredient(){
        this.ingredientsService.deleteIngredient(2L);
        Mockito.verify(ingredientsRepository).deleteById(2L);
    }

    @Test
    public void testUpdateIngredient(){
        Ingredients milk = new Ingredients(16L,"milk", LocalDate.of(2024, Month.MAY,6), 10);
        when(ingredientsService.findById(16L)).thenReturn(java.util.Optional.of(milk));
        Ingredients result = this.ingredientsService.updateIngredient(16L,"milk", LocalDate.of(2024, Month.MAY,6), 10);
        Mockito.verify(ingredientsRepository).findById(16L);
        assertEquals(result,milk);
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
