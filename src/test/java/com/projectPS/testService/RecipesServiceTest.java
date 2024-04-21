package com.projectPS.testService;

import com.projectPS.Contract.IngredientsContract;
import com.projectPS.Model.Ingredients;
import com.projectPS.Model.Recipes;
import com.projectPS.Repository.IngredientsRepository;
import com.projectPS.Repository.RecipesRepository;
import com.projectPS.Service.IngredientsService;
import com.projectPS.Service.RecipesService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class RecipesServiceTest {
    private RecipesService recipesService;
    @Mock
    private RecipesRepository recipesRepository;


    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.recipesService = new RecipesService(recipesRepository);
    }

    @Test
    public void testGetRecipes(){
        recipesService.getRecipes();
        Mockito.verify(recipesRepository).findAll();
    }

    @Test
    public void testFindById(){
        Ingredients potato = new Ingredients(15L,"potato", LocalDate.of(2024, Month.APRIL,6), 10);
        Recipes recipe = new Recipes("fries", List.of(potato),LocalTime.of(0,20,0));
        when(recipesService.findById(15L)).thenReturn(java.util.Optional.of(recipe));
        recipesService.findById(15L);
        Mockito.verify(recipesRepository).findById(15L);
    }


    @Test
    public void testSaveRecipes(){
        Ingredients potato = new Ingredients(15L,"potato", LocalDate.of(2024, Month.APRIL,6), 10);
        Recipes recipe = new Recipes("fries", List.of(potato),LocalTime.of(0,20,0));
        recipesService.saveRecipes(recipe);
        Mockito.verify(recipesRepository).save(recipe);
    }

    @Test
    public void testAddRecipes(){
        Ingredients potato = new Ingredients(15L,"potato", LocalDate.of(2024, Month.APRIL,6), 10);
        Recipes recipe = new Recipes("fries", List.of(potato),LocalTime.of(0,20,0));
        recipesService.addRecipes(recipe);
        Mockito.verify(recipesRepository).save(recipe);
    }
    @Test
    public void testDeleteRecipe(){
        when(recipesRepository.existsById(2L)).thenReturn(true);
        this.recipesService.deleteRecipe(2L);
        Mockito.verify(recipesRepository).deleteById(2L);
    }

    @Test
    public void testUpdateRecipe(){
        Ingredients potato = new Ingredients(15L,"potato", LocalDate.of(2024, Month.APRIL,6), 10);
        Recipes recipe = new Recipes(2L,"fries", List.of(potato), LocalTime.of(0,20,0));
        when(recipesService.findById(2L)).thenReturn(java.util.Optional.of(recipe));
        Recipes result = this.recipesService.updateRecipe(2L,"potato", List.of(potato),LocalTime.of(0,10,0));
        Mockito.verify(recipesRepository).findById(2L);
        assertEquals(result,recipe);
    }

    @Test
    public void testFastFoodRecipes(){
        Ingredients potato = new Ingredients(15L,"potato", LocalDate.of(2024, Month.APRIL,6), 10);
        Recipes fries = new Recipes(2L,"fries", List.of(potato), LocalTime.of(0,20,0));
        Recipes mashedPotatoes = new Recipes(3L,"mashed", List.of(potato), LocalTime.of(0,10,0));
        List <Recipes> recipes = new ArrayList<>();
        recipes.add(mashedPotatoes);
        when(recipesService.findFastRecipes()).thenReturn(recipes);
        List<Recipes> result = this.recipesService.findFastRecipes();
        Mockito.verify(recipesRepository).findFastRecipes();
        assertEquals(result,recipes);
    }

    @Test
    public void testSlowFoodRecipes(){
        Ingredients potato = new Ingredients(15L,"potato", LocalDate.of(2024, Month.APRIL,6), 10);
        Recipes fries = new Recipes(2L,"fries", List.of(potato), LocalTime.of(0,20,0));
        Recipes mashedPotatoes = new Recipes(3L,"mashed", List.of(potato), LocalTime.of(0,10,0));
        List <Recipes> recipes = new ArrayList<>();
        when(recipesService.findSlowCookedRecipes()).thenReturn(recipes);
        List<Recipes> result = this.recipesService.findFastRecipes();
        Mockito.verify(recipesRepository).findFastRecipes();
        assertEquals(result,recipes);
    }




}
