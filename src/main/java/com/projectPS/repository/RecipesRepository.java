package com.projectPS.repository;

import com.projectPS.model.Recipes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface RecipesRepository extends JpaRepository<Recipes,Long> {

    Recipes findByName(String name);
    @Query("SELECT r FROM Recipes r WHERE HOUR(r.timeForCooking) * 60 + MINUTE(r.timeForCooking) <= 10")
    List<Recipes> findFastRecipes();

    @Query("SELECT r FROM Recipes r WHERE HOUR(r.timeForCooking) * 60 + MINUTE(r.timeForCooking) >= 180")
    List<Recipes> findSlowCookedRecipes();


}
