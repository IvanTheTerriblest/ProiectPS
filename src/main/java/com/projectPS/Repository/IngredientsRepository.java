package com.projectPS.Repository;

import com.projectPS.Model.Ingredients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientsRepository extends JpaRepository<Ingredients,Long> {
    Ingredients findByName(String name);
    @Query("Select i from Ingredients i Where i.expirationDate =:date")
    List<Ingredients> findByExpirationDate(@Param("date")Date date);

    @Query("Select i from Ingredients i where i.expirationDate < CURRENT DATE ")
    List<Ingredients> findExpiredIngredients();
}
