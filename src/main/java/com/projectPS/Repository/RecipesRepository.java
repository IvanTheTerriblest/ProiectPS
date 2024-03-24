package com.projectPS.Repository;

import com.projectPS.Model.Recipes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface RecipesRepository extends JpaRepository<Recipes,Long> {
}
