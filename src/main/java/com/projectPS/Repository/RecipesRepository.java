package com.projectPS.Repository;

import com.projectPS.Model.Ingredients;
import com.projectPS.Model.Recipes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.List;

@Repository

public interface RecipesRepository extends JpaRepository<Recipes,Long> {
    Recipes findByName(String name);
    @Query("Select r from Recipes r where String(r.timeForCooking) <=String(001000)")
    List<Recipes> findFastRecipes();
}
